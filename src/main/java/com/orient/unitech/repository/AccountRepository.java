package com.orient.unitech.repository;

import com.orient.unitech.model.Status;
import com.orient.unitech.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findByStatus(Status status);

    Account findByPinAndPasswordAndStatus(String pin,String password,Status status);

    boolean  existsByPin(String pin);
}
