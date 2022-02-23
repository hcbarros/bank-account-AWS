package br.com.bankaccountapi.models;

import br.com.bankaccountapi.enums.Flag;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Pattern(regexp = "[a-zA-Z ]{1,128}",message="")
    private String name;

    private Flag flag;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tyoe_id", referencedColumnName = "id")
    private Type typeCard;

    @Pattern(regexp = "\\d{4}\\.\\d{4}\\. \\d{4}\\.\\d{4}",message="")
    private String number;

    @Pattern(regexp = "\\d{2,5}",message="Digit code should be max size 5 and min size 2!")
    private String digitCode;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=20, fraction=2)
    private BigDecimal limitBalance;

    @ManyToOne
    private Account account;

    public Card() { }

    public Card(String name, Flag flag, String number, String digitCode, BigDecimal limitBalance) {
        this.name = name;
        this.flag = flag;
        this.number = number;
        this.digitCode = digitCode;
        this.limitBalance = limitBalance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDigitCode() {
        return digitCode;
    }

    public void setDigitCode(String digitCode) {
        this.digitCode = digitCode;
    }

    public BigDecimal getLimitBalance() {
        return limitBalance;
    }

    public void setLimitBalance(BigDecimal limitBalance) {
        this.limitBalance = limitBalance;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

}
