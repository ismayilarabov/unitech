package com.orient.unitech.service;


import com.orient.unitech.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    List<Account> findAll();

    Account findById(Long id);

    void create(Account account) throws Exception;

    void update(Long id,Account account);

    void delete(Long id);

    Account login(String pin,String password) throws Exception;

    boolean isExistPin(String pin);

    List<Account> listActiveAccount();

    void c2c(Long idFrom, Long idTo, double cost) throws Exception;
}
