package com.example.buka_buku.model;

import java.io.Serializable;

public class Book implements Serializable {
    private String key;
    private String title;
    private String author;
    private String genre;
    private String cover;
    private String description;
    private String rating;

    public Book(String title, String author, String genre, String cover, String description, String rating) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.cover = cover;
        this.rating = rating;
        this.description = description;
    }

    public Book() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
