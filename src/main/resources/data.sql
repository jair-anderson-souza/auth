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


insert into t_account_balance(id, account_id, balance, mcc, company_name) values ('88cae9b9-eedb-48ca-a2b4-4fcffa47714f', '5ac90306-5796-4897-b749-84b78b07bcda', '100000', 'MEAL', 'Google');
insert into t_account_balance(id, account_id, balance, mcc, company_name) values ('811cd728-c4cd-460b-992b-4136edce9508', '5ac90306-5796-4897-b749-84b78b07bcda', '100000', 'CASH', 'Google');
insert into t_account_balance(id, account_id, balance, mcc, company_name) values ('5ac90306-5796-4897-b749-84b78b07bcda', '5ac90306-5796-4897-b749-84b78b07bcda', '100000', 'FOOD', 'Google');

insert into t_account_balance(id, account_id, balance, mcc, company_name) values ('52a235b8-e78f-4fcd-9e20-53952b710843', '3f94fe6e-7076-441a-b837-a46d5f75b499', '100000', 'MEAL', 'XP');
insert into t_account_balance(id, account_id, balance, mcc, company_name) values ('34af7c13-4927-4651-bcc6-9026c5ed5afb', '3f94fe6e-7076-441a-b837-a46d5f75b499', '100000', 'CASH', 'XP');
insert into t_account_balance(id, account_id, balance, mcc, company_name) values ('2a8a0227-5add-4266-a112-09d196fcd190', '3f94fe6e-7076-441a-b837-a46d5f75b499', '100000', 'FOOD', 'XP');

insert into t_account_balance(id, account_id, balance, mcc, company_name) values ('9f12023a-abdf-46e5-a089-3a59d1cef760', '026b36f0-8454-4af4-b223-f94fa6bc3568', '100000', 'MEAL', 'Caju');
insert into t_account_balance(id, account_id, balance, mcc, company_name) values ('afa09a10-8c4a-4bfc-a7c0-82e6a9cdc388', '026b36f0-8454-4af4-b223-f94fa6bc3568', '100000', 'CASH', 'Caju');
insert into t_account_balance(id, account_id, balance, mcc, company_name) values ('58b69a53-040c-4bb7-8559-b4ba2ae2c671', '026b36f0-8454-4af4-b223-f94fa6bc3568', '100000', 'FOOD', 'Caju');