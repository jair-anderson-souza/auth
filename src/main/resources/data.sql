DROP TABLE IF EXISTS t_account_balance;
DROP TABLE IF EXISTS t_account;
DROP TABLE IF EXISTS t_transaction;

CREATE TABLE IF NOT EXISTS t_account(
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    company_name varchar(36) NOT NULL
);

CREATE TABLE IF NOT EXISTS t_account_balance (
    account_id VARCHAR(36) NOT NULL CONSTRAINT account_balance_pkey REFERENCES t_account,
    balance NUMERIC(10, 2)  NOT NULL,
    mcc VARCHAR(36) NOT NULL
);

CREATE TABLE IF NOT EXISTS t_transaction(
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    account_id varchar(36) NOT NULL,
    mcc varchar(36) NOT NULL,
    amount NUMERIC(10,2) NOT NULL,
    merchant varchar(36) NOT NULL
);

insert into t_account (id, company_name) values ('5ac90306-5796-4897-b749-84b78b07bcda', 'Google');
insert into t_account (id, company_name) values ('3f94fe6e-7076-441a-b837-a46d5f75b499', 'XP');

insert into t_account_balance(account_id, balance, mcc) values ( '5ac90306-5796-4897-b749-84b78b07bcda', '100', 'MEAL');
insert into t_account_balance(account_id, balance, mcc) values ( '5ac90306-5796-4897-b749-84b78b07bcda', '100', 'CASH');
insert into t_account_balance(account_id, balance, mcc) values (  '5ac90306-5796-4897-b749-84b78b07bcda', '100', 'FOOD');

insert into t_account_balance(account_id, balance, mcc) values ( '3f94fe6e-7076-441a-b837-a46d5f75b499', '300', 'MEAL');
insert into t_account_balance(account_id, balance, mcc) values ( '3f94fe6e-7076-441a-b837-a46d5f75b499', '300', 'CASH');
insert into t_account_balance(account_id, balance, mcc) values (  '3f94fe6e-7076-441a-b837-a46d5f75b499', '300', 'FOOD');