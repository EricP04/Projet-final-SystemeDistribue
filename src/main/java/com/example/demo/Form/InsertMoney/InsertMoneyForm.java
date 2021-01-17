package com.example.demo.Form.InsertMoney;


import com.example.demo.Entity.Customer;

public class InsertMoneyForm {
    private String numAccount;
    private float amountAsk;

    public InsertMoneyForm()
    {

    }

    public InsertMoneyForm(String numAccount, float amountAsk) {
        this.numAccount = numAccount;
        this.amountAsk = amountAsk;
    }

    public String getNumAccount() {
        return numAccount;
    }

    public void setNumAccount(String numAccount) {
        this.numAccount = numAccount;
    }

    public float getAmountAsk() {
        return amountAsk;
    }

    public void setAmountAsk(float amountAsk) {
        this.amountAsk = amountAsk;
    }
}
