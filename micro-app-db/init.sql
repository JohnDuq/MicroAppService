-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema micro_app_service
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema micro_app_service
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `micro_app_service` DEFAULT CHARACTER SET utf8 ;
USE `micro_app_service` ;

-- -----------------------------------------------------
-- Table `micro_app_service`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `micro_app_service`.`user` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(30) NOT NULL,
  `password` VARCHAR(250) NOT NULL,
  `status` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `User_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `micro_app_service`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `micro_app_service`.`role` (
  `id_role` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `description` VARCHAR(1000) NULL,
  PRIMARY KEY (`id_role`),
  UNIQUE INDEX `Name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `micro_app_service`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `micro_app_service`.`user_role` (
  `id_user_role` INT NOT NULL AUTO_INCREMENT,
  `id_role` INT NOT NULL,
  `id_user` INT NOT NULL,
  PRIMARY KEY (`id_user_role`),
  INDEX `fk_User_Role_Role1_idx` (`id_role` ASC) VISIBLE,
  INDEX `fk_User_Role_User1_idx` (`id_user` ASC) VISIBLE,
  CONSTRAINT `fk_User_Role_Role1`
    FOREIGN KEY (`id_role`)
    REFERENCES `micro_app_service`.`role` (`id_role`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Role_User1`
    FOREIGN KEY (`id_user`)
    REFERENCES `micro_app_service`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `micro_app_service`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `micro_app_service`;
INSERT INTO `micro_app_service`.`user` (`id_user`, `username`, `password`, `status`) VALUES (DEFAULT, 'ROOT', '$2a$10$RU//2EA6IMVNcu4KmLSPoei5mPRTcon4HUaOzjJEG6kgvhA5w1bEu', 'ENABLE');

COMMIT;


-- -----------------------------------------------------
-- Data for table `micro_app_service`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `micro_app_service`;
INSERT INTO `micro_app_service`.`role` (`id_role`, `name`, `description`) VALUES (DEFAULT, 'ADMIN', 'Root role cannot be updated');

COMMIT;


-- -----------------------------------------------------
-- Data for table `micro_app_service`.`user_role`
-- -----------------------------------------------------
START TRANSACTION;
USE `micro_app_service`;
INSERT INTO `micro_app_service`.`user_role` (`id_user_role`, `id_role`, `id_user`) VALUES (DEFAULT, 1, 1);

COMMIT;

