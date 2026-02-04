package calistofernando.model;

public class Review {

    private int id;
    private int bookID;
    private String username;
    private int stars;
    private String comment;

    public Review(int id, int bookID, String username, int stars, String comment) {
        this.id = id;
        this.bookID = bookID;
        this.username = username;
        this.stars = stars;
        this.comment = comment;
    }

    public Review() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
}
