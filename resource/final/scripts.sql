-- MySQL Script generated by MySQL Workbench
-- Tue Apr  5 11:11:43 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`enterprise`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`enterprise` (
  `id_enterprise` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(55) NOT NULL,
  `siret` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_enterprise`),
  UNIQUE INDEX `idEnterprise_UNIQUE` (`id_enterprise` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `account` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `idUser_UNIQUE` (`id_user` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`account` (
  `id_account` INT NOT NULL AUTO_INCREMENT,
  `iban` INT NOT NULL,
  `bic` VARCHAR(45) NOT NULL,
  `accountnumber` VARCHAR(45) NOT NULL,
  `amount` DECIMAL(10,2) NULL DEFAULT NULL,
  `id_user` INT NULL DEFAULT NULL,
  `id_enterprise` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_account`),
  UNIQUE INDEX `idBankAccount_UNIQUE` (`id_account` ASC),
  INDEX `fk_account_user1_idx` (`id_user` ASC),
  INDEX `fk_account_enterprise1_idx` (`id_enterprise` ASC),
  CONSTRAINT `fk_account_enterprise1`
    FOREIGN KEY (`id_enterprise`)
    REFERENCES `mydb`.`enterprise` (`id_enterprise`),
  CONSTRAINT `fk_account_user1`
    FOREIGN KEY (`id_user`)
    REFERENCES `mydb`.`user` (`id_user`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`transaction` (
  `id_transaction` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NOT NULL,
  `amount` DECIMAL(10,2) NULL DEFAULT NULL,
  `id_emitter` INT NOT NULL,
  `id_receiver` INT NOT NULL,
  PRIMARY KEY (`id_transaction`),
  UNIQUE INDEX `idTransaction_UNIQUE` (`id_transaction` ASC),
  INDEX `fk_transaction_user2_idx` (`id_emitter` ASC),
  INDEX `fk_transaction_user1_idx` (`id_receiver` ASC),
  CONSTRAINT `fk_transaction_user1`
    FOREIGN KEY (`id_receiver`)
    REFERENCES `mydb`.`user` (`id_user`),
  CONSTRAINT `fk_transaction_user2`
    FOREIGN KEY (`id_emitter`)
    REFERENCES `mydb`.`user` (`id_user`))
ENGINE = InnoDB
AUTO_INCREMENT = 37
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`commission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`commission` (
  `id_commission` INT NOT NULL AUTO_INCREMENT,
  `pourcentage` DOUBLE NOT NULL,
  `id_enterprise` INT NOT NULL,
  `id_transaction` INT NOT NULL,
  `count` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id_commission`),
  UNIQUE INDEX `idCommission_UNIQUE` (`id_commission` ASC),
  INDEX `fk_commission_entreprise1_idx` (`id_enterprise` ASC),
  INDEX `fk_commission_transaction1_idx` (`id_transaction` ASC),
  CONSTRAINT `fk_commission_entreprise1`
    FOREIGN KEY (`id_enterprise`)
    REFERENCES `mydb`.`enterprise` (`id_enterprise`),
  CONSTRAINT `fk_commission_transaction1`
    FOREIGN KEY (`id_transaction`)
    REFERENCES `mydb`.`transaction` (`id_transaction`))
ENGINE = InnoDB
AUTO_INCREMENT = 37
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`role` (
  `id_role` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `role` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_role`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`user_has_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user_has_user` (
  `id_user` INT NOT NULL,
  `id_friend` INT NOT NULL,
  PRIMARY KEY (`id_user`, `id_friend`),
  INDEX `fk_user_has_user_user1_idx` (`id_friend` ASC),
  INDEX `fk_user_has_user_user_idx` (`id_user` ASC),
  CONSTRAINT `fk_user_has_user_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `mydb`.`user` (`id_user`),
  CONSTRAINT `fk_user_has_user_user1`
    FOREIGN KEY (`id_friend`)
    REFERENCES `mydb`.`user` (`id_user`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user_role` (
  `id_user` INT NOT NULL,
  `id_role` INT NOT NULL,
  INDEX `FK2aam9nt2tv8vcfymi3jo9c314` (`id_role` ASC),
  INDEX `FKfhxaael2m459kbk8lv8smr5iv` (`id_user` ASC),
  CONSTRAINT `FK2aam9nt2tv8vcfymi3jo9c314`
    FOREIGN KEY (`id_role`)
    REFERENCES `mydb`.`role` (`id_role`),
  CONSTRAINT `FKfhxaael2m459kbk8lv8smr5iv`
    FOREIGN KEY (`id_user`)
    REFERENCES `mydb`.`user` (`id_user`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
