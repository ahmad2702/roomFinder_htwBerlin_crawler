# htwLsf_room_crawler

events Table
CREATE TABLE `lsf_1`.`events` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `begin` DOUBLE NOT NULL,
  `end` DOUBLE NOT NULL,
  `lsf_nr` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `lsf_id` INT NOT NULL,
  `building` VARCHAR(45) NOT NULL,
  `room` VARCHAR(45) NOT NULL,
  `lecturer` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
  
  
  