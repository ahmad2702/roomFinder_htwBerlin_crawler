# htwLsf_room_crawler

events Table
CREATE TABLE `lsf_6`.`events` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `begin` DOUBLE NOT NULL,
  `end` DOUBLE NOT NULL,
  `lsf_nr` DOUBLE NOT NULL,
  `name` VARCHAR(300) NOT NULL,
  `lsf_id` DOUBLE NOT NULL,
  `building` VARCHAR(300) NOT NULL,
  `room` VARCHAR(300) NOT NULL,
  `lecturer` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`id`)
  );
  
  
  
  