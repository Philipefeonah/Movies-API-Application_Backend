package com.maxiflexy.moviesapplication.service;

import com.maxiflexy.moviesapplication.entity.Movie;
import com.maxiflexy.moviesapplication.entity.Review;
import com.maxiflexy.moviesapplication.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId){
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;
    }

    public List<Review> getReviewsByMovieId(String imdbId, int page, int size) {
        return reviewRepository.findByImdbId(imdbId, PageRequest.of(page, size)).getContent();
    }
}
