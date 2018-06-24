package com.therealdanvega.repository;

import com.therealdanvega.domain.Tweet;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Tweet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

}
