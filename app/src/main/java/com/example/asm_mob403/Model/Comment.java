package com.example.asm_mob403.Model;

public class Comment {

    String _id, content, date, idBook, idUser, fullName, img;

    public Comment(String content, String date, String idBook, String idUser, String fullName, String img) {
        this.content = content;
        this.date = date;
        this.idBook = idBook;
        this.idUser = idUser;
        this.fullName = fullName;
        this.img = img;
    }

    public Comment(String content, String date) {
        this.content = content;
        this.date = date;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Comment(String _id, String content, String date, String idBook, String idUser, String fullName, String img) {
        this._id = _id;
        this.content = content;
        this.date = date;
        this.idBook = idBook;
        this.idUser = idUser;
        this.fullName = fullName;
        this.img = img;
    }
}
