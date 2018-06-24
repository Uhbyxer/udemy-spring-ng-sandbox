package com.therealdanvega.web.rest;

import com.therealdanvega.JblogApp;

import com.therealdanvega.domain.Tweet;
import com.therealdanvega.repository.TweetRepository;
import com.therealdanvega.service.TweetService;
import com.therealdanvega.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;


import static com.therealdanvega.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TweetResource REST controller.
 *
 * @see TweetResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JblogApp.class)
public class TweetResourceIntTest {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_BODY = "AAAAAAAAAA";
    private static final String UPDATED_BODY = "BBBBBBBBBB";

    private static final String DEFAULT_AUTHOR = "AAAAAAAAAA";
    private static final String UPDATED_AUTHOR = "BBBBBBBBBB";

    @Autowired
    private TweetRepository tweetRepository;

    

    @Autowired
    private TweetService tweetService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTweetMockMvc;

    private Tweet tweet;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TweetResource tweetResource = new TweetResource(tweetService);
        this.restTweetMockMvc = MockMvcBuilders.standaloneSetup(tweetResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tweet createEntity(EntityManager em) {
        Tweet tweet = new Tweet()
            .title(DEFAULT_TITLE)
            .body(DEFAULT_BODY)
            .author(DEFAULT_AUTHOR);
        return tweet;
    }

    @Before
    public void initTest() {
        tweet = createEntity(em);
    }

    @Test
    @Transactional
    public void createTweet() throws Exception {
        int databaseSizeBeforeCreate = tweetRepository.findAll().size();

        // Create the Tweet
        restTweetMockMvc.perform(post("/api/tweets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tweet)))
            .andExpect(status().isCreated());

        // Validate the Tweet in the database
        List<Tweet> tweetList = tweetRepository.findAll();
        assertThat(tweetList).hasSize(databaseSizeBeforeCreate + 1);
        Tweet testTweet = tweetList.get(tweetList.size() - 1);
        assertThat(testTweet.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testTweet.getBody()).isEqualTo(DEFAULT_BODY);
        assertThat(testTweet.getAuthor()).isEqualTo(DEFAULT_AUTHOR);
    }

    @Test
    @Transactional
    public void createTweetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tweetRepository.findAll().size();

        // Create the Tweet with an existing ID
        tweet.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTweetMockMvc.perform(post("/api/tweets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tweet)))
            .andExpect(status().isBadRequest());

        // Validate the Tweet in the database
        List<Tweet> tweetList = tweetRepository.findAll();
        assertThat(tweetList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = tweetRepository.findAll().size();
        // set the field null
        tweet.setTitle(null);

        // Create the Tweet, which fails.

        restTweetMockMvc.perform(post("/api/tweets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tweet)))
            .andExpect(status().isBadRequest());

        List<Tweet> tweetList = tweetRepository.findAll();
        assertThat(tweetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAuthorIsRequired() throws Exception {
        int databaseSizeBeforeTest = tweetRepository.findAll().size();
        // set the field null
        tweet.setAuthor(null);

        // Create the Tweet, which fails.

        restTweetMockMvc.perform(post("/api/tweets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tweet)))
            .andExpect(status().isBadRequest());

        List<Tweet> tweetList = tweetRepository.findAll();
        assertThat(tweetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTweets() throws Exception {
        // Initialize the database
        tweetRepository.saveAndFlush(tweet);

        // Get all the tweetList
        restTweetMockMvc.perform(get("/api/tweets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tweet.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].body").value(hasItem(DEFAULT_BODY.toString())))
            .andExpect(jsonPath("$.[*].author").value(hasItem(DEFAULT_AUTHOR.toString())));
    }
    

    @Test
    @Transactional
    public void getTweet() throws Exception {
        // Initialize the database
        tweetRepository.saveAndFlush(tweet);

        // Get the tweet
        restTweetMockMvc.perform(get("/api/tweets/{id}", tweet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tweet.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.body").value(DEFAULT_BODY.toString()))
            .andExpect(jsonPath("$.author").value(DEFAULT_AUTHOR.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTweet() throws Exception {
        // Get the tweet
        restTweetMockMvc.perform(get("/api/tweets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTweet() throws Exception {
        // Initialize the database
        tweetService.save(tweet);

        int databaseSizeBeforeUpdate = tweetRepository.findAll().size();

        // Update the tweet
        Tweet updatedTweet = tweetRepository.findById(tweet.getId()).get();
        // Disconnect from session so that the updates on updatedTweet are not directly saved in db
        em.detach(updatedTweet);
        updatedTweet
            .title(UPDATED_TITLE)
            .body(UPDATED_BODY)
            .author(UPDATED_AUTHOR);

        restTweetMockMvc.perform(put("/api/tweets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTweet)))
            .andExpect(status().isOk());

        // Validate the Tweet in the database
        List<Tweet> tweetList = tweetRepository.findAll();
        assertThat(tweetList).hasSize(databaseSizeBeforeUpdate);
        Tweet testTweet = tweetList.get(tweetList.size() - 1);
        assertThat(testTweet.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTweet.getBody()).isEqualTo(UPDATED_BODY);
        assertThat(testTweet.getAuthor()).isEqualTo(UPDATED_AUTHOR);
    }

    @Test
    @Transactional
    public void updateNonExistingTweet() throws Exception {
        int databaseSizeBeforeUpdate = tweetRepository.findAll().size();

        // Create the Tweet

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTweetMockMvc.perform(put("/api/tweets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tweet)))
            .andExpect(status().isBadRequest());

        // Validate the Tweet in the database
        List<Tweet> tweetList = tweetRepository.findAll();
        assertThat(tweetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTweet() throws Exception {
        // Initialize the database
        tweetService.save(tweet);

        int databaseSizeBeforeDelete = tweetRepository.findAll().size();

        // Get the tweet
        restTweetMockMvc.perform(delete("/api/tweets/{id}", tweet.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tweet> tweetList = tweetRepository.findAll();
        assertThat(tweetList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tweet.class);
        Tweet tweet1 = new Tweet();
        tweet1.setId(1L);
        Tweet tweet2 = new Tweet();
        tweet2.setId(tweet1.getId());
        assertThat(tweet1).isEqualTo(tweet2);
        tweet2.setId(2L);
        assertThat(tweet1).isNotEqualTo(tweet2);
        tweet1.setId(null);
        assertThat(tweet1).isNotEqualTo(tweet2);
    }
}
