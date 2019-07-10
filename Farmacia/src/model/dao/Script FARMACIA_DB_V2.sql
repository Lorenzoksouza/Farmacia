DROP DATABASE IF EXISTS FARMACIA_DB;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema FARMACIA_DB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `FARMACIA_DB` DEFAULT CHARACTER SET utf8 ;
USE `FARMACIA_DB` ;

-- -----------------------------------------------------
-- Table `FARMACIA_DB`.`FORMA_USO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FARMACIA_DB`.`FORMA_USO` (
  `ID_FORMA_USO` INT NOT NULL AUTO_INCREMENT,
  `DESCRICAO` VARCHAR(100) NULL,
  PRIMARY KEY (`ID_FORMA_USO`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `FARMACIA_DB`.`LABORATORIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FARMACIA_DB`.`LABORATORIO` (
  `ID_LABORATORIO` INT NOT NULL AUTO_INCREMENT,
  `NM_LABORATORIO` VARCHAR(100) NULL,
  PRIMARY KEY (`ID_LABORATORIO`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `FARMACIA_DB`.`REMEDIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FARMACIA_DB`.`REMEDIO` (
  `COD_BARRA` CHAR(13) NOT NULL,
  `DOSAGEM` VARCHAR(15) NULL,
  `COMPOSICAO` VARCHAR(100) NULL,
  `GENERICO` TINYINT NULL,
  `NM_REMEDIO` VARCHAR(150) NULL,
  `DT_CADASTRO` DATETIME NULL,
  `PRECO` DOUBLE NULL,
  `PRECO_CUSTO` DOUBLE NULL,
  `ESTOQUE` INT NULL,
  `ID_FORMA_USO` INT NOT NULL,
  `ID_LABORATORIO` INT NOT NULL,
  PRIMARY KEY (`COD_BARRA`),
  INDEX `fk_REM_FORMA_USO_idx` (`ID_FORMA_USO` ASC),
  INDEX `fk_REM_LABORATORIO_idx` (`ID_LABORATORIO` ASC),
  CONSTRAINT `fk_REM_FORMA_USO`
    FOREIGN KEY (`ID_FORMA_USO`)
    REFERENCES `FARMACIA_DB`.`FORMA_USO` (`ID_FORMA_USO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_REM_LABORATORIO`
    FOREIGN KEY (`ID_LABORATORIO`)
    REFERENCES `FARMACIA_DB`.`LABORATORIO` (`ID_LABORATORIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `FARMACIA_DB`.`CATEGORIA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FARMACIA_DB`.`CATEGORIA` (
  `ID_CATEGORIA` INT NOT NULL AUTO_INCREMENT,
  `NM_CATEGORIA` VARCHAR(100) NULL,
  PRIMARY KEY (`ID_CATEGORIA`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `FARMACIA_DB`.`PRODUTO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FARMACIA_DB`.`PRODUTO` (
  `COD_BARRA` CHAR(13) NOT NULL,
  `NM_PRODUTO` VARCHAR(150) NULL,
  `DT_CADASTRO` DATETIME NULL,
  `PRECO` DOUBLE NULL,
  `PRECO_CUSTO` DOUBLE NULL,
  `ESTOQUE` INT NULL,
  `ID_CATEGORIA` INT NOT NULL,
  PRIMARY KEY (`COD_BARRA`),
  INDEX `fk_PROD_CATEGORIA_idx` (`ID_CATEGORIA` ASC)  ,
  CONSTRAINT `fk_PROD_CATEGORIA`
    FOREIGN KEY (`ID_CATEGORIA`)
    REFERENCES `FARMACIA_DB`.`CATEGORIA` (`ID_CATEGORIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `FARMACIA_DB`.`FORMA_PGTO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FARMACIA_DB`.`FORMA_PGTO` (
  `ID_FORMA_PGTO` INT NOT NULL AUTO_INCREMENT,
  `DESCRICAO` VARCHAR(45) NULL,
  PRIMARY KEY (`ID_FORMA_PGTO`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `FARMACIA_DB`.`NIVEL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FARMACIA_DB`.`NIVEL` (
  `ID_NIVEL` INT NOT NULL AUTO_INCREMENT,
  `DESCRICAO` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_NIVEL`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `FARMACIA_DB`.`USUARIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FARMACIA_DB`.`USUARIO` (
  `ID_USUARIO` INT NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(45) NULL,
  `DT_CADASTRO` DATETIME NULL,
  `LOGIN` VARCHAR(20) NULL,
  `SENHA` VARCHAR(20) NULL,
  `ID_NIVEL` INT NOT NULL,
  PRIMARY KEY (`ID_USUARIO`),
  INDEX `fk_USUARIO_NIVEL1_idx` (`ID_NIVEL` ASC)  ,
  CONSTRAINT `fk_USUARIO_NIVEL1`
    FOREIGN KEY (`ID_NIVEL`)
    REFERENCES `FARMACIA_DB`.`NIVEL` (`ID_NIVEL`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `FARMACIA_DB`.`ENDERECO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FARMACIA_DB`.`ENDERECO` (
  `ID_ENDERECO` INT NOT NULL AUTO_INCREMENT,
  `CEP` CHAR(8),
  `RUA` VARCHAR(100),
  `NUMERO` INT,
  `COMPLEMENTO` VARCHAR(100),
  `BAIRRO` VARCHAR(45),
  `CIDADE` VARCHAR(45),
  `UF` CHAR(2),
  PRIMARY KEY (`ID_ENDERECO`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `FARMACIA_DB`.`CLIENTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FARMACIA_DB`.`CLIENTE` (
  `ID_CLIENTE` INT NOT NULL AUTO_INCREMENT,
  `CPF` CHAR(11),
  `NOME` VARCHAR(45) NOT NULL,
  `DT_NASC` DATE,
  `DT_CADASTRO` DATETIME NOT NULL,
  `ID_ENDERECO` INT,
  PRIMARY KEY (`ID_CLIENTE`),
  UNIQUE INDEX `CPF_UNIQUE` (`CPF` ASC)  ,
  INDEX `fk_CLIENTE_ENDERECO1_idx` (`ID_ENDERECO` ASC)  ,
  CONSTRAINT `fk_CLIENTE_ENDERECO1`
    FOREIGN KEY (`ID_ENDERECO`)
    REFERENCES `FARMACIA_DB`.`ENDERECO` (`ID_ENDERECO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `FARMACIA_DB`.`VENDA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FARMACIA_DB`.`VENDA` (
  `ID_VENDA` INT NOT NULL AUTO_INCREMENT,
  `DT_VENDA` DATETIME NULL,
  `VALOR_TOTAL` DOUBLE NULL,
  `ID_FORMA_PGTO` INT NULL,
  `CANCELADA` TINYINT NULL,
  `ID_USUARIO` INT NOT NULL,
  `ID_CLIENTE` INT,
  PRIMARY KEY (`ID_VENDA`),
  INDEX `fk_VENDA_FORMA_PGTO_idx` (`ID_FORMA_PGTO` ASC)  ,
  INDEX `fk_VENDA_USUARIO1_idx` (`ID_USUARIO` ASC)  ,
  INDEX `fk_VENDA_CLIENTE1_idx` (`ID_CLIENTE` ASC)  ,
  CONSTRAINT `fk_VENDA_FORMA_PGTO`
    FOREIGN KEY (`ID_FORMA_PGTO`)
    REFERENCES `FARMACIA_DB`.`FORMA_PGTO` (`ID_FORMA_PGTO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_VENDA_USUARIO`
    FOREIGN KEY (`ID_USUARIO`)
    REFERENCES `FARMACIA_DB`.`USUARIO` (`ID_USUARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_VENDA_CLIENTE`
    FOREIGN KEY (`ID_CLIENTE`)
    REFERENCES `FARMACIA_DB`.`CLIENTE` (`ID_CLIENTE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `FARMACIA_DB`.`ITEM_REMEDIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FARMACIA_DB`.`ITEM_REMEDIO` (
  `ID_ITEM_REMEDIO` INT NOT NULL,
  `COD_BARRA` CHAR(13) NOT NULL,
  `QT_REMEDIO` INT NULL,
  `ID_VENDA` INT NOT NULL,
  PRIMARY KEY (`ID_ITEM_REMEDIO`),
  INDEX `fk_ITEM_REM_REMEDIO_idx` (`COD_BARRA` ASC)  ,
  INDEX `fk_ITEM_REM_VENDA_idx` (`ID_VENDA` ASC)  ,
  CONSTRAINT `fk_ITEM_REM_REMEDIO`
    FOREIGN KEY (`COD_BARRA`)
    REFERENCES `FARMACIA_DB`.`REMEDIO` (`COD_BARRA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ITEM_REMEDIO_VENDA1`
    FOREIGN KEY (`ID_VENDA`)
    REFERENCES `FARMACIA_DB`.`VENDA` (`ID_VENDA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `FARMACIA_DB`.`ITEM_PRODUTO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FARMACIA_DB`.`ITEM_PRODUTO` (
  `ID_ITEM_PRODUTO` INT NOT NULL,
  `COD_BARRA` CHAR(13) NOT NULL,
  `QT_PRODUTO` INT NULL,
  `ID_VENDA` INT NOT NULL,
  PRIMARY KEY (`ID_ITEM_PRODUTO`),
  INDEX `fk_ITEM_PROD_PRODUTO_idx` (`COD_BARRA` ASC)  ,
  INDEX `fk_ITEM_PROD_VENDA_idx` (`ID_VENDA` ASC)  ,
  CONSTRAINT `fk_ITEM_PROD_PRODUTO`
    FOREIGN KEY (`COD_BARRA`)
    REFERENCES `FARMACIA_DB`.`PRODUTO` (`COD_BARRA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ITEM_PRODUTO_VENDA1`
    FOREIGN KEY (`ID_VENDA`)
    REFERENCES `FARMACIA_DB`.`VENDA` (`ID_VENDA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `farmacia_db`.`categoria` (`NM_CATEGORIA`) VALUES ('Higiene Pessoal');
INSERT INTO `farmacia_db`.`categoria` (`NM_CATEGORIA`) VALUES ('Beleza');

INSERT INTO `farmacia_db`.`forma_uso` (`DESCRICAO`) VALUES ('Via Oral');
INSERT INTO `farmacia_db`.`forma_uso` (`DESCRICAO`) VALUES ('Pomada');

INSERT INTO `farmacia_db`.`laboratorio` (`NM_LABORATORIO`) VALUES ('EMS');
INSERT INTO `farmacia_db`.`laboratorio` (`NM_LABORATORIO`) VALUES ('Bayer');

INSERT INTO `farmacia_db`.`produto`(`COD_BARRA`,`NM_PRODUTO`,`DT_CADASTRO`,`PRECO`, `PRECO_CUSTO`,`ESTOQUE`,`ID_CATEGORIA`)
VALUES( 4800123410113, 'Shampoo Palmolive', NOW(), 12.25, 5.75 ,20 ,1);

INSERT INTO `farmacia_db`.`produto`(`COD_BARRA`,`NM_PRODUTO`,`DT_CADASTRO`,`PRECO`, `PRECO_CUSTO`,`ESTOQUE`,`ID_CATEGORIA`)
VALUES(4800123410114, 'Condicionador Palmolive', NOW(), 13.90, 4.90, 15 ,1);

INSERT INTO `farmacia_db`.`remedio` (`COD_BARRA`,`DOSAGEM`,`COMPOSICAO`,`GENERICO`,`NM_REMEDIO`,`DT_CADASTRO`,`PRECO`, `PRECO_CUSTO`,`ESTOQUE`,`ID_FORMA_USO`,`ID_LABORATORIO`)
VALUES( 310012340122, '100mg','Metamizol', 1, 'Dipirona Sódica', NOW(), 24.90, 13.25, 30, 1, 1);

INSERT INTO `farmacia_db`.`remedio` (`COD_BARRA`,`DOSAGEM`,`COMPOSICAO`,`GENERICO`,`NM_REMEDIO`,`DT_CADASTRO`,`PRECO`, `PRECO_CUSTO`,`ESTOQUE`,`ID_FORMA_USO`,`ID_LABORATORIO`)
VALUES( 310012340130, '200mg','Panadol', 0, 'Paracetamol', NOW(), 29.90, 16.75, 19, 1, 2);

INSERT INTO `farmacia_db`.`endereco`(`CEP`,`RUA`,`NUMERO`,`COMPLEMENTO`,`BAIRRO`,`CIDADE`,`UF`)
VALUES('88130090','Servidão Augusto Haeming',68,'Próximo a praça de palhoça','Centro','Palhoça','SC');

INSERT INTO `farmacia_db`.`cliente` (`CPF`,`NOME`,`DT_NASC`,`DT_CADASTRO`,`ID_ENDERECO`)
VALUES ('07185434912','Vitor Fabre de Souza','1997-12-29',NOW(),1);

INSERT INTO `farmacia_db`.`nivel`(`DESCRICAO`)
VALUES('ADMIN');
INSERT INTO `farmacia_db`.`nivel`(`DESCRICAO`)
VALUES('GERENTE');
INSERT INTO `farmacia_db`.`nivel`(`DESCRICAO`)
VALUES('ATENDENTE');

INSERT INTO `farmacia_db`.`usuario`(`NOME`,`DT_CADASTRO`,`LOGIN`,`SENHA`,`ID_NIVEL`)
VALUES('Administrador',NOW(),'admin','1234',1);

INSERT INTO `farmacia_db`.`forma_pgto`(`DESCRICAO`)
VALUES('Débito');

INSERT INTO `farmacia_db`.`forma_pgto`(`DESCRICAO`)
VALUES('Crédito');

INSERT INTO `farmacia_db`.`forma_pgto`(`DESCRICAO`)
VALUES('Dinheiro');

