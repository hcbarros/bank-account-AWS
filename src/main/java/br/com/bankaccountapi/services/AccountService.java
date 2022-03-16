package br.com.bankaccountapi.services;

import br.com.bankaccountapi.exceptions.ExistsAccountByAccountCode;
import br.com.bankaccountapi.exceptions.ExistsCardAssociatedWithAccount;
import br.com.bankaccountapi.exceptions.ExistsCardByNumberAndFlagException;
import br.com.bankaccountapi.models.Account;
import br.com.bankaccountapi.models.Card;
import br.com.bankaccountapi.repositories.AccountRepository;
import br.com.bankaccountapi.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    public Account save(Account account) {
        boolean exists = accountRepository.existsByAccountCode(account.getAccountCode());
        if(exists) {
            throw new ExistsAccountByAccountCode();
        }
        return accountRepository.save(account);
    }

    public Account edit(Integer id, Account account) {
        Account ac = findById(id);
        boolean exists = accountRepository.existsByAccountCode(account.getAccountCode());
        if(ac.getAccountCode() != account.getAccountCode() && exists) {
            throw new ExistsAccountByAccountCode();
        }
        ac.setAccountCode(account.getAccountCode());
        ac.setAgencyCode(account.getAgencyCode());
        ac.setNameOwner(account.getNameOwner());
        ac.setVerificationDigital(account.getVerificationDigital());
        return accountRepository.save(ac);
    }

    public Account findById(Integer id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No account was found with id "+id));
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account addCard(Integer id, Card card) {
        boolean exists = cardRepository
                .existsByNumberAndType_TypeCard(card.getNumber(), card.getType().getTypeCard());
        if(exists) {
            throw new ExistsCardByNumberAndFlagException();
        }
        Account account = findById(id);
        account.addCard(card);
        return accountRepository.save(account);
    }

    public boolean existsByAccountCode(String accountCode) {
        return accountRepository.existsByAccountCode(accountCode);
    }

    public void delete(Integer id) {
        Account account = findById(id);
        if(!account.getCards().isEmpty()) {
            throw new ExistsCardAssociatedWithAccount();
        }
        accountRepository.deleteById(id);
    }


}
