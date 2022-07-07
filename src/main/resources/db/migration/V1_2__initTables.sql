CREATE TABLE IF NOT EXISTS users(
    ID BIGINT NOT NULL,
    login VARCHAR(30) NOT NULL,
    password VARCHAR(20),
    first_name VARCHAR(20),
    second_name VARCHAR(20),
    phone_number VARCHAR(9),
    email VARCHAR(20) NOT NULL,
    pesel VARCHAR(11) NOT NULL,
    CONSTRAINT users_id_pkey PRIMARY KEY(ID)
    );

CREATE TYPE IF NOT EXISTS car_body_type AS ENUM('Coupe', 'Sedan', 'SUV', 'Station_Wagon', 'Hatchback', 'Cabriolet');
CREATE TYPE IF NOT EXISTS car_type_of_fuel AS ENUM('Benzine', 'Diesel', 'Electric', 'LPG');
CREATE TABLE IF NOT EXISTS cars(
    ID BIGINT NOT NULL,
    brand VARCHAR(20),
    model VARCHAR(20),
    engine_capacity numeric(2,1),
    body_type car_body_type NOT NULL,
    type_of_fuel car_type_of_fuel NOT NULL,
    new_car_cost numeric(6,2),
    production_year int NOT NULL,
    CONSTRAINT cars_id_pkey PRIMARY KEY(ID)
    );

