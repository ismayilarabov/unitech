package com.orient.unitech.service.impl;

import com.orient.unitech.model.Account;
import com.orient.unitech.model.Status;
import com.orient.unitech.model.exception.ErrorCodeEnum;
import com.orient.unitech.model.exception.RestException;
import com.orient.unitech.repository.AccountRepository;
import com.orient.unitech.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {

        Account account = null;
        try {
            account = accountRepository.findById(id).get();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RestException(ErrorCodeEnum.USER_NOT_FOUND);
        }
        return account;
    }

    @Override
    public void create(Account account) throws Exception {
        if (isExistPin(account.getPin())){
            throw new RestException(ErrorCodeEnum.PIN_ALREADY_EXISTS);
        }
        accountRepository.save(account);
    }

    @Override
    public void update(Long id, Account account) {
        try {
            Account updateAccount = accountRepository.findById(id).get();
            updateAccount.setPin(account.getPin());
            updateAccount.setPassword(account.getPassword());
            updateAccount.setFirstName(account.getFirstName());
            updateAccount.setLastName(account.getLastName());
            updateAccount.setEmail(account.getEmail());
            updateAccount.setBalance(account.getBalance());
            updateAccount.setStatus(account.getStatus());

            accountRepository.save(updateAccount);
        }catch (NoSuchElementException e){
            throw new RestException(ErrorCodeEnum.No_value_present);
        }

    }

    @Override
    public void delete(Long id) {
        try {
            Account account = accountRepository.findById(id).get();
            accountRepository.delete(account);
        }catch (NoSuchElementException e){
            throw new RestException(ErrorCodeEnum.No_value_present);
        }
    }

    @Override
    public Account login(String pin, String password){
        Account account = accountRepository.findByPinAndPasswordAndStatus(pin, password,Status.ACTIVE);
        if (account == null) {
            throw new RestException(ErrorCodeEnum.PIN_OR_PASSWORD_WRONG_OR_DEACTIVE);
        }
        return account;
    }

    @Override
    public boolean isExistPin(String pin) {
        return accountRepository.existsByPin(pin);
    }

    @Override
    public List<Account> listActiveAccount() {
        List<Account> accountList = accountRepository.findByStatus(Status.ACTIVE);
        return accountList;
    }

    @Override
    public void c2c(Long idFrom, Long idTo, double cost) throws Exception {
        try {
            Account accountFrom = accountRepository.findById(idFrom).get();
            Account accountTo = accountRepository.findById(idTo).get();
            if (accountFrom.getBalance() >= cost) {
                accountFrom.setBalance(accountFrom.getBalance() - cost);
                accountTo.setBalance(accountTo.getBalance() + cost);
                accountRepository.save(accountFrom);
                accountRepository.save(accountTo);
            } else {
                System.out.println("balance is not enough");
                throw new RestException(ErrorCodeEnum.BALANCE_NOT_ENOUGH);

            }
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            throw new RestException(ErrorCodeEnum.No_value_present);
        }
    }

}
