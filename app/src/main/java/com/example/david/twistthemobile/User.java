package com.example.david.twistthemobile;

import android.text.LoginFilter;

/**
 * Created by David on 4/07/2017.
 */

public class User {

    private String username;
    private String password;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}