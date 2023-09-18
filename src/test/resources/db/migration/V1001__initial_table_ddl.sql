CREATE TABLE tutorial.employee (
	id integer NOT NULL AUTO_INCREMENT,
	first_name varchar(100) NOT NULL,
	last_name varchar(100) NOT NULL,
	start_date timestamp NULL,
	department_id integer,
	CONSTRAINT employeex_pk PRIMARY KEY (id)
);

CREATE TABLE tutorial.department (
	id integer NOT NULL AUTO_INCREMENT,
	department_name varchar(100) NOT NULL,
	CONSTRAINT department_pk PRIMARY KEY (id)
);











