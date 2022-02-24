package br.com.bankaccountapi.repositories;

import br.com.bankaccountapi.enums.Flag;
import br.com.bankaccountapi.models.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

//    @Query(value = "select a.count(*) from Account a inner join Card c on  number = ?1 and flag = ?2)", nativeQuery = true)
//    long countByNumberAndFlag(String number, Flag flag);

}
