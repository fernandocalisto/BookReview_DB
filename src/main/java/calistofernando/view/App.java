package calistofernando.view;

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
            option = scanner.nextInt();
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

}