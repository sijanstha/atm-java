package com.sample;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private String name;
    private String uuid;
    private User holder;
    private List<Transaction> transactions;

    public Account(String name, User holder, Bank theBank) {
        this.name = name;
        this.holder = holder;

        this.uuid = theBank.getNewAccountUUID();
        this.transactions = new ArrayList<>();

    }

    public String getUUID() {
        return this.uuid;
    }

    public String getSummaryLine(){
        double balance = this.getBalance();

        if (balance >=0)
            return String.format("%s : $%.02f : %s", uuid, balance,name);
        else
            return String.format("%s : $(%.02f) : %s", uuid, balance,name);

    }

    private double getBalance() {
        double balance = 0;
        for (Transaction t : transactions) {
            balance += t.getAmount();
        }
        return balance;
    }
}