CREATE TYPE IF NOT EXISTS reservation_status AS ENUM('Free', 'Reserved', 'Taken');
CREATE TABLE IF NOT EXISTS reservations(
    ID BIGINT NOT NULL,
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

--Insert

insert into cars (ID, brand, model, engine_capacity, body_type, type_of_fuel, new_car_cost, production_year) values
(1, 'GMC', 'Envoy XL', 4.2, 'SUV', 'Benzine', 2583355.15, 2002),
(2, 'Chevrolet', 'Impala SS', 3.2, 'Sedan', 'Benzine', 2794112.89, 1995),
(3, 'Lexus', 'RX', 3.0,'Coupe', 'Benzine', 242138.12, 2005),
(4, 'Chevrolet', '3500', 3.6, 'Sedan' ,'Benzine', 1169492.47, 2000),
(5, 'Hyundai', 'Sonata', 1.7, 'Sedan', 'Diesel', 619185.83, 1998),
(6, 'Lexus', 'LS', 2.2, 'Coupe', 'Diesel', 1167804.06, 2001),
(7, 'GMC', 'Suburban 1500', 4.2, 'SUV', 'Benzine', 2070876.68, 1998),
(8, 'Audi', 'A4', 3.0, 'Station_Wagon', 'Diesel', 313313.89, 2005),
(9, 'Audi', 'Q8', 3.0, 'SUV', 'Benzine', 1938005.44, 2009),
(10, 'Lexus', 'IS-F', 3.0, 'Coupe', 'Benzine', 2552772.81, 2009),
(11, 'Mitsubishi', 'Pajero', 1.2, 'Hatchback', 'LPG', 411653.59, 2002),
(12, 'Chrysler', 'Grand Voyager',2.8, 'SUV', 'LPG', 356890.01, 2000),
(13, 'Chrysler', 'Pacifica', 3.4, 'SUV', 'LPG', 2341958.83, 2007),
(14, 'Subaru', 'Legacy', 4.0, 'Sedan', 'Benzine', 1736392.40, 2001),
(15, 'Nissan', 'Pathfinder', 2.0,'Hatchback', 'Benzine', 2664744.42, 2004),
(16, 'GMC', '1500', 4.2, 'SUV', 'Benzine', 944749.93, 1998),
(17, 'Mercedes-Benz','R-Class', 5.2, 'Coupe', 'Benzine', 1979299.45, 2010),
(18, 'GMC', 'Yukon XL 1500', 4.2, 'SUV', 'Benzine', 1920072.05, 2000),
(19, 'Oldsmobile', '88', 1.7, 'Station_Wagon', 'Diesel', 2966425.83, 1992),
(20, 'Buick', 'LaCrosse', 2.4, 'Sedan', 'Benzine' , 95234.57, 2008);

insert into users (ID, login, password, first_name, second_name, phone_number, email, pesel) values
(1, 'ngartin0', 'Qu217b7', 'Nara', 'Gartin', '609724448', 'ngartin0@lulu.com', 87092048473),
(2, 'ecureton1', 'iWGFaVu5L3oZ', 'Eustace', 'Cureton', '716790081', 'ecureton1@4shared.com', 87080246827),
(3, 'nleggett2', 'gygYsiCIYzi', 'Nelly', 'Leggett', '390985484', 'nleggett2@cdc.gov', 75052895481),
(4, 'rchalcot3', '6kazAY', 'Rosy', 'Chalcot', '339948779', 'rchalcot3@amazon.co.uk', 73022866264),
(5, 'cruffey4', 'h95cB1', 'Carole', 'Ruffey', '553897019', 'cruffey4@kickstarter.com', 90050444581),
(6, 'pocullen5', 'GILeL2vauS', 'Pavia', 'O''Cullen', '716989136', 'pocullen5@businessinsider.com', 75031665869),
(7, 'jlocke6', 'g8D7uU', 'Jobi', 'Locke', '821585705', 'jlocke6@geocities.com', 86020244198),
(8, 'gstower7', 'azeZvOJMiKtw', 'Goober', 'Stower', '829966115', 'gstower7@plala.or.jp', 99042771212),
(9, 'efarquar8', 'peerhydyALcq', 'Everett', 'Farquar', '311088735', 'efarquar8@e-recht24.de', 83091536322),
(10, 'gbelding9', 'IbjGDrti', 'Georges', 'Belding', '210836552', 'gbelding9@livejournal.com', 67091581186),
(11, 'debhardta', 'k3xeOAG', 'Davide', 'Ebhardt', '822345502', 'debhardta@about.com', 70082925836),
(12, 'dguillotinb', 'CGCa4E2lG', 'Denver', 'Guillotin', '588052271', 'dguillotinb@dailymail.co.uk', 73012169458),
(13, 'emutterc', 'ROWsaKK', 'Erastus', 'Mutter', '643233175', 'emutterc@cargocollective.com', 78052322155),
(14, 'ktrunkfieldd', 'oe8ejq7oo', 'Kele', 'Trunkfield', '922928935', 'ktrunkfieldd@google.co.uk', 95010641736),
(15, 'datleye', '9PCN7D75bVSb', 'Doug', 'Atley', '517857919', 'datleye@technorati.com', 64082445317),
(16, 'sbraunerf', 'unH0rsjBkt', 'Salim', 'Brauner', '812775564', 'sbraunerf@ucoz.com', 62011838742),
(17, 'rpierrepointg', 'ZLsKDsNlm', 'Roxi', 'Pierrepoint', '718074094', 'rpierrepointg@miibeian.gov.cn', 83032119331),
(18, 'khanwellh', 'w14ksTiBf22e', 'Konstantine', 'Hanwell', '933165805', 'khanwellh@fotki.com', 67040981166),
(19, 'dtoureti', 'lgdy6rU74Zv', 'Darcy', 'Touret', '483549697', 'dtoureti@merriam-webster.com', 99080175524),
(20, 'ebruckentalj', 'oSTRP1ZMvMgt', 'Erie', 'Bruckental', '773864681', 'ebruckentalj@gnu.org', 88072037757);

--TODO add status as last row
-- insert into reservations (ID, user_id, car_id, date_start, date_end, cost, payment_in_advance) values (1, 10, 8, '2022-11-15 10:31:55', '2021-08-02 05:33:36', '$813427.21', '$26115.84'),
-- (2, 5, 5, '2023-01-15 20:35:16', '2022-04-04 06:20:37', 211579.59, 5870.62),
-- (3, 13, 19, '2022-05-09 15:49:16', '2021-11-19 00:06:17', 553201.85, 33613.52),
-- (4, 7, 1, '2023-01-15 16:04:55', '2022-02-03 22:47:30', 960360.99, 56085.35),
-- (5, 19, 12, '2023-02-20 05:50:55', '2021-12-28 20:57:28', 228561.24, 36852.65),
-- (6, 15, 20, '2021-11-24 22:10:37', '2022-03-10 18:14:40', 546545.42, 4180.56),
-- (7, 2, 8, '2022-09-12 12:14:06', '2021-07-16 14:01:33', 741577.06, 36542.29),
-- (8, 20, 5, '2023-01-24 16:47:47', '2022-06-17 14:45:14', 135806.70, 8953.09),
-- (9, 4, 6, '2023-05-11 01:10:27', '2021-12-20 13:33:41', 618801.51, 96672.79),
-- (10, 9, 8, '2022-09-08 20:23:20', '2022-05-05 12:44:35', 374744.64, 3723.60),
-- (11, 7, 20, '2022-09-19 04:07:22', '2022-02-21 15:50:21', 747642.82, 90623.29),
-- (12, 7, 11, '2022-05-02 20:55:13', '2022-05-09 19:58:02', 122591.11, 64159.54),
-- (13, 3, 13, '2022-07-06 02:39:20', '2021-08-13 02:51:11', 274378.34, 49585.29),
-- (14, 19, 20, '2023-07-29 04:46:50', '2022-04-28 14:36:41', 836796.43, 90715.98),
-- (15, 20, 19, '2022-03-31 23:00:21', '2021-10-13 04:08:55', 861367.63, 21366.10),
-- (16, 3, 20, '2023-04-26 05:00:25', '2022-04-25 17:26:47', 60821.42, 10425.08),
-- (17, 9, 2, '2023-06-19 02:54:44', '2021-07-17 07:48:31', 90262.54, 85051.96),
-- (18, 6, 19, '2022-05-11 14:01:02', '2021-12-06 09:04:22', 362140.68, 61687.45),
-- (19, 13, 11, '2023-07-24 16:53:39', '2022-08-04 00:50:07', 941215.56, 13159.57),
-- (20, 9, 9, '2022-02-14 13:18:40', '2021-12-26 01:35:11', 425994.36, 94523.82);




