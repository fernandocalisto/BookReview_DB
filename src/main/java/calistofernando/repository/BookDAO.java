package calistofernando.repository;

import calistofernando.model.Book;

import java.util.List;

public interface BookDAO {

    boolean addBook (Book book);
    boolean deleteBook (int id);
    Book getBook (int id);
    List<Book> getAllBooks ();

}
