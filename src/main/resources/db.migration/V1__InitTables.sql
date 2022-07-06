CREATE TABLE IF NOT EXISTS users(
    ID serial NOT NULL,
    login VARCHAR(30) NOT NULL,
    password VARCHAR(20),
    first_name VARCHAR(20),
    second_name VARCHAR(20),
    phone_number VARCHAR(9),
    email VARCHAR(20) NOT NULL,
    pesel VARCHAR(11) NOT NULL,
    CONSTRAINT users_id_pkey PRIMARY KEY(ID)
    );

CREATE TYPE cars_body_type AS ENUM('Coupe', 'Sedan', 'SUV', 'Station_Wagon', 'Hatchback', 'Cabriolet');
CREATE TYPE cars_type_of_fuel AS ENUM('Benzine', 'Diesel', 'Electric', 'LPG');
CREATE TABLE IF NOT EXISTS cars(
    ID serial NOT NULL,
    brand VARCHAR(20),
    model VARCHAR(20),
    engine_capacity numeric(2,1),
    body_type cars_body_type NOT NULL,
    type_of_fuel cars_type_of_fuel NOT NULL,
    new_car_cost numeric(6,2),
    production_year int NOT NULL,
    CONSTRAINT cars_id_pkey PRIMARY KEY(ID)
    );

CREATE TYPE reservation_status AS ENUM('Free', 'Reserved', 'Taken');

CREATE TABLE IF NOT EXISTS reservations(
    ID serial NOT NULL,
    user_id int NOT NULL,
    car_id int NOT NULL,
    date_start date, --if car is free then that column cannot be NOT NULL
    date_end date, --if car is free then that column cannot be NOT NULL
    cost numeric(6,2) NOT NULL,
    payment_in_advance numeric(5,2) NOT NULL,
    status reservation_status NOT NULL,
    CONSTRAINT reservations_id_pkey PRIMARY KEY(ID),
    CONSTRAINT user_id_pkey FOREIGN KEY(user_id) REFERENCES users(ID),
    CONSTRAINT car_id_pkey FOREIGN KEY(car_id) REFERENCES cars(ID)
    );




