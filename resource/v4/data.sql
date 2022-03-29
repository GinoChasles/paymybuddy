insert into mydbtest.enterprise
(`name`, `siret`) values
('PayMyBuddy', 'FAKESIRET');

insert into mydbtest.account
(`iban`, `bic`, `accountnumber`, `amount`, `id_user`, `id_enterprise`) values
(34,'TESTBIC', 'ABCDEFGHIJ', 1000.00, NULL,1);

insert into mydbtest.role (`role`) values ('ROLE_USER'), ('ROLE_ADMIN');
insert into mydbtest.user (`id_user`,`username`, `password`, `email`, `account`) values (1,'test', 'testtest', 'email@teste.com', 500);
insert into mydbtest.user (`id_user`,`username`, `password`, `email`, `account`) values (2,'test2', 'testtest2', 'email2@teste.com', 500);

insert into mydbtest.transaction
(`description`, `amount`, `id_emitter`, `id_receiver`) values ('description', 10, 1, 2);