package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
public class UserController {

    @Autowired
    private AccountDao accountDao;

    public UserController(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    @RequestMapping(path = "/balance/{id}", method = RequestMethod.GET)
    public BigDecimal balance(@Valid @PathVariable int id){
        BigDecimal balance = null;
        balance = accountDao.getBalance(id);
        return balance;
    }

}
