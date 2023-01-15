package org.example.service;

import org.example.entity.Review;

public interface ReviewService extends GenericsService<Review> {

    Review read();
}
