DROP TABLE `person`;

CREATE TABLE person (
  id mediumint(8) NOT NULL,
  name varchar(255) NULL,
  PRIMARY KEY (id)
);

insert into t_account (id, company_name) values ('5ac90306-5796-4897-b749-84b78b07bcda', 'Google');
insert into t_balance(id, balance, mcc) values ('17735be9-e8f0-4a16-8d6a-cabb67294cf1', '100', 'MEAL');
insert into t_balance(id, balance, mcc) values ('a6d7da6b-c3ce-4c64-ba5e-91bf72aa0009', '100', 'FOOD');
insert into t_balance(id, balance, mcc) values ('59e8b040-cba0-4ef7-a86b-c1730271ad35', '100', 'CASH');
insert into t_account_balance (account_id, balance_id) values ('5ac90306-5796-4897-b749-84b78b07bcda', '17735be9-e8f0-4a16-8d6a-cabb67294cf1');
insert into t_account_balance (account_id, balance_id) values ('5ac90306-5796-4897-b749-84b78b07bcda', 'a6d7da6b-c3ce-4c64-ba5e-91bf72aa0009');
insert into t_account_balance (account_id, balance_id) values ('5ac90306-5796-4897-b749-84b78b07bcda', '59e8b040-cba0-4ef7-a86b-c1730271ad35');

