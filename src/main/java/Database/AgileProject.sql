USE `AgileProject`;

DROP TABLE IF EXISTS `PREREQUISITETASKS`;
DROP TABLE IF EXISTS `TASK`;
DROP TABLE IF EXISTS `PROJECT`;
DROP TABLE IF EXISTS `PERSON`;

CREATE TABLE IF NOT EXISTS `PERSON` (
	`ID` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	`username` varchar(50) NOT NULL UNIQUE,
	`password` varchar(50) NOT NULL,
	PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `PROJECT` (
	`projectID` int(11) NOT NULL AUTO_INCREMENT,
	`managerID` int(11) NOT NULL,
	`name` varchar(50) NOT NULL,
	FOREIGN KEY(`managerID`) REFERENCES PERSON(ID),
	PRIMARY KEY (`projectID`)
);


CREATE TABLE IF NOT EXISTS `TASK` (
	`taskID` int(11) NOT NULL AUTO_INCREMENT,
	`projectID` int(11) NOT NULL,
	`name` varchar(50) NOT NULL,
	`effortEstimate` int(11) NOT NULL,
	PRIMARY KEY (`taskID`),
	FOREIGN KEY (`projectID`) REFERENCES PROJECT(projectID)
);

CREATE TABLE IF NOT EXISTS `PREREQUISITETASKS`(
	`taskID` int(11) NOT NULL,
	`taskToComplete` int(11) NOT NULL,
	FOREIGN KEY (`taskID`) REFERENCES TASK(taskID)
);


INSERT INTO `PERSON` (`ID`, `name`, `username`, `password`) VALUES
(1, "Maten", "k1631403", "arsenal");

INSERT INTO `PROJECT` (`projectID`, `managerID`, `name`) VALUES
(1, 1, "blueFox");

INSERT INTO `PROJECT` (`projectID`, `managerID`, `name`) VALUES
(2, 1, "greenDragon");

INSERT INTO `TASK` (`projectID`,`name`,`effortEstimate`) VALUES
(1,"task1",3);

INSERT INTO `TASK` (`projectID`,`name`,`effortEstimate`) VALUES
(1,"task2",5);

INSERT INTO `PREREQUISITETASKS` (`taskID`,`taskToComplete`) VALUES
(2,1);
