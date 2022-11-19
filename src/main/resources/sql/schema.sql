DROP TABLE IF EXISTS `board_table`;
DROP TABLE IF EXISTS `user_table`;

CREATE TABLE IF NOT EXISTS `user_table` (
	`ID` BIGINT NOT NULL AUTO_INCREMENT,
	`USERNAME` VARCHAR(30) NOT NULL,
	`PASSWORD` VARCHAR(80) NOT NULL,
	`NAME` VARCHAR(30) NOT NULL,
	`EMAIL` VARCHAR(50) NOT NULL,
	`ROLES` VARCHAR(30) NOT NULL DEFAULT 'ROLE_USER',
	PRIMARY KEY (`ID`),
	UNIQUE KEY (`USERNAME`)
);

CREATE TABLE IF NOT EXISTS `board_table` (
	`ID` BIGINT NOT NULL AUTO_INCREMENT,
	`TITLE` VARCHAR(50) NOT NULL,
	`CONTENT` TEXT NOT NULL,
	`AUTHOR` VARCHAR(30) NOT NULL,
	`FILE_NAME` VARCHAR(50) NULL,
	`CREATED_DATE` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	`MODIFIED_DATE` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	PRIMARY KEY (ID),
	CONSTRAINT `FK_AUTHOR_USERNAME` FOREIGN KEY (`AUTHOR`)
	REFERENCES `user_table` (`USERNAME`)
);