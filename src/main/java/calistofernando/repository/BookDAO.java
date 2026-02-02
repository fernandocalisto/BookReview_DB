package calistofernando.repository;

import calistofernando.model.Book;

import java.util.List;

public interface BookDAO {

    void addBook (Book book);
    void deleteBook (int id);
    Book getBook (int id);
    List<Book> getAllBooks ();

}
