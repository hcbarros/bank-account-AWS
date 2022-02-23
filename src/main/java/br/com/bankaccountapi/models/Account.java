package br.com.bankaccountapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Pattern(regexp = "[a-zA-Z ]{1,50}",message="")
    private String nameOwner;

    @Pattern(regexp = "\\d{1,4}",message="Only digits on agency code!")
    private String agencyCode;

    @Pattern(regexp = "\\d{1,8}",message="Only digits on account code!")
    private String accountCode;

    @Pattern(regexp = "\\d{1}",message="Only digits on verification digital!")
    private String verificationDigital;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    // @JoinColumn(name = "processo_reus_id")
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

}
