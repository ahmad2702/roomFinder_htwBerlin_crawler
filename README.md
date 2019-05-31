# htwLsf_room_crawler
##############################

#Table: events

CREATE TABLE `bachelor`.`events` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `begin` DATETIME NOT NULL,
  `end` DATETIME NOT NULL,
  `lsf_nr` VARCHAR(300) NOT NULL,
  `name` VARCHAR(300) NOT NULL,
  `lsf_id` DOUBLE NOT NULL,
  `building` VARCHAR(300),
  `room` VARCHAR(300),
  `lecturer` VARCHAR(300) NOT NULL,
  `is_actual` INT NOT NULL,
  PRIMARY KEY (`id`)
  );
  
  
  
  