package calistofernando.view;

import calistofernando.model.Book;
import calistofernando.repository.BookDAO_MySQL;
import calistofernando.repository.ReviewDAO_MySQL;

import java.util.Scanner;

public class App {

    private final BookDAO_MySQL bookDAO;
    private final ReviewDAO_MySQL reviewDAO;
    private final Scanner scanner;

    public App () {

        this.bookDAO = new BookDAO_MySQL();
        this.reviewDAO = new ReviewDAO_MySQL();
        this.scanner = new Scanner(System.in);

    }

    public void run () {
        int option = -1;
        while (option !=0) {
            showMenu();
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                handleOption(option);
            } else {
                System.out.println("Please, enter a number!");
                scanner.next();
            }
        }
    }

    public void showMenu () {
        System.out.println("\n=======================================");
        System.out.println("       LIBRARY MANAGEMENT SYSTEM       ");
        System.out.println("=======================================");
        System.out.println(" [1] Register New Book");
        System.out.println(" [2] List All Books");
        System.out.println(" [3] Search Book & Reviews (Details)");
        System.out.println(" [4] Delete a Book");
        System.out.println("---------------------------------------");
        System.out.println(" [5] Add Review to a Book");
        System.out.println(" [6] Delete a Review");
        System.out.println(" [7] View My Reviews (Search by User)");
        System.out.println("---------------------------------------");
        System.out.println(" [0] EXIT");
        System.out.println("=======================================");
        System.out.print("Choose an option: ");
    }

    private void handleOption(int option) {
        switch (option) {
            case 1 -> registerBook();
            case 2 -> listAllBooks();
            case 3 -> showBookDetails();
            case 4 -> deletingBook();
            case 5 -> addingReview();
            case 6 -> deletingReview();
            case 7 -> showUserReviews();
            case 0 -> System.out.println("Closing system... Goodbye!");
            default -> System.out.println("Invalid option! Try again.");
        }
    }

    private void registerBook() {
    }

    private void listAllBooks() {
    }

    private void showBookDetails() {
    }

    private void deletingBook() {
    }

    private void addingReview() {
    }

    private void deletingReview() {
    }

    private void showUserReviews() {
    }

}