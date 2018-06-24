package com.therealdanvega.service;

import com.therealdanvega.domain.Tweet;
import com.therealdanvega.repository.TweetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
/**
 * Service Implementation for managing Tweet.
 */
@Service
@Transactional
public class TweetService {

    private final Logger log = LoggerFactory.getLogger(TweetService.class);

    private final TweetRepository tweetRepository;

    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    /**
     * Save a tweet.
     *
     * @param tweet the entity to save
     * @return the persisted entity
     */
    public Tweet save(Tweet tweet) {
        log.debug("Request to save Tweet : {}", tweet);        return tweetRepository.save(tweet);
    }

    /**
     * Get all the tweets.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Tweet> findAll() {
        log.debug("Request to get all Tweets");
        return tweetRepository.findAll();
    }


    /**
     * Get one tweet by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<Tweet> findOne(Long id) {
        log.debug("Request to get Tweet : {}", id);
        return tweetRepository.findById(id);
    }

    /**
     * Delete the tweet by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Tweet : {}", id);
        tweetRepository.deleteById(id);
    }
}
