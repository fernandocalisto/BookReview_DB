package calistofernando.repository;

import calistofernando.model.Review;

import java.util.List;

public interface ReviewDAO {

    boolean addReview (Review review);
    boolean deleteReview (int id);
    Review getReview (int id);
    List<Review> getReviewsByBook (int bookId);
    List<Review> getReviewsByUser (String username);

}
