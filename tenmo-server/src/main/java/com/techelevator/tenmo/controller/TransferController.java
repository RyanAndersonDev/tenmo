package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.TransferDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
public class TransferController {

    private TransferDao transferDao;
    private AccountDao accountDao;
    private UserDao userDao;

    public TransferController(TransferDao transferDao, AccountDao accountDao, UserDao userDao){
        this.transferDao = transferDao;
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/pay", method = RequestMethod.POST)
    public void sendTransfer(@RequestBody TransferDto transferDto, Principal principal){

        int principalUserId = userDao.findIdByUsername(principal.getName());
        Account pricipalAccount = accountDao.getAccountByUserId(principalUserId);
        int principalAccountId = pricipalAccount.getAccountId();

        int accountToId = transferDto.getAccountToId();
        BigDecimal transferAmount = transferDto.getAmount();

        transferDao.createTransfer(false, principalAccountId, accountToId, transferAmount);
        transferDao.updateSenderBalance(transferAmount, accountToId);
        transferDao.updateRecipientBalance(transferAmount, principalAccountId);
    }
  //  implement
}
