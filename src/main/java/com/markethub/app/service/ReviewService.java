package com.markethub.app.service;

import com.markethub.app.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();

    Review getReviewById(long id);

    Review saveReview(Review review);

    Review updateReview(long id, Review review);

    void deleteReviewById(long id);
}
