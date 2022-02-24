package br.com.bankaccountapi.repositories;

import br.com.bankaccountapi.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {

}
