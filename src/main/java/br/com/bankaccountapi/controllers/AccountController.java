package br.com.bankaccountapi.controllers;

import br.com.bankaccountapi.models.Account;
import br.com.bankaccountapi.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping("{id}")
    public ResponseEntity<Account> findById(
            @PathVariable("id") Integer id) {

        Account account = service.findById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Account> findAll() {
        List<Account> accounts = service.findAll();
        return new ResponseEntity(accounts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Account> save(@RequestBody Account account) {
        Account ac = service.save(account);
        return new ResponseEntity<>(ac, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Account> edit(@PathVariable("id") Integer id,
                                        @RequestBody Account account) {
        Account ac = service.edit(id, account);
        return new ResponseEntity<>(ac, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}