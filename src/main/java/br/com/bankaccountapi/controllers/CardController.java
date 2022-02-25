package br.com.bankaccountapi.controllers;

import br.com.bankaccountapi.models.Account;
import br.com.bankaccountapi.models.Card;
import br.com.bankaccountapi.repositories.CardRepository;
import br.com.bankaccountapi.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/cards")
public class CardController {

    @Autowired
    private CardService service;

    @GetMapping("{id}")
    public ResponseEntity<Card> findById(@PathVariable("id") Integer id) {
        Card card = service.findById(id);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Card>> findAll() {
        List<Card> cards = service.findAll();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
