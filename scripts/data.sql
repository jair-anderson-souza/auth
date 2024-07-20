DROP TABLE IF EXISTS t_account_balance;
DROP TABLE IF EXISTS t_transaction;

CREATE TABLE IF NOT EXISTS t_account_balance (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    account_id VARCHAR(36) NOT NULL,
    balance NUMERIC(10, 2)  NOT NULL,
    mcc VARCHAR(36) NOT NULL,
    company_name varchar(36) NOT NULL
);

CREATE TABLE IF NOT EXISTS t_transaction(
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    account_id varchar(36) NOT NULL,
    mcc varchar(36) NOT NULL,
    amount NUMERIC(10,2) NOT NULL,
    merchant varchar(36) NOT NULL
);


insert into t_account_balance(id, account_id, balance, mcc, company_name) values ('88cae9b9-eedb-48ca-a2b4-4fcffa47714f', '5ac90306-5796-4897-b749-84b78b07bcda', '100', 'MEAL', 'Google');
insert into t_account_balance(id, account_id, balance, mcc, company_name) values ('811cd728-c4cd-460b-992b-4136edce9508', '5ac90306-5796-4897-b749-84b78b07bcda', '100', 'CASH', 'Google');
insert into t_account_balance(id, account_id, balance, mcc, company_name) values ('5ac90306-5796-4897-b749-84b78b07bcda', '5ac90306-5796-4897-b749-84b78b07bcda', '100', 'FOOD', 'Google');

insert into t_account_balance(id, account_id, balance, mcc, company_name) values ('52a235b8-e78f-4fcd-9e20-53952b710843', '3f94fe6e-7076-441a-b837-a46d5f75b499', '300', 'MEAL', 'XP');
insert into t_account_balance(id, account_id, balance, mcc, company_name) values ('34af7c13-4927-4651-bcc6-9026c5ed5afb', '3f94fe6e-7076-441a-b837-a46d5f75b499', '300', 'CASH', 'XP');
insert into t_account_balance(id, account_id, balance, mcc, company_name) values ('2a8a0227-5add-4266-a112-09d196fcd190', '3f94fe6e-7076-441a-b837-a46d5f75b499', '300', 'FOOD', 'XP');