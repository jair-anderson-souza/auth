package io.github.jairandersonsouza.authorizer.repository;

import io.github.jairandersonsouza.authorizer.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
//TODO
//validar jdbc
public class TransacaoRePo {

    @Autowired
            private JdbcTemplate jdbcTemplate;

    public void insert(Transaction transaction){
//        tran.setId(UUID.randomUUID().toString());
//        tran.setAccountId(transactionInput.getAccount());
//        tran.setAmount(transactionInput.getTotalAmount());
//        tran.setMerchant(transactionInput.getMerchant());
//        tran.setMcc(transactionInput.getMcc());

        var sql = "INSERT INTO t_transaction(id, account_id, amount, merchant, mcc, id) values(?,?,?,?,?)";
        jdbcTemplate.update(sql, transaction.getId(), transaction.getAccountId(), transaction.getAmount(), transaction.getMerchant(), transaction.getMcc());
    }


}
