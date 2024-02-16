CREATE TABLE delivery
(
	id serial PRIMARY KEY,
	delivery_date date NOT NULL,
	curier_first_name varchar(25) NOT NULL,
	curier_last_name varchar(25) NOT NULL
);

CREATE TABLE client
(
	id serial PRIMARY KEY,
	phone_number varchar(12) NOT NULL,
	first_name varchar(25) NOT NULL,
	last_name varchar(25) NOT NULL,
	address varchar(100) NOT NULL
);

CREATE TABLE order_detail
(
	id serial PRIMARY KEY,
	order_date date NOT NULL,
	code varchar(15) NOT NULL,
	status varchar(25),
	id_client int REFERENCES client(id),
	id_delivery int REFERENCES delivery(id)
);

CREATE TABLE payment
(
	id serial PRIMARY KEY,
	payment_date date NOT NULL,
	payment_sum real NOT NULL,
	id_order int UNIQUE REFERENCES order_detail(id)
);

CREATE TABLE manufacturer
(
	id serial PRIMARY KEY,
	manufacturer_name varchar(25) NOT NULL,
	country varchar(25) NOT NULL
);

CREATE TABLE good
(
	id serial PRIMARY KEY,
	product_name varchar(25) NOT NULL,
	model varchar(25),
	guarantee int NOT NULL,
	price real NOT NULL,
	description text NOT NULL,
	id_manufacturer int REFERENCES manufacturer(id)
);

CREATE TABLE storehouse
(
	id serial PRIMARY KEY,
	address varchar(100) NOT NULL,
	square real NOT NULL
);

CREATE TABLE good_storehouse
(
	id_good int REFERENCES good(id),
	id_stock int REFERENCES storehouse(id),
	good_num int NOT NULL,
	CONSTRAINT good_storehouse_pk PRIMARY KEY (id_good, id_stock)
);

CREATE TABLE good_order
(
	id_good int REFERENCES good(id),
	id_order int REFERENCES order_detail(id),
	good_num int NOT NULL,
	CONSTRAINT good_order_pk PRIMARY KEY (id_good, id_order)
);


