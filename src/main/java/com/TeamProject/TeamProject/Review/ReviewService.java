package com.TeamProject.TeamProject.Review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Review findById(Integer reviewid) {
        return this.reviewRepository.findById(reviewid).get();
    }

}
