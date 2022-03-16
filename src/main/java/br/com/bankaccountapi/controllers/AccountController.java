package br.com.bankaccountapi.controllers;

import br.com.bankaccountapi.models.Account;
import br.com.bankaccountapi.models.Card;
import br.com.bankaccountapi.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;
import java.net.URI;
import java.net.http.HttpHeaders;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Api(value="API REST accounts")
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @ApiOperation(value="Return account by Id")
    @GetMapping("{id}")
    public ResponseEntity<Account> findById(
            @PathVariable("id") Integer id) {

        Account account = service.findById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @ApiOperation(value="Return all accounts")
    @GetMapping
    public ResponseEntity<Account> findAll() {

        List<Account> accounts = service.findAll();
        return new ResponseEntity(accounts, HttpStatus.OK);
    }

    @ApiOperation(value="Save account")
    @PostMapping
    public ResponseEntity<Account> save(@Valid @RequestBody Account account) {
        Account ac = service.save(account);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ac.getId())
                .toUri();
        return ResponseEntity.created(location).body(ac);
    }

    @ApiOperation(value="Edit account data, except cards.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Account> edit(@PathVariable("id") Integer id,
                                        @Valid @RequestBody Account account) {
        Account ac = service.edit(id, account);
        return new ResponseEntity<>(ac, HttpStatus.CREATED);
    }

    @ApiOperation(value="Add a card to an account")
    @PutMapping("/addcard/{id}")
    public ResponseEntity<Account> addCard(@PathVariable("id") Integer id,
                                           @Valid @RequestBody Card card) {
        Account ac = service.addCard(id, card);
        return new ResponseEntity<Account>(ac, HttpStatus.OK);
    }

    @ApiOperation(value="Delete an account")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
