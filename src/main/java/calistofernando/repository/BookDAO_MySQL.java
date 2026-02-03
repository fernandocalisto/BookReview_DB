package calistofernando.repository;

import calistofernando.factory.ConnectionFactory;
import calistofernando.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDAO_MySQL implements BookDAO {


    @Override
    public boolean addBook(Book book) {
        String command = "INSERT INTO livros (titulo, autor) VALUES (?,?);";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement stmt = c.prepareStatement(command)){

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        }catch (SQLException e) {
            System.err.println("Erro ao adicionar livro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteBook(int id) {
        String command = "DELETE FROM livros WHERE id = ?;";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement stmt = c.prepareStatement(command)){

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao Deletar livro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Book getBook(int id) {

        String command = "SELECT * FROM livros WHERE id = ?";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement stmt = c.prepareStatement(command)){

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Book(rs.getString("titulo"), rs.getString("autor"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao Achar o livro: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return List.of();
    }
}
