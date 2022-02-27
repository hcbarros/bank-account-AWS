package br.com.bankaccountapi.exceptions;

public class ExistsCardAssociatedWithAccount extends RuntimeException {

    public ExistsCardAssociatedWithAccount() {
        super("It is not possible to delete this account. There is still a card associated with it.!");
    }
}
