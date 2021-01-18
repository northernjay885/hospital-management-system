# CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
#
# GRANT ALL PRIVILEGES ON * . * TO 'admin'@'localhost';

CREATE DATABASE hospital_data;

USE hospital_data;

CREATE TABLE `admin` (
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;

CREATE TABLE `patient`(
    `id` INTEGER NOT NULL,
    `firstname` VARCHAR(255) NOT NULL,
    `lastname` VARCHAR(255) NOT NULL,
    `inpatient` BOOLEAN NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE =InnoDB DEFAULT CHARSET = latin1;

CREATE TABLE `lab_report`(
    `id` INTEGER NOT NULL,
    `patient_id` INTEGER NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `date` TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`patient_id`) REFERENCES patient(`id`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;

CREATE TABLE `billing` (
    `id` INTEGER NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `patient_id` INTEGER NOT NULL,
    `price` DECIMAL(15, 2) NOT NULL,
    `date` TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;

INSERT INTO `admin` VALUES ('admin', 'admin');