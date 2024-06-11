package com.example.buka_buku;

public class WishlistItem {
    private boolean isChecked;
    private int bookCoverResource;
    private String bookTitle;
    private String genre;
    private String author;

    public WishlistItem(boolean isChecked, int bookCoverResource, String bookTitle, String genre, String author) {
        this.isChecked = isChecked;
        this.bookCoverResource = bookCoverResource;
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.author = author;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getBookCoverResource() {
        return bookCoverResource;
    }

    public void setBookCoverResource(int bookCoverResource) {
        this.bookCoverResource = bookCoverResource;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
