package calistofernando.factory;

import java.sql.*;

public class CreateDatabase {

    public void createDatabase() {

        try (Connection c = ConnectionFactory.getConnectionServerOnly(); Statement stmt = c.createStatement()) {

            if (!databaseExists(c, "bookreview_db")) {
                stmt.executeUpdate("CREATE DATABASE bookreview_db");
                System.out.println("BANCO DE DADOS CRIADO COM SUCESSO!");
                stmt.executeUpdate("USE bookreview_db");
                createTables(stmt);
            } else {
                System.out.println("BANCO DE DADOS JÁ EXISTENTE!");
                System.out.println("PROGRAMA PRONTO PARA SER INICIADO!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o SQL!", e);
        }
    }

    private boolean databaseExists(Connection conn, String dbName) throws SQLException {
        ResultSet resultSet = conn.getMetaData().getCatalogs();
        while (resultSet.next()) {
            String databaseName = resultSet.getString(1);
            if (databaseName.equalsIgnoreCase(dbName)) {
                return true;
            }
        }
        return false;
    }


    private void createTables(Statement stmt) throws SQLException {

        String sqlLivros = "CREATE TABLE IF NOT EXISTS livros (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "titulo VARCHAR(255) NOT NULL, " +
                "autor VARCHAR(150))";

        String sqlReviews = "CREATE TABLE IF NOT EXISTS reviews (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "livro_id INT NOT NULL, " +
                "nome_usuario VARCHAR(100) NOT NULL, " +
                "estrelas INT NOT NULL, " +
                "comentario TEXT, " +
                "CONSTRAINT fk_livro FOREIGN KEY (livro_id) REFERENCES livros(id) ON DELETE CASCADE, " +
                "CONSTRAINT chk_estrelas CHECK (estrelas BETWEEN 1 AND 5))";

        stmt.execute(sqlLivros);
        stmt.execute(sqlReviews);
        System.out.println("TABELAS CRIADAS!");
        System.out.println("PROGRAMA PRONTO PARA SER INICIADO!");

    }


}
