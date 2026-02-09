package calistofernando.view;

import calistofernando.model.Book;
import calistofernando.model.Review;
import calistofernando.repository.BookDAO_MySQL;
import calistofernando.repository.ReviewDAO_MySQL;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;
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

        scanner.nextLine();
        Book b = new Book();
        System.out.println("Enter the book title: ");
        b.setTitle(scanner.nextLine());

        System.out.println("Enter the author: ");
        b.setAuthor(scanner.nextLine());

        if (bookDAO.addBook(b)) {
            System.out.println("Success: Book registered!");
        } else {
            System.out.println("Error: Could not register book.");
        }

    }

    private void listAllBooks() {
        List<Book> bookList = bookDAO.getAllBooks();

        System.out.println("\n===========================================================");
        System.out.println("                      Book List                      ");
        System.out.println("===========================================================");
        System.out.printf("%-5s | %-25s | %-20s\n", "ID", "TÍTULO", "AUTOR");
        System.out.println("-----------------------------------------------------------");
        if (bookList.isEmpty()) {
            System.out.println("          No books currently available.          ");
        } else {
            for (Book b : bookList) {
                System.out.printf("%-5d | %-25s | %-20s\n",
                        b.getId(),
                        b.getTitle(),
                        b.getAuthor());
            }
        }
        System.out.println("===========================================================\n");
    }

    private void showBookDetails() {
        System.out.print("\nEnter the book ID to see details: ");

        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            scanner.nextLine();

            Book b = bookDAO.getBook(id);

            if (b != null) {
                System.out.println("\n=======================================");
                System.out.println("            BOOK DETAILS               ");
                System.out.println("=======================================");
                System.out.println("Title:  " + b.getTitle());
                System.out.println("Author: " + b.getAuthor());
                System.out.println("---------------------------------------");

                List<Review> reviews = reviewDAO.getReviewsByBook(id);

                System.out.println("REVIEWS:");
                if (reviews.isEmpty()) {
                    System.out.println("  No reviews yet for this book.");
                } else {
                    for (Review r : reviews) {
                        System.out.printf("  [%d stars] %s: \"%s\"\n",
                                r.getStars(), r.getUsername(), r.getComment());
                    }
                }
                System.out.println("=======================================");
            } else {
                System.out.println("\n>>> Error: Book with ID " + id + " not found.");
            }
        } else {
            System.out.println("\n>>> Error: Please enter a numeric ID.");
            scanner.nextLine();
        }
    }

    private void deletingBook() {
        System.out.print("\nEnter the ID of the book you want to delete: ");

        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Are you sure you want to delete book #" + id + "? (Y/N): ");
            String confirm = scanner.nextLine().trim().toUpperCase();

            if (confirm.equals("Y")) {
                if (bookDAO.deleteBook(id)) {
                    System.out.println(">>> Book successfully deleted!");
                } else {
                    System.out.println(">>> Error: Could not delete. Check if the ID exists or if there are linked reviews.");
                }
            } else {
                System.out.println(">>> Operation canceled.");
            }
        } else {
            System.out.println(">>> Error: Please enter a numeric ID.");
            scanner.nextLine();
        }
    }

    private void addingReview() {
        scanner.nextLine();
        int bookId = -1;
        while (true) {
            System.out.print("Enter the ID of the book you wish to review: ");
            if (scanner.hasNextInt()) {
                bookId = scanner.nextInt();
                scanner.nextLine();
                if (bookDAO.getBook(bookId) != null) break;
                System.out.println(">>> Error: Book ID not found.");
            } else {
                System.out.println(">>> Error: Please enter a numeric ID.");
                scanner.nextLine();
            }
        }
        String username = "";
        while (username.isBlank()) {
            System.out.print("Your name: ");
            username = scanner.nextLine().trim();
        }
        int stars = -1;
        while (stars < 1 || stars > 5) {
            System.out.print("Stars (1-5): ");
            if (scanner.hasNextInt()) {
                stars = scanner.nextInt();
                scanner.nextLine();
                if (stars < 1 || stars > 5) System.out.println(">>> Use a scale of 1 to 5.");
            } else {
                System.out.println(">>> Please enter a number.");
                scanner.nextLine();
            }
        }
        System.out.print("Your comment: ");
        String comment = scanner.nextLine();
        Review newReview = new Review(comment, stars, username, bookId);
        if (reviewDAO.addReview(newReview)) {
            System.out.println("\n>>> Review added successfully!");
        } else {
            System.out.println("\n>>> Failed to save review.");
        }
    }

    private void deletingReview() {
        scanner.nextLine();
        System.out.println("Insert the ID of the review you want to delete: ");
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Are you sure you want to delete this review? (Y/N): ");
            String confirm = scanner.nextLine().trim().toUpperCase();
            if (confirm.equals("Y")) {
                if (reviewDAO.deleteReview(id)) {
                    System.out.println("Review successfully deleted!");
                } else {
                    System.out.println("Failed to delete review");
                }
            } else {
                System.out.println("Operation Aborted.");
            }
        } else {
            System.out.println("Operation Aborted. Enter a valid numeric ID.");
            scanner.nextLine();
        }
    }

    private void showUserReviews() {
    }

}