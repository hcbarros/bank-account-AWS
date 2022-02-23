package br.com.bankaccountapi.repositories;

import br.com.bankaccountapi.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
