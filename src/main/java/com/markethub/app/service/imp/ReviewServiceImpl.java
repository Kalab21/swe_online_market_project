package com.markethub.app.service.imp;

import com.markethub.app.model.Review;
import com.markethub.app.repository.ReviewRepository;
import com.markethub.app.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;


    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReviewById(long id) {
        return reviewRepository.findById(id).orElseGet(null);
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(long id, Review review) {
        review.setId(id);
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReviewById(long id) {
        reviewRepository.deleteById(id);
    }
}

