insert into mydb.user 
(`username`, `password`, `email`, `account`) values
('user1', 'mdp123dsfe!', 'user1@gmail.com', 0 ),
('user2', 'mdp123dsfe!', 'user2@gmail.com', 10.50 );
insert into mydb.enterprise
(`name`, `siret`) values
('PayMyBuddy', 'FAKESIRET');

insert into mydb.account
(`iban`, `bic`, `accountnumber`, `amount`, `id_user`, `id_enterprise`) values
(34,'TESTBIC', 'ABCDEFGHIJ', 1000.00, NULL,1);

insert into mydb.role (`role`) values ('ROLE_USER'), ('ROLE_ADMIN');
