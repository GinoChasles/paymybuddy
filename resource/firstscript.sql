-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema paymybuddy
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema paymybuddy
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `paymybuddy` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema paymybuddy
-- -----------------------------------------------------
USE `paymybuddy` ;

-- -----------------------------------------------------
-- Table `paymybuddy`.`bankAccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`bankAccount` (
  `idBankAccount` INT NOT NULL,
  `iban` INT NOT NULL,
  `bic` VARCHAR(45) NOT NULL,
  `accountNumber` VARCHAR(45) NOT NULL,
  `key` INT NOT NULL,
  `amount` DOUBLE NOT NULL,
  PRIMARY KEY (`idBankAccount`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymybuddy`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`user` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `account` DOUBLE NULL,
  `idBankAccount` INT NOT NULL,
  PRIMARY KEY (`idUser`),
  INDEX `fk_user_bankAccount1_idx` (`idBankAccount` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  CONSTRAINT `fk_user_bankAccount1`
    FOREIGN KEY (`idBankAccount`)
    REFERENCES `paymybuddy`.`bankAccount` (`idBankAccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymybuddy`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`transaction` (
  `idTransaction` INT NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `amount` DOUBLE NOT NULL,
  `idEmitter` INT NOT NULL,
  `idReceiver` INT NOT NULL,
  PRIMARY KEY (`idTransaction`),
  INDEX `fk_transaction_user1_idx` (`idEmitter` ASC),
  INDEX `fk_transaction_user2_idx` (`idReceiver` ASC),
  CONSTRAINT `fk_transaction_user1`
    FOREIGN KEY (`idEmitter`)
    REFERENCES `paymybuddy`.`user` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction_user2`
    FOREIGN KEY (`idReceiver`)
    REFERENCES `paymybuddy`.`user` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymybuddy`.`enterprise`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`enterprise` (
  `idEnterprise` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(55) NOT NULL,
  `idBankAccount` INT NOT NULL,
  `siret` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEnterprise`),
  INDEX `fk_entreprise_bankAccount1_idx` (`idBankAccount` ASC),
  CONSTRAINT `fk_entreprise_bankAccount1`
    FOREIGN KEY (`idBankAccount`)
    REFERENCES `paymybuddy`.`bankAccount` (`idBankAccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymybuddy`.`user_has_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`user_has_user` (
  `idUser` INT NOT NULL,
  `idUserFriend` INT NOT NULL,
  PRIMARY KEY (`idUser`, `idUserFriend`),
  INDEX `fk_user_has_user_user1_idx` (`idUserFriend` ASC),
  INDEX `fk_user_has_user_user_idx` (`idUser` ASC),
  CONSTRAINT `fk_user_has_user_user`
    FOREIGN KEY (`idUser`)
    REFERENCES `paymybuddy`.`user` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_user1`
    FOREIGN KEY (`idUserFriend`)
    REFERENCES `paymybuddy`.`user` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymybuddy`.`commission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`commission` (
  `idCommission` INT NOT NULL AUTO_INCREMENT,
  `pourcentage` DOUBLE NOT NULL,
  `idUser` INT NOT NULL,
  `idEnterprise` INT NOT NULL,
  `transaction_idTransaction` INT NOT NULL,
  PRIMARY KEY (`idCommission`),
  INDEX `fk_commission_user1_idx` (`idUser` ASC),
  INDEX `fk_commission_entreprise1_idx` (`idEnterprise` ASC),
  INDEX `fk_commission_transaction1_idx` (`transaction_idTransaction` ASC),
  CONSTRAINT `fk_commission_user1`
    FOREIGN KEY (`idUser`)
    REFERENCES `paymybuddy`.`user` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_commission_entreprise1`
    FOREIGN KEY (`idEnterprise`)
    REFERENCES `paymybuddy`.`enterprise` (`idEnterprise`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_commission_transaction1`
    FOREIGN KEY (`transaction_idTransaction`)
    REFERENCES `paymybuddy`.`transaction` (`idTransaction`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
