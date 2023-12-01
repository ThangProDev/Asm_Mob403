package com.example.asm_mob403.Model;

public class User {
    String _id, username, passwork,email, imageUser, fullname;

    public User(String username, String passwork, String email, String imageUser, String fullname) {
        this.username = username;
        this.passwork = passwork;
        this.email = email;
        this.imageUser = imageUser;
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswork() {
        return passwork;
    }

    public void setPasswork(String passwork) {
        this.passwork = passwork;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUser() {
        return imageUser;
    }

    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public User() {
    }

    public User(String _id, String username, String passwork, String email, String imageUser, String fullname) {
        this._id = _id;
        this.username = username;
        this.passwork = passwork;
        this.email = email;
        this.imageUser = imageUser;
        this.fullname = fullname;

    }

    public String get_Id() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }
}
