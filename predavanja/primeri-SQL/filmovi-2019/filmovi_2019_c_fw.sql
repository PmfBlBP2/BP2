-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema filmovi_bp2_2019
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema filmovi_bp2_2019
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `filmovi_bp2_2019` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `filmovi_bp2_2019` ;

-- -----------------------------------------------------
-- Table `filmovi_bp2_2019`.`drzava`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `filmovi_bp2_2019`.`drzava` ;

CREATE TABLE IF NOT EXISTS `filmovi_bp2_2019`.`drzava` (
  `DrzavaId` INT(11) NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`DrzavaId`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `Naziv` ON `filmovi_bp2_2019`.`drzava` (`Naziv` ASC);


-- -----------------------------------------------------
-- Table `filmovi_bp2_2019`.`zanr`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `filmovi_bp2_2019`.`zanr` ;

CREATE TABLE IF NOT EXISTS `filmovi_bp2_2019`.`zanr` (
  `ZanrId` INT(11) NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ZanrId`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `Naziv_2` ON `filmovi_bp2_2019`.`zanr` (`Naziv` ASC);

CREATE INDEX `Naziv` ON `filmovi_bp2_2019`.`zanr` (`Naziv` ASC);


-- -----------------------------------------------------
-- Table `filmovi_bp2_2019`.`film`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `filmovi_bp2_2019`.`film` ;

CREATE TABLE IF NOT EXISTS `filmovi_bp2_2019`.`film` (
  `FilmId` INT(11) NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(255) NOT NULL,
  `ZanrId` INT(11) NOT NULL,
  `Trajanje` INT(11) NULL DEFAULT '90' COMMENT 'Trajanje u minutama',
  `Godina` SMALLINT(6) NULL DEFAULT NULL COMMENT 'Godina proizvodnje',
  `Ocena` FLOAT NULL DEFAULT NULL,
  `Opis` TEXT NULL DEFAULT NULL,
  `Zarada` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`FilmId`),
  CONSTRAINT `film_ibfk_1`
    FOREIGN KEY (`ZanrId`)
    REFERENCES `filmovi_bp2_2019`.`zanr` (`ZanrId`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE INDEX `ZanrId` ON `filmovi_bp2_2019`.`film` (`ZanrId` ASC);


-- -----------------------------------------------------
-- Table `filmovi_bp2_2019`.`osoba`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `filmovi_bp2_2019`.`osoba` ;

CREATE TABLE IF NOT EXISTS `filmovi_bp2_2019`.`osoba` (
  `OsobaId` INT(11) NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(45) NOT NULL,
  `Prezime` VARCHAR(45) NOT NULL,
  `Nadimak` VARCHAR(45) NULL,
  `DrzavaId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`OsobaId`),
  CONSTRAINT `reziser_ibfk_10`
    FOREIGN KEY (`DrzavaId`)
    REFERENCES `filmovi_bp2_2019`.`drzava` (`DrzavaId`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `ReziserId_UNIQUE` ON `filmovi_bp2_2019`.`osoba` (`OsobaId` ASC);

CREATE INDEX `DrzavaId` ON `filmovi_bp2_2019`.`osoba` (`DrzavaId` ASC);


-- -----------------------------------------------------
-- Table `filmovi_bp2_2019`.`glumac`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `filmovi_bp2_2019`.`glumac` ;

CREATE TABLE IF NOT EXISTS `filmovi_bp2_2019`.`glumac` (
  `GlumacId` INT(11) NOT NULL,
  PRIMARY KEY (`GlumacId`),
  CONSTRAINT `fk_glumac_osoba1`
    FOREIGN KEY (`GlumacId`)
    REFERENCES `filmovi_bp2_2019`.`osoba` (`OsobaId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE INDEX `fk_glumac_osoba1_idx` ON `filmovi_bp2_2019`.`glumac` (`GlumacId` ASC);


-- -----------------------------------------------------
-- Table `filmovi_bp2_2019`.`glumio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `filmovi_bp2_2019`.`glumio` ;

CREATE TABLE IF NOT EXISTS `filmovi_bp2_2019`.`glumio` (
  `GlumioId` INT(11) NOT NULL AUTO_INCREMENT,
  `FilmId` INT(11) NOT NULL,
  `GlumacId` INT(11) NOT NULL,
  `Honorar` DOUBLE NOT NULL DEFAULT 1,
  PRIMARY KEY (`GlumioId`),
  CONSTRAINT `glumio_ibfk_2`
    FOREIGN KEY (`FilmId`)
    REFERENCES `filmovi_bp2_2019`.`film` (`FilmId`),
  CONSTRAINT `fk_glumio_glumac1`
    FOREIGN KEY (`GlumacId`)
    REFERENCES `filmovi_bp2_2019`.`glumac` (`GlumacId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE INDEX `GlumacId` ON `filmovi_bp2_2019`.`glumio` (`GlumioId` ASC);

CREATE INDEX `FilmId` ON `filmovi_bp2_2019`.`glumio` (`FilmId` ASC);

CREATE INDEX `fk_glumio_glumac1_idx` ON `filmovi_bp2_2019`.`glumio` (`GlumacId` ASC);


-- -----------------------------------------------------
-- Table `filmovi_bp2_2019`.`scenarista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `filmovi_bp2_2019`.`scenarista` ;

CREATE TABLE IF NOT EXISTS `filmovi_bp2_2019`.`scenarista` (
  `ScenaristaId` INT(11) NOT NULL,
  PRIMARY KEY (`ScenaristaId`),
  CONSTRAINT `fk_scenarista_osoba1`
    FOREIGN KEY (`ScenaristaId`)
    REFERENCES `filmovi_bp2_2019`.`osoba` (`OsobaId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE INDEX `fk_scenarista_osoba1_idx` ON `filmovi_bp2_2019`.`scenarista` (`ScenaristaId` ASC);


-- -----------------------------------------------------
-- Table `filmovi_bp2_2019`.`pisao_scenario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `filmovi_bp2_2019`.`pisao_scenario` ;

CREATE TABLE IF NOT EXISTS `filmovi_bp2_2019`.`pisao_scenario` (
  `FilmId` INT(11) NOT NULL,
  `ScenaristaId` INT(11) NOT NULL,
  CONSTRAINT `pisao_scenario_ibfk_2`
    FOREIGN KEY (`FilmId`)
    REFERENCES `filmovi_bp2_2019`.`film` (`FilmId`),
  CONSTRAINT `fk_pisao_scenario_scenarista1`
    FOREIGN KEY (`ScenaristaId`)
    REFERENCES `filmovi_bp2_2019`.`scenarista` (`ScenaristaId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE INDEX `FilmId` ON `filmovi_bp2_2019`.`pisao_scenario` (`FilmId` ASC);

CREATE INDEX `fk_pisao_scenario_scenarista1_idx` ON `filmovi_bp2_2019`.`pisao_scenario` (`ScenaristaId` ASC);


-- -----------------------------------------------------
-- Table `filmovi_bp2_2019`.`reziser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `filmovi_bp2_2019`.`reziser` ;

CREATE TABLE IF NOT EXISTS `filmovi_bp2_2019`.`reziser` (
  `ReziserId` INT(11) NOT NULL,
  PRIMARY KEY (`ReziserId`),
  CONSTRAINT `fk_reziser_osoba1`
    FOREIGN KEY (`ReziserId`)
    REFERENCES `filmovi_bp2_2019`.`osoba` (`OsobaId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE INDEX `fk_reziser_osoba1_idx` ON `filmovi_bp2_2019`.`reziser` (`ReziserId` ASC);


-- -----------------------------------------------------
-- Table `filmovi_bp2_2019`.`rezirao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `filmovi_bp2_2019`.`rezirao` ;

CREATE TABLE IF NOT EXISTS `filmovi_bp2_2019`.`rezirao` (
  `FilmId` INT(11) NOT NULL,
  `ReziserId` INT(11) NOT NULL,
  CONSTRAINT `rezirao_ibfk_2`
    FOREIGN KEY (`FilmId`)
    REFERENCES `filmovi_bp2_2019`.`film` (`FilmId`),
  CONSTRAINT `fk_rezirao_reziser1`
    FOREIGN KEY (`ReziserId`)
    REFERENCES `filmovi_bp2_2019`.`reziser` (`ReziserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE INDEX `FilmId` ON `filmovi_bp2_2019`.`rezirao` (`FilmId` ASC);

CREATE INDEX `fk_rezirao_reziser1_idx` ON `filmovi_bp2_2019`.`rezirao` (`ReziserId` ASC);


-- -----------------------------------------------------
-- Table `filmovi_bp2_2019`.`finasniranje`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `filmovi_bp2_2019`.`finasniranje` ;

CREATE TABLE IF NOT EXISTS `filmovi_bp2_2019`.`finasniranje` (
  `FinansiranjeId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `DrzavaId` INT(11) NOT NULL,
  `FilmId` INT(11) NOT NULL,
  `Ucesce` DOUBLE NOT NULL DEFAULT 0,
  PRIMARY KEY (`FinansiranjeId`),
  CONSTRAINT `fk_finasniranje_drzava1`
    FOREIGN KEY (`DrzavaId`)
    REFERENCES `filmovi_bp2_2019`.`drzava` (`DrzavaId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_finasniranje_film1`
    FOREIGN KEY (`FilmId`)
    REFERENCES `filmovi_bp2_2019`.`film` (`FilmId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_finasniranje_drzava1_idx` ON `filmovi_bp2_2019`.`finasniranje` (`DrzavaId` ASC);

CREATE INDEX `fk_finasniranje_film1_idx` ON `filmovi_bp2_2019`.`finasniranje` (`FilmId` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
