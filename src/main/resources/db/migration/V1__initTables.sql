CREATE TYPE car_body_type AS ENUM('Coupe', 'Sedan', 'SUV', 'Station_Wagon', 'Hatchback', 'Cabriolet');
CREATE TYPE car_type_of_fuel AS ENUM('Benzine', 'Diesel', 'Electric', 'LPG');

CREATE TABLE IF NOT EXISTS CARS(
    ID BIGINT NOT NULL,
    brand VARCHAR(20),
    model VARCHAR(20),
    engine_capacity numeric(2,1),
    body_type car_body_type NOT NULL,
    type_of_fuel car_type_of_fuel NOT NULL,
    new_car_cost numeric(9,2),
    production_year int NOT NULL,
    CONSTRAINT cars_id_pkey PRIMARY KEY(ID)
    );

CREATE TABLE IF NOT EXISTS USERS(
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

CREATE TYPE reservation_status AS ENUM('Free', 'Reserved', 'Taken');

CREATE TABLE IF NOT EXISTS RESERVATIONS(
    ID BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    car_id BIGINT NOT NULL,
    date_start timestamp without time zone, --if car is free then that column cannot be NOT NULL
    date_end timestamp without time zone, --if car is free then that column cannot be NOT NULL
    cost numeric(7,2) NOT NULL,
    payment_in_advance numeric(5,2) NOT NULL,
    status reservation_status NOT NULL,
    CONSTRAINT reservations_id_pkey PRIMARY KEY(ID),
    CONSTRAINT user_id_pkey FOREIGN KEY(user_id) REFERENCES users(ID),
    CONSTRAINT car_id_pkey FOREIGN KEY(car_id) REFERENCES cars(ID)
    );

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