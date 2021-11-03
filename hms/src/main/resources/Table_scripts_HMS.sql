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
	date_of_discharge date,
	bill_id int,
	constraint hms_patient_pk primary key(patient_id),
	constraint hms_address_id_fk foreign key(address_id) references address(address_id)
	constraint hms_bill_id_fk foreign key(bill_id) references bill(bill_id)
);


create table medicines(
	medicine_id int auto_increment,
	medicine_name varchar(25),
	quantity int,
	rate double,
	constraint hms_medicine_pk primary key(medicine_id)	
);

insert into medicines (medicine_name,quantity,rate)values("Paracetemol",10,20);
insert into medicines (medicine_name,quantity,rate)values("paracet",10,20);

create table medicine_issued(
	medicine_issued_id int auto_increment,
	patient_id int not null,
	medicine_id int not null,
	quantity int not null,
	date_of_issue date not null,
	constraint hms_medicine_issued_pk primary key(medicine_issued_id)
);

insert into medicine_issued (patient_id,medicine_id,quantity,date_of_issue)values(1,1,2,"2021-11-03");

create table diagnosis(
	test_id int auto_increment,
	test_name varchar(25) not null,
	test_description varchar(50),
	test_charges double not null,
	constraint hms_diagnostics_pk primary key(test_id)
);

insert into diagnosis (test_name,test_description,test_charges) values ("Hemogram","Hemogram comprises of CBC and ESR",150);


create table diagnostics_conducted(
	diagnostics_conducted_id int auto_increment,
	patient_id int not null,
	test_id int not null,
	date_of_diagnosis date not null,
	result varchar(25),
	comment_on_result varchar(30),
	constraint hms_diagnostics_conducted_pk primary key(diagnostics_conducted_id)
);

create table bill(
	bill_id int auto_increment,
	medicine_bill double,
	diagnisis_bill double,
	room_bill double,
	gst double,
	total_bill double,
	payment_mode varchar(15),
	transaction_id varchar(15),
	constraint hms_bill_pk primary_key(bill_id)
);

select * from hospital_staff;
select * from address;
select * from patient;
select * from medicines;
select * from medicine_issued;
select * from diagnostics;
select * from diagnostics_conducted;




















