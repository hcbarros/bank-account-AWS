package br.com.bankaccountapi.services;

import br.com.bankaccountapi.models.Account;
import br.com.bankaccountapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account save(Account account) {
        return repository.save(account);
    }

    public Account edit(Integer id, Account account) {
        Account ac = findById(id);
        ac.setCards(account.getCards());
        ac.setAccountCode(account.getAccountCode());
        ac.setAgencyCode(account.getAgencyCode());
        ac.setNameOwner(account.getNameOwner());
        ac.setVerificationDigital(account.getVerificationDigital());
        return save(ac);
    }

    public Account findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found!"));
    }

    public List<Account> findAll() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }


}
