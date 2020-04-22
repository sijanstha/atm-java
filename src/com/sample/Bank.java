package com.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank {

    private String name;
    private List<User> users;
    private List<Account> accounts;

    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    public String getNewUserUUID() {
        String uuid;
        Random random = new Random();
        int len = 6;
        boolean nonUnique = false;

        do {
            // generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer) random.nextInt(10)).toString();
            }

            // check to make sure it's unique
            for (User u : users) {
                if (uuid.compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                }
            }
        } while (nonUnique);

        return uuid;
    }

    public String getNewAccountUUID() {
        String uuid;
        Random random = new Random();
        int len = 10;
        boolean nonUnique = false;

        do {
            // generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer) random.nextInt(10)).toString();
            }

            // check to make sure it's unique
            for (Account a : accounts) {
                if (uuid.compareTo(a.getUUID()) == 0) {
                    nonUnique = true;
                }
            }
        } while (nonUnique);

        return uuid;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public User addUser(String fname, String lname, String pin) {
        User newUser = new User(fname, lname, pin, this);
        this.users.add(newUser);

        // create a savings account for the user.
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }

    public User userLogin(String userId, String pin) {
        for (User u : users) {
            if (u.getUUID().compareTo(userId) == 0 && u.validatePin(pin)) {
                return u;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }
}
