package com.orient.unitech.controller;


import com.orient.unitech.model.CardDto;
import com.orient.unitech.model.LoginDto;
import com.orient.unitech.repository.AccountRepository;
import com.orient.unitech.service.AccountService;
import com.orient.unitech.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping(value="/all")
    public List<Account> findAll(){
        List<Account> accounts = accountService.findAll();
        return accounts;
    }

    @GetMapping(value = "/{id}")
    public Account findById(@PathVariable Long id){
        Account account = accountService.findById(id);
        return account;
    }

    @PostMapping(value = "/create")
    public void create(@RequestBody Account account) throws Exception {
        accountService.create(account);

    }

    @GetMapping(value = "/exist")
    public boolean exist(@RequestParam("pin") String pin) {
        return accountService.isExistPin(pin);
    }
    @PutMapping(value = "/update/{id}")
    public void update(@PathVariable Long id,@RequestBody Account account){
        accountService.update(id,account);
    }

    @PatchMapping(value = "/update/{id}")
    public void update2(@PathVariable Long id,@RequestParam("password") String password){
        Account account = accountService.findById(id);
        account.setPassword(password);
        accountRepository.save(account);
    }
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id){
        accountService.delete(id);
    }
    @GetMapping(value = "/active-list")
    public List<Account> getActiveAccount(){
        List<Account> accounts = accountService.listActiveAccount();
        return accounts;
    }
    @PostMapping("/card-to-card")
    public void cardToCard(@RequestBody CardDto dataTransfer) throws Exception {
        accountService.c2c(dataTransfer.getIdFrom(),dataTransfer.getIdTo(), dataTransfer.getCost());
    }

    @GetMapping("/login")
    public Account login(@RequestBody LoginDto loginDto) throws Exception {
        return accountService.login(loginDto.getPin(),loginDto.getPassword());
    }
}
