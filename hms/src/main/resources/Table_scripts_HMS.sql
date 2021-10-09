DROP DATABASE if exists HMS;
CREATE DATABASE HMS;
USE HMS;

create table hospital_staff(
	staff_id int,
	fname varchar(20) NOT NULL,
	lname varchar(20) NOT NULL,
	email varchar(20) NOT NULL,
	contact varchar(10) NOT NULL,
	addhar_number varchar(12) NOT NULL,
	username varchar(20) NOT NULL,
	password varchar(20) NOT NULL,
	last_updated timestamp NOT NULL,
	constraint HMS_STAFF_Pk primary key ( staff_id )
);


