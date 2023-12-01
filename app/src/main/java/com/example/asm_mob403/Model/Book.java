package com.example.asm_mob403.Model;

public class Book {
    String _id, name, describe, author, imageBook, date, content;

    @Override
    public String toString() {
        return "Book{" +
                "id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", author='" + author + '\'' +
                ", imageBook='" + imageBook + '\'' +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Book(String name, String describe, String author, String imageBook, String date, String content) {
        this.name = name;
        this.describe = describe;
        this.author = author;
        this.imageBook = imageBook;
        this.date = date;
        this.content = content;
    }

    public Book(String _id, String name, String describe, String author, String imageBook, String date, String content) {
        this._id = _id;
        this.name = name;
        this.describe = describe;
        this.author = author;
        this.imageBook = imageBook;
        this.date = date;
        this.content = content;
    }

    public String get_Id() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageBook() {
        return imageBook;
    }

    public void setImageBook(String imageBook) {
        this.imageBook = imageBook;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
