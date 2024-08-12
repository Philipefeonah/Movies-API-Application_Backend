package com.maxiflexy.moviesapplication.repository;

import com.maxiflexy.moviesapplication.entity.Review;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {

    Page<Review> findByImdbId(String imdbId, Pageable pageable);
}
