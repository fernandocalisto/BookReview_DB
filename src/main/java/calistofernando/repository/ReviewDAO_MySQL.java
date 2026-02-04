package calistofernando.repository;

import calistofernando.factory.ConnectionFactory;
import calistofernando.model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ReviewDAO_MySQL implements ReviewDAO{

    @Override
    public boolean addReview(Review review) {

        String command = "INSERT INTO reviews (livro_id, nome_usuario, estrelas, comentario) VALUES (?,?,?,?)";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement stmt = c.prepareStatement(command)){

            stmt.setInt(1, review.getBookID());
            stmt.setString(2, review.getUsername());
            stmt.setInt(3, review.getStars());
            stmt.setString(4, review.getComment());

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar a review: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteReview(int id) {
        return false;
    }

    @Override
    public Review getReview(int id) {
        return null;
    }

    @Override
    public List<Review> getReviewsByBook(int bookId) {
        return List.of();
    }

    @Override
    public List<Review> getReviewsByUser(String username) {
        return List.of();
    }
}
