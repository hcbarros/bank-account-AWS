package br.com.bankaccountapi.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "[a-zA-Z ]{1,50}",message="")
    private String nameOwner;

    @Pattern(regexp = "\\d{1,4}",message="Only digits on agency code!")
    private String agencyCode;

    @Pattern(regexp = "\\d{1,8}",message="Only digits on account code!")
    private String accountCode;

    @Pattern(regexp = "\\d{0,1}",message="Only digits on verification digital!")
    private String verificationDigital;

    public Account() { }

    public Account(String nameOwner, String agencyCode, String accountCode, String verificationDigital) {
        this.nameOwner = nameOwner;
        this.agencyCode = agencyCode;
        this.accountCode = accountCode;
        this.verificationDigital = verificationDigital;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
