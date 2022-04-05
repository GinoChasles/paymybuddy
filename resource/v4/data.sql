insert into mydbtest.enterprise
(`name`, `siret`) values
('PayMyBuddy', 'FAKESIRET');
insert into mydbtest.role (`role`) values ('ROLE_USER'), ('ROLE_ADMIN');
insert into mydbtest.user (`id_user`,`username`, `password`, `email`, `account`) values (1,'test', 'testtest', 'email@teste.com', 500);
insert into mydbtest.user (`id_user`,`username`, `password`, `email`, `account`) values (2,'test2', 'testtest2', 'email2@teste.com', 500);
insert into mydbtest.account
(`id_account`,`iban`, `bic`, `accountnumber`, `amount`, `id_user`, `id_enterprise`) values
(1, 34,'TESTBIC', 'ABCDEFGHIJ', 1000.00, NULL,1);
insert into mydbtest.account
(`id_account`,`iban`, `bic`, `accountnumber`, `amount`, `id_user`, `id_enterprise`) values
(2, 35,'TESTBIC2', 'test', 1000.00, 1,NULL);
insert into mydbtest.account
(`id_account`,`iban`, `bic`, `accountnumber`, `amount`, `id_user`, `id_enterprise`) values
(3, 35,'TESTBIC2', 'test', 1000.00, 1,NULL);


insert into mydbtest.transaction
(`description`, `amount`, `id_emitter`, `id_receiver`) values ('description', 10, 1, 2);