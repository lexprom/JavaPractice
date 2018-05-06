package com.pak.Model;

public class Client {
    private static int count = 0;
    private int ID_client;
    private String name;
    private String password;
    private double bankAccount;
    private double amountMoney;

    public Client() { }

    public Client(String name,String password,double bankAccount,double amountMoney)
    {
        ID_client = ++count;
        this.name = name;
        this.password = password;
        this.bankAccount = bankAccount;
        this.amountMoney = amountMoney;
    }

    public String getName() {
        return name;
    }

    public double getAmountMoney() {
        return amountMoney;
    }

    public double getBankAccount() {
        return bankAccount;
    }

    public int getID_client() {
        return ID_client;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString()
    {
        return getName() + " " + getPassword() + " " + getAmountMoney() + " " + getBankAccount() + "\n";
    }
}
