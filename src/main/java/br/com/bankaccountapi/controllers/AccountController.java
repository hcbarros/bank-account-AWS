package br.com.bankaccountapi.controllers;

import br.com.bankaccountapi.models.Account;
import br.com.bankaccountapi.models.Card;
import br.com.bankaccountapi.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
        return new ResponseEntity<>(ac, HttpStatus.CREATED);
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

    //    @PostMapping
//    public ResponseEntity<Cliente> criaCliente(@RequestBody Cliente cliente) {
//        Cliente cli = clienteService.create(cliente);
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(cli.getId())
//                .toUri();
//        return ResponseEntity.created(location).build();
//    }


    @ApiOperation(value="Delete an account")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}