CREATE TABLE person (
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    first_name VARCHAR (250) NOT NULL,
     last_name VARCHAR (250) NOT NULL,
     address VARCHAR (250) NOT NULL,
      city VARCHAR (250) NOT NULL,
       zip INT NOT NULL,
        phone VARCHAR (250) NOT NULL,
       email VARCHAR (250) NOT NULL);



INSERT INTO person (first_name, last_name, address, city, zip, phone, email) VALUES
('John','Boyd','1509 Culver St','Culver',97451,'841-874-6512','jaboyd@email.com'),
('Jacob','Boyd','1509 Culver St','Culver',97451,'841-874-6513','drk@email.com'),
('Tenley','Boyd','1509 Culver St','Culver',97451,'841-874-6512','tenz@email.com'),
('Roger','Boyd','1509 Culver St','Culver',97451,'841-874-6512','jaboyd@email.com'),
('Felicia','Boyd','1509 Culver St','Culver',97451,'841-874-6544','jaboyd@email.com'),
('Jonanathan','Marrack','29 15th St','Culver',97451,'841-874-6513','drk@email.com'),
('Tessa','Carman','834 Binoc Ave','Culver',97451,'841-874-6512','tenz@email.com'),
('Peter','Duncan','644 Gershwin Cir','Culver',97451,'841-874-6512','jaboyd@email.com'),
('Foster','Shepard','748 Townings Dr','Culver',97451,'841-874-6544','jaboyd@email.com'),
('Tony','Cooper','112 Steppes Pl','Culver',97451,'841-874-6874','tcoop@ymail.com'),
('Lily','Cooper','489 Manchester St','Culver',97451,'841-874-9845','lily@email.com'),
('Sophia','Zemicks','892 Downing Ct','Culver',97451,'841-874-7878','soph@email.com'),
('Warren','Zemicks','892 Downing Ct','Culver',97451,'841-874-7512','ward@email.com'),
('Zach','Zemicks','892 Downing Ct','Culver',97451,'841-874-7512','zarc@email.com'),
('Reginold','Walker','908 73rd St','Culver',97451,'841-874-8547','reg@email.com'),
('Jamie','Peters','908 73rd St','Culver',97451,'841-874-7462','jpeter@email.com'),
('Ron','Peters','112 Steppes Pl','Culver',97451,'841-874-8888','jpeter@email.com'),
('Allison','Boyd','112 Steppes Pl','Culver',97451,'841-874-9888','aly@imail.com'),
('Brian','Stelzer','947 E. Rose Dr','Culver',97451,'841-874-7784','bstel@email.com'),
('Shawna','Stelzer','947 E. Rose Dr','Culver',97451,'841-874-7784','ssanw@email.com'),
('Kendrik','Stelzer','947 E. Rose Dr','Culver',97451,'841-874-7784','bstel@email.com'),
('Clive','Ferguson','748 Townings Dr','Culver',97451,'841-874-6741','clivfd@ymail.com'),
('Eric','Cadigan','951 LoneTree Rd','Culver',97451,'841-874-7458','gramps@email.com');


CREATE TABLE firestation(id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                        address VARCHAR (250) NOT NULL,
                         station INT NOT NULL);

INSERT INTO firestation (address, station) VALUES
('1509 Culver St',3),
('29 15th St',2),
('834 Binoc Ave',3),
('644 Gershwin Cir',1),
('748 Townings Dr',3),
('112 Steppes Pl',3),
('489 Manchester St',4),
('892 Downing Ct',2),
('908 73rd St',1),
('112 Steppes Pl',4),
('947 E. Rose Dr',1),
('748 Townings Dr',3),
('951 LoneTree Rd',2);

CREATE TABLE medicalrecords (id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            birthdate date,
                            firstname varchar(255),
                            lastname varchar(255),
                            primary key (id));

INSERT INTO medicalrecords (birthdate, firstname, lastname) VALUES
('1984-03-06','John','Boyd'),
('1989-03-06','Jacob','Boyd'),
('2012-02-18','Tenley','Boyd'),
('2017-09-06','Roger','Boyd'),
('1986-01-08','Felicia','Boyd'),
('1989-01-03','Jonanathan','Marrack'),
('2012-02-18','Tessa','Carman'),
('2000-09-06','Peter','Duncan'),
('1980-01-08','Foster','Shepard'),
('1994-03-06','Tony','Cooper'),
('1994-03-06','Lily','Cooper'),
('1988-03-06','Sophia','Zemicks'),
('1985-03-06','Warren','Zemicks'),
('2017-03-06','Zach','Zemicks'),
('1979-08-30','Reginold','Walker'),
('1982-03-06','Jamie','Peters'),
('1965-04-06','Ron','Peters'),
('1965-03-15','Allison','Boyd'),
('1975-12-06','Brian','Stelzer'),
('1980-07-08','Shawna','Stelzer'),
('2014-03-06','Kendrik','Stelzer'),
('1994-03-06','Clive','Ferguson'),
('1945-08-06','Eric','Cadigan');

CREATE TABLE medicalrecords_allergies (medicalrecords_id bigint not null,
                                        allergies varchar(255),
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY);

INSERT INTO medicalrecords_allergies (medicalrecords_id, allergies) VALUES
(1,'nillacilan'),
(2,''),
(3,'peanut'),
(4,''),
(5,'xilliathal'),
(6,''),
(7,''),
(8,'shellfish'),
(9,''),
(10,'shellfish'),
(11,''),
(12,'peanut'),
(12,'shellfish'),
(12,'aznol'),
(13,''),
(14,''),
(15,'illisoxian'),
(16,''),
(17,''),
(18,'nillacilan'),
(19,'nillacilan'),
(20,''),
(21,''),
(22,''),
(23,'');



CREATE TABLE medicalrecords_medications (medicalrecords_id bigint not null,
                                            medications varchar(255));

INSERT INTO medicalrecords_medications (medicalrecords_id, medications) VALUES
(1,'aznol:350mg'),
(1,'hydrapermazol:100mg'),
(2,'pharmacol:5000mg'),
(2,'terazine:10mg'),
(2,'noznazol:250mg'),
(3,''),
(4,''),
(5,'tetracyclaz:650mg'),
(6,''),
(7,''),
(8,''),
(9,''),
(10,'hydrapermazol:300mg'),
(10,'dodoxadin:30mg'),
(11,''),
(12,'aznol:60mg'),
(12,'hydrapermazol:900mg'),
(12,'pharmacol:5000mg'),
(12,'terazine:500mg'),
(13,''),
(14,''),
(15,'thradox:700mg'),
(16,''),
(17,''),
(18,'aznol:200mg'),
(19,'ibupurin:200mg'),
(19,'hydrapermazol:400mg'),
(20,''),
(21,'noxidian:100mg'),
(21,'pharmacol:2500mg'),
(22,''),
(23,'tradoxidine:400mg');

COMMIT;