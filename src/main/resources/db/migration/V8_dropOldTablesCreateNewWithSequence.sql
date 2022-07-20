DROP TABLE reservations CASCADE;
DROP TABLE users CASCADE;

CREATE SEQUENCE IF NOT EXISTS id_seq
AS BIGINT
INCREMENT BY 1
START WITH 10000;

CREATE TABLE IF NOT EXISTS public.USERS(
    ID BIGINT NOT NULL DEFAULT nextval('id_seq')PRIMARY KEY,
    login VARCHAR(28) NOT NULL,
    password VARCHAR(80),
    first_name VARCHAR(60),
    second_name VARCHAR(60),
    phone_number VARCHAR(9),
    email VARCHAR(80) NOT NULL,
    pesel VARCHAR(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.RESERVATIONS(
    ID BIGINT NOT NULL DEFAULT nextval('id_seq')PRIMARY KEY,
    user_id BIGINT NOT NULL,
    car_id BIGINT NOT NULL,
    date_start timestamp without time zone, --if car is free then that column cannot be NOT NULL
    date_end timestamp without time zone, --if car is free then that column cannot be NOT NULL
    cost numeric(7,2) NOT NULL,
    payment_in_advance numeric(7,2) NOT NULL,
    CONSTRAINT user_id_pkey FOREIGN KEY(user_id) REFERENCES users(ID),
    CONSTRAINT car_id_pkey FOREIGN KEY(car_id) REFERENCES cars(ID)
    );