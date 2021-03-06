# CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
#
# GRANT ALL PRIVILEGES ON * . * TO 'admin'@'localhost';
DROP DATABASE IF EXISTS hospital_data;

CREATE DATABASE IF NOT EXISTS hospital_data;

USE hospital_data;

CREATE TABLE `admin` (
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;

CREATE TABLE `patient`(
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `firstname` VARCHAR(255) NOT NULL,
    `lastname` VARCHAR(255) NOT NULL,
    `patient_type` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE =InnoDB DEFAULT CHARSET = latin1;

CREATE TABLE `lab_report`(
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `patient_id` INTEGER NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `date` TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`patient_id`) REFERENCES patient(`id`) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = latin1;

CREATE TABLE `billing` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `patient_id` INTEGER NOT NULL,
    `price` DECIMAL(15, 2) NOT NULL,
    `date` TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`patient_id`) REFERENCES patient(`id`) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = latin1;

INSERT INTO `admin` VALUES ('admin', 'admin');