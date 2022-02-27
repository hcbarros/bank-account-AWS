package br.com.bankaccountapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "NameOwner is mandatory")
    @Pattern(regexp = "[a-zA-Z ]{1,50}",message="Name owner must be between 1 and 50 letters.")
    private String nameOwner;

    @NotNull(message = "AgencyCode is mandatory")
    @Pattern(regexp = "\\d{4}",message="agency code must be 4 digits!")
    private String agencyCode;

    @NotNull(message = "AccountCode is mandatory")
    @Pattern(regexp = "\\d{8}",message="Account code must be 8 digits!")
    private String accountCode;

    @NotNull(message = "VerificationDigital is mandatory")
    @Pattern(regexp = "\\d{1}",message="Verification digital must be 8 digits!")
    private String verificationDigital;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private List<Card> cards;


    public Account() {
        cards = new ArrayList<>();
    }

    public Account(String nameOwner, String agencyCode, String accountCode,
                   String verificationDigital, List<Card> cards) {
        this.nameOwner = nameOwner;
        this.agencyCode = agencyCode;
        this.accountCode = accountCode;
        this.verificationDigital = verificationDigital;
        this.cards = cards == null ? new ArrayList<>() : cards;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getVerificationDigital() {
        return verificationDigital;
    }

    public void setVerificationDigital(String verificationDigital) {
        this.verificationDigital = verificationDigital;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean removeCard(Card card) {
        return cards.remove(card);
    }

}
