package br.com.bankaccountapi.enums;

public enum TypeCard {

    DEBIT_CARD("Debit card"),
    CREDIT_CARD("Credit card"),
    MEAL_CARD("Real card"),
    GIFT_CARD("Gift card");

    private String name;

    TypeCard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
