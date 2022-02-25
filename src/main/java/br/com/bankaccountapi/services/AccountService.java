package br.com.bankaccountapi.services;

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
        return accountRepository.save(account);
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
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found!"));
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account addCard(Integer id, Card card) {
        boolean exists = cardRepository.existsByNumberAndType_TypeCard(card.getNumber(), card.getType().getTypeCard());
        if(exists) {
            throw new RuntimeException("There is already a card registered with this number and this flag!");
        }
        Account account = findById(id);
        account.addCard(card);
        return save(account);
    }


    public void delete(Integer id) {
        Account account = findById(id);
        if(!account.getCards().isEmpty()) {
            throw new RuntimeException(
                    "It is not possible to delete this account. There is still a card associated with it.!");
        }
        accountRepository.deleteById(id);
    }


}
