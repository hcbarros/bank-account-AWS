package br.com.bankaccountapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Flag {

    MASTERCARD,
    VISA,
    ELO;

    @JsonCreator
    public static Flag fromString(String name) {
        for(Flag flag : Flag.values()) {
            if(flag.name().equalsIgnoreCase(name)) {
                return flag;
            }
        }
        return null;
    }

}
