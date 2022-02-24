package br.com.bankaccountapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TypeCard {

    DEBIT_CARD,
    CREDIT_CARD,
    MEAL_CARD,
    GIFT_CARD;

    @JsonCreator
    public static TypeCard fromString(String name) {
        for(TypeCard type : TypeCard.values()) {
            if(name.toUpperCase().contains(type.name().substring(0, 3))) {
                return type;
            }
        }
        throw new RuntimeException("Type card not found!");
    }

}
