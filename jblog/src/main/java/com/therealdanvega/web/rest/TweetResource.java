package com.therealdanvega.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.therealdanvega.domain.Tweet;
import com.therealdanvega.service.TweetService;
import com.therealdanvega.web.rest.errors.BadRequestAlertException;
import com.therealdanvega.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Tweet.
 */
@RestController
@RequestMapping("/api")
public class TweetResource {

    private final Logger log = LoggerFactory.getLogger(TweetResource.class);

    private static final String ENTITY_NAME = "tweet";

    private final TweetService tweetService;

    public TweetResource(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    /**
     * POST  /tweets : Create a new tweet.
     *
     * @param tweet the tweet to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tweet, or with status 400 (Bad Request) if the tweet has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tweets")
    @Timed
    public ResponseEntity<Tweet> createTweet(@Valid @RequestBody Tweet tweet) throws URISyntaxException {
        log.debug("REST request to save Tweet : {}", tweet);
        if (tweet.getId() != null) {
            throw new BadRequestAlertException("A new tweet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Tweet result = tweetService.save(tweet);
        return ResponseEntity.created(new URI("/api/tweets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tweets : Updates an existing tweet.
     *
     * @param tweet the tweet to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tweet,
     * or with status 400 (Bad Request) if the tweet is not valid,
     * or with status 500 (Internal Server Error) if the tweet couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tweets")
    @Timed
    public ResponseEntity<Tweet> updateTweet(@Valid @RequestBody Tweet tweet) throws URISyntaxException {
        log.debug("REST request to update Tweet : {}", tweet);
        if (tweet.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Tweet result = tweetService.save(tweet);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tweet.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tweets : get all the tweets.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tweets in body
     */
    @GetMapping("/tweets")
    @Timed
    public List<Tweet> getAllTweets() {
        log.debug("REST request to get all Tweets");
        return tweetService.findAll();
    }

    /**
     * GET  /tweets/:id : get the "id" tweet.
     *
     * @param id the id of the tweet to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tweet, or with status 404 (Not Found)
     */
    @GetMapping("/tweets/{id}")
    @Timed
    public ResponseEntity<Tweet> getTweet(@PathVariable Long id) {
        log.debug("REST request to get Tweet : {}", id);
        Optional<Tweet> tweet = tweetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tweet);
    }

    /**
     * DELETE  /tweets/:id : delete the "id" tweet.
     *
     * @param id the id of the tweet to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tweets/{id}")
    @Timed
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id) {
        log.debug("REST request to delete Tweet : {}", id);
        tweetService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
