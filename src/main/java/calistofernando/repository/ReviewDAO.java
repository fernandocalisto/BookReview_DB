package calistofernando.repository;

import calistofernando.model.Review;

import java.util.List;

public interface ReviewDAO {

    void addReview (int bookID);
    void deleteReview (int id);
    Review getReview (int id);
    List<Review> getReviewsByBook (int bookId);
    List<Review> getReviewsByUser (String username);

}
