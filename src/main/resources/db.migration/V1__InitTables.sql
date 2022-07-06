CREATE TABLE users(
    ID serial NOT NULL,
    login VARCHAR(30) NOT NULL,
    password VARCHAR(20),
    first_name VARCHAR(20),
    second_name VARCHAR(20),
    phone_number VARCHAR(9),
    email VARCHAR(20) NOT NULL,
    pesel VARCHAR(11) NOT NULL,
    PRIMARY KEY ID
    )

CREATE TABLE reservations(ID serial NOT NULL)

CREATE TABLE cars(ID serial NOT NULL)

