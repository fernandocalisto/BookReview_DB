package calistofernando.repository;

import calistofernando.factory.ConnectionFactory;
import calistofernando.model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO_MySQL implements ReviewDAO {

    @Override
    public boolean addReview(Review review) {

        String command = "INSERT INTO reviews (livro_id, nome_usuario, estrelas, comentario) VALUES (?,?,?,?)";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement stmt = c.prepareStatement(command)) {

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

        String command = "DELETE FROM reviews WHERE id = ?";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement stmt = c.prepareStatement(command)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao deletar a Review: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Review getReview(int id) {

        String command = "SELECT * FROM reviews WHERE id = ?";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement stmt = c.prepareStatement(command)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    Review ans = new Review(rs.getInt("id"),
                            rs.getInt("livro_id"),
                            rs.getString("nome_usuario"),
                            rs.getInt("estrelas"),
                            rs.getString("comentario"));

                    return ans;

                }

                return null;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao consultar review: " + e.getMessage());
            return null;
        }

    }

    @Override
    public List<Review> getReviewsByBook(int bookId) {

        String command = "SELECT * FROM reviews WHERE livro_id = ?";
        List<Review> ans = new ArrayList<>();

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement stmt = c.prepareStatement(command)) {

            stmt.setInt(1, bookId);

            try (ResultSet rs = stmt.executeQuery()){

                while (rs.next()) {
                    Review r = new Review(rs.getInt("id"),
                            rs.getInt("livro_id"),
                            rs.getString("nome_usuario"),
                            rs.getInt("estrelas"),
                            rs.getString("comentario"));

                    ans.add(r);
                }

                return ans;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao consultar as Reviews do livro: " + e.getMessage());
            return List.of();
        }

    }

    @Override
    public List<Review> getReviewsByUser(String username) {

        String command = "SELECT * FROM reviews WHERE nome_usuario = ?";
        List<Review> ans = new ArrayList<>();

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement stmt = c.prepareStatement(command)){

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    Review r = new Review(rs.getInt("id"),
                            rs.getInt("livro_id"),
                            rs.getString("nome_usuario"),
                            rs.getInt("estrelas"),
                            rs.getString("comentario"));

                    ans.add(r);

                }

                return ans;

            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar Reviews de Usuário: " + e.getMessage());
            return List.of();
        }

    }
}
