# htwLsf_room_crawler

events Table
CREATE TABLE `lsf_5`.`events` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `begin` DATETIME NOT NULL,
  `end` DATETIME NOT NULL,
  `lsf_nr` VARCHAR(300) NOT NULL,
  `name` VARCHAR(300) NOT NULL,
  `lsf_id` DOUBLE NOT NULL,
  `building` VARCHAR(300) NOT NULL,
  `room` VARCHAR(300) NOT NULL,
  `lecturer` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`id`)
  );
  
  
  
  