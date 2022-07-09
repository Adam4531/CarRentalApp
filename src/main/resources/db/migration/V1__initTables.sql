CREATE TYPE public.car_body_type AS ENUM('COUPE', 'SEDAN', 'SUV', 'STATION_WAGON', 'HATCHBACK', 'CABRIOLET');
CREATE TYPE public.car_type_of_fuel AS ENUM('BENZINE', 'DIESEL', 'ELECTRIC', 'LPG');

CREATE TABLE IF NOT EXISTS public.CARS(
    ID BIGINT NOT NULL,
    brand VARCHAR(20),
    model VARCHAR(20),
    engine_capacity numeric(2,1),
    body_type public.car_body_type NOT NULL,
    type_of_fuel public.car_type_of_fuel NOT NULL,
    new_car_cost numeric(9,2),
    production_year int NOT NULL,
    CONSTRAINT cars_id_pkey PRIMARY KEY(ID)
    );

CREATE TABLE IF NOT EXISTS public.USERS(
    ID BIGINT NOT NULL,
    login VARCHAR(30) NOT NULL,
    password VARCHAR(20),
    first_name VARCHAR(20),
    second_name VARCHAR(20),
    phone_number VARCHAR(9),
    email VARCHAR(30) NOT NULL,
    pesel VARCHAR(11) NOT NULL,
    CONSTRAINT users_id_pkey PRIMARY KEY(ID)
    );

CREATE TYPE public.reservation_status AS ENUM('Free', 'Reserved', 'Taken');

CREATE TABLE IF NOT EXISTS public.RESERVATIONS(
    ID BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    car_id BIGINT NOT NULL,
    date_start timestamp without time zone, --if car is free then that column cannot be NOT NULL
    date_end timestamp without time zone, --if car is free then that column cannot be NOT NULL
    cost numeric(7,2) NOT NULL,
    payment_in_advance numeric(7,2) NOT NULL,
    status public.reservation_status NOT NULL,
    CONSTRAINT reservations_id_pkey PRIMARY KEY(ID),
    CONSTRAINT user_id_pkey FOREIGN KEY(user_id) REFERENCES users(ID),
    CONSTRAINT car_id_pkey FOREIGN KEY(car_id) REFERENCES cars(ID)
    );