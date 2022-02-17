insert into mydb.enterprise
(`name`, `siret`) values
('PayMyBuddy', 'FAKESIRET');

insert into mydb.account
(`iban`, `bic`, `accountnumber`, `amount`, `id_user`, `id_enterprise`) values
(34,'TESTBIC', 'ABCDEFGHIJ', 1000.00, NULL,1);

insert into mydb.role (`role`) values ('ROLE_USER'), ('ROLE_ADMIN');
