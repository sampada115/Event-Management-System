package com.eventmgmt.model;

public class UserManager {

    private static UserManager instance;

    private UserManager() {}

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public void addUser(User user) {
        System.out.println("User added: " + user.getName());
    }
}