SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS ticket;
DROP TABLE IF EXISTS ticket_type;
DROP TABLE IF EXISTS transaction;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE users(
id BIGINT NOT NULL AUTO_INCREMENT,
username VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
role VARCHAR(255) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE customer(
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE event(
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
place VARCHAR(255) NOT NULL,
city VARCHAR(255) NOT NULL,
ticket_amount INT NOT NULL,
event_date DATE NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE ticket_type(
id BIGINT NOT NULL AUTO_INCREMENT,
description VARCHAR(255) NOT NULL,
price DOUBLE NOT NULL,
event_id BIGINT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (event_id) REFERENCES event(id)
);

CREATE TABLE transaction(
id BIGINT NOT NULL AUTO_INCREMENT,
transaction_date DATE NOT NULL,
amount DOUBLE NOT NULL,
customer_id BIGINT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE ticket(
id BIGINT NOT NULL AUTO_INCREMENT,
event_id BIGINT NOT NULL,
ticket_type_id BIGINT NOT NULL,
transaction_id BIGINT NOT NULL,
verified BOOLEAN NOT NULL,
code VARCHAR(255) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (event_id) REFERENCES event(id),
FOREIGN KEY (ticket_type_id) REFERENCES ticket_type(id),
FOREIGN KEY (transaction_id) REFERENCES transaction(id)
);

INSERT INTO users (username, password, role)
VALUES("user", "{bcrypt}$2a$10$cZAbqG8AaRTHSdwuNgPEHunTzr5.M.cAx4u6XwMsDSri.0e6wF8ca", "USER"),
("admin", "{bcrypt}$2a$12$wW1l6GyD6.OHZsOZuBaFVu0bFqD0aggEQ9k2vjo1d9.Adn0j0PmGK", "ADMIN"),
("scanner", "{bcrypt}$2a$12$J18yaI/yoy2LYzjbie8Vhus3s3UfbyRJN3BV/y6LxQxwjnGOgXte6", "SCANNER");

INSERT INTO customer (name, email)
VALUES("DBTesti", "dbmail@mail.com");

INSERT INTO event (name, place, city, ticket_amount, event_date)
VALUES("DBtapahtuma", "kantapaikka", "vaikka kanta", 1000, "2023-12-24");

INSERT INTO ticket_type (description, price, event_id)
VALUES("Testi", 20.30, 1);

INSERT INTO transaction(transaction_date, amount, customer_id)
VALUES("2023-12-12", 40.40, 1);

INSERT INTO ticket (event_id, ticket_type_id, transaction_id, code, verified)
VALUES(1, 1, 1, "KANTA-123", TRUE);

SELECT * FROM users;
SELECT * FROM customer;
SELECT * FROM event;
SELECT * FROM ticket_type;
SELECT * FROM transaction;
SELECT * FROM ticket;

