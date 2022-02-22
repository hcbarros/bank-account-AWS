package br.com.bankaccountapi.enums;

public enum Flag {

    MASTERCARD("Mastercard"),
    VISA("Visa"),
    ELO("Elo");

    private String name;

    Flag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
