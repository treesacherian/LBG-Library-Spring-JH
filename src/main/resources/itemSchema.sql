DROP TABLE `item` CASCADE;
CREATE TABLE `item` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`type` VARCHAR,
	`name` VARCHAR,
	`person_id` INT
);