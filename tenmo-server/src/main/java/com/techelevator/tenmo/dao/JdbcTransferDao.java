package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;

@Component
public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;


    public JdbcTransferDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }



    public void updateRecipientBalance(BigDecimal transferAmount, int accountId) {
        String sql = "UPDATE account " +
            "SET balance = balance + ? " +
            "WHERE account_id = ?;";
        jdbcTemplate.update(sql, transferAmount, accountId);
    }

    public void updateSenderBalance(BigDecimal transferAmount, int accountId) {
        String sql = "UPDATE account " +
                "SET balance = balance - ? " +
                "WHERE account_id = ?;";
        jdbcTemplate.update(sql, transferAmount, accountId);
    }

    public void createTransfer(boolean isRequest, int accountFromId, int accountToId, BigDecimal amount) {
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (?,?,?,?,?); ";
        int transferTypeId = 2;
        int transferStatusId = 2;
        if(isRequest){
            transferTypeId = 1;
            transferStatusId = 1;
        }
       jdbcTemplate.update(sql, transferTypeId, transferStatusId, accountFromId, accountToId, amount);
    }


    public void requestTransfer() {

    }

    public Transfer mapRowToTransfer(SqlRowSet rowSet){
        Transfer transfer = new Transfer();
        transfer.setTransferId(rowSet.getInt("transfer_id"));
        transfer.setTransferTypeId(rowSet.getInt("transfer_type_id"));
        transfer.setTransferStatusId(rowSet.getInt("transfer_status_id"));
        transfer.setAccountFrom(rowSet.getInt("account_from"));
        transfer.setAccountTo(rowSet.getInt("account_to"));
        transfer.setAmount(rowSet.getBigDecimal("amount"));
        return transfer;
    }
}
