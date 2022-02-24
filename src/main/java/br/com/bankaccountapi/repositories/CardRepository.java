package br.com.bankaccountapi.repositories;

import br.com.bankaccountapi.enums.Flag;
import br.com.bankaccountapi.enums.TypeCard;
import br.com.bankaccountapi.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends JpaRepository<Card, Integer> {

    boolean existsByNumberAndType_TypeCard(String number, TypeCard type);
}
