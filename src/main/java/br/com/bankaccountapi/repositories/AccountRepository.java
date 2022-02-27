package br.com.bankaccountapi.repositories;

import br.com.bankaccountapi.enums.Flag;
import br.com.bankaccountapi.models.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    boolean existsByAccountCode(String eccountCode);
}
