package br.com.bankaccountapi.models;

import br.com.bankaccountapi.enums.TypeCard;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Type card not found!")
    private TypeCard typeCard;

    public Type() { }

    public Type(TypeCard typeCard) {
        this.typeCard = typeCard;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeCard getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(TypeCard typeCard) {
        this.typeCard = typeCard;
    }
}
