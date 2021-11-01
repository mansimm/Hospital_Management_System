DROP DATABASE if exists HMS;
CREATE DATABASE HMS;
USE HMS;

create table hospital_staff(
	staff_id int AUTO_INCREMENT,
	fname varchar(20) NOT NULL,
	lname varchar(20) NOT NULL,
	email varchar(20) NOT NULL,
	contact varchar(10) NOT NULL,
	addhar_number varchar(12) NOT NULL,
	username varchar(20) NOT NULL,
	password varchar(100) NOT NULL,
	last_updated timestamp NOT NULL,
	constraint HMS_STAFF_Pk primary key ( staff_id )
);

create table address(
	address_id int auto_increment,
	house_no int,
	area varchar(30),
	street varchar(30),
	constraint hms_address_pk primary key(address_id)
);

create table patient(
	ssn_id int NOT NULL,
	patient_id int AUTO_INCREMENT,
	name varchar(20) NOT NULL,
	age int NOT NULL,
	date_of_admission date NOT NULL,
	type_of_bed varchar(20) NOT NULL,
	address_id int ,
	country varchar(20) NOT NULL,
	city varchar(20) NOT NULL,
	status varchar(20) NOT NULL,
	constraint hms_patient_pk primary key(patient_id),
	constraint hms_address_id_fk foreign key(address_id) references address(address_id)
);


select * from address;
select * from patient;
