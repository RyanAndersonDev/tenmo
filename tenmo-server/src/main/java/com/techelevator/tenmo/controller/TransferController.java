package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.exception.BalanceException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

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
    public boolean sendTransfer(@RequestBody TransferDto transferDto, Principal principal) {

        int principalUserId = userDao.findIdByUsername(principal.getName());
        Account principalAccount = accountDao.getAccountByUserId(principalUserId);
        int principalAccountId = principalAccount.getAccountId();

        int accountToUserId = transferDto.getAccountToUserId();
        Account recipientAccount = accountDao.getAccountByUserId(accountToUserId);
        int recipientAccountId = recipientAccount.getAccountId();

        BigDecimal transferAmount = transferDto.getAmount();

        if(principalAccount.getBalance().compareTo(transferAmount) >= 0 && transferAmount.compareTo(new BigDecimal("0")) > 0) {
            transferDao.createTransfer(false, principalAccountId, recipientAccountId, transferAmount);
            transferDao.updateSenderBalance(transferAmount, principalAccountId);
            transferDao.updateRecipientBalance(transferAmount, recipientAccountId);
        } else {
            throw new BalanceException("Invalid funds");
        }

        // Add better exception handling later ??
        return true;
    }

    @RequestMapping(path = "/transfers", method = RequestMethod.GET)
    public List<Transfer> listTransfers(Principal principal){
        int principalUserId = userDao.findIdByUsername(principal.getName());
        Account principalAccount = accountDao.getAccountByUserId(principalUserId);
        int principalAccountId = principalAccount.getAccountId();
        return transferDao.getTransferList(principalAccountId);
    }
}
