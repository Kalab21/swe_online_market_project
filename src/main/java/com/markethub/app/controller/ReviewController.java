package com.markethub.app.controller;

import com.markethub.app.model.Review;
import com.markethub.app.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public List<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }

    @GetMapping("{id}")
    public Review getReviewById(@PathVariable("id") long id){
        return reviewService.getReviewById(id);
    }

    @PostMapping("/save-review")
    public Review saveReview(@RequestBody Review review){
        return reviewService.saveReview(review);
    }

    @PutMapping("/{id}/update-review")
    public Review updateReview(@PathVariable("id") long id, @RequestBody Review review){
        return reviewService.updateReview(id, review);
    }

    @DeleteMapping("{id}")
    public void deleteReviewById(@PathVariable("id") long id){
        reviewService.deleteReviewById(id);
    }

}
