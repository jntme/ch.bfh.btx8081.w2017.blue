 CREATE DATABASE sophobia;
 
 
  CREATE TABLE patient (
	 pid Serial PRIMARY KEY,
	 picture BYTEA,
	 name character varying(15) NOT NULL,
	 prename character varying(15) NOT NULL,
	 gender Char(1) NOT NULL CHECK (gender IN ('m','f')),
	 birthdate date CHECK (birthdate > '01.01.1900') NOT NULL,
	 street character varying(30) NOT NULL,
	 zip CHAR(4) NOT NULL CHECK (char_length(zip) = 4),
	 city character varying(30) NOT NULL,
	 note character varying[],
	 diagnosis character varying[],
	 drug character varying[],
	 objective character varying[]
 );
--Note, diagnosis, drug and objective are placeholder only 
 
 
 
 --Insert first patient
 
 INSERT INTO patient(name,prename,gender,birthdate,street,zip,city) VALUES(
	'Babbel',
	'Tim',
	'm',
	'1995-03-15',
	'Weihergasse 7',
	'3000',
	'Bern'
);

--Insert second patient

INSERT INTO patient(name,prename,gender,birthdate,street,zip,city) VALUES(
	'Stahl',
	'Stefanie',
	'f',
	'1981-05-30',
	'Hauptstrasse 87',
	'2502',
	'Biel'
); 

select * from patient;