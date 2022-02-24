package br.com.bankaccountapi.controllers;

import br.com.bankaccountapi.models.Account;
import br.com.bankaccountapi.models.Card;
import br.com.bankaccountapi.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/card")
public class CardController {

    @Autowired
    private CardRepository repository;

    @GetMapping("{id}")
    public ResponseEntity<Card> findById(@PathVariable("id") Integer id) {
        Card card = repository.findById(id).orElse(null);
        return new ResponseEntity(card, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Card>> findAll() {
        List<Card> cards = repository.findAll();
        return new ResponseEntity(cards, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Account> save(@RequestBody Card card) {
        Card resp = repository.save(card);
        return new ResponseEntity(resp, HttpStatus.CREATED);
    }




}
