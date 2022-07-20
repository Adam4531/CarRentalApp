--INSERT INTO roles(ID, name) values(1, 'ROLE_USER'),(2, 'ROLE_EMPLOYEE'),(3, 'ROLE_ADMIN');

insert into public.CARS(ID, brand, model, engine_capacity, body_type, type_of_fuel, new_car_cost, production_year) values
    (1, 'GMC', 'Envoy XL', 4.2, 'SUV', 'BENZINE', 2583355.15, 2002),
    (2, 'Chevrolet', 'Impala SS', 3.2, 'SEDAN', 'BENZINE', 2794112.89, 1995),
    (3, 'Lexus', 'RX', 3.0,'COUPE', 'BENZINE', 242138.12, 2005),
    (4, 'Chevrolet', '3500', 3.6, 'SEDAN' ,'BENZINE', 1169492.47, 2000),
    (5, 'Hyundai', 'Sonata', 1.7, 'SEDAN', 'DIESEL', 619185.83, 1998),
    (6, 'Lexus', 'LS', 2.2, 'COUPE', 'DIESEL', 1167804.06, 2001),
    (7, 'GMC', 'Suburban 1500', 4.2, 'SUV', 'BENZINE', 2070876.68, 1998),
    (8, 'Audi', 'A4', 3.0, 'STATION_WAGON', 'DIESEL', 313313.89, 2005),
    (9, 'Audi', 'Q8', 3.0, 'SUV', 'BENZINE', 1938005.44, 2009),
    (10, 'Lexus', 'IS-F', 3.0, 'COUPE', 'BENZINE', 2552772.81, 2009),
    (11, 'Mitsubishi', 'Pajero', 1.2, 'HATCHBACK', 'LPG', 411653.59, 2002),
    (12, 'Chrysler', 'Grand Voyager',2.8, 'SUV', 'LPG', 356890.01, 2000),
    (13, 'Chrysler', 'Pacifica', 3.4, 'SUV', 'LPG', 2341958.83, 2007),
    (14, 'Subaru', 'Legacy', 4.0, 'SEDAN', 'BENZINE', 1736392.40, 2001),
    (15, 'Nissan', 'Pathfinder', 2.0,'HATCHBACK', 'BENZINE', 2664744.42, 2004),
    (16, 'GMC', '1500', 4.2, 'SUV', 'BENZINE', 944749.93, 1998),
    (17, 'Mercedes-Benz','R-Class', 5.2, 'COUPE', 'BENZINE', 1979299.45, 2010),
    (18, 'GMC', 'Yukon XL 1500', 4.2, 'SUV', 'BENZINE', 1920072.05, 2000),
    (19, 'Oldsmobile', '88', 1.7, 'STATION_WAGON', 'DIESEL', 2966425.83, 1992),
    (20, 'Buick', 'LaCrosse', 2.4, 'SEDAN', 'BENZINE' , 95234.57, 2008);

-- zakodowane dlatego, ze nie da sie wrzucic tych rezerwacji pod te user_id, bo userzy
-- z tym id nie istnieją
--insert into public.RESERVATIONS (id, user_id, car_id, date_start, date_end, cost, payment_in_advance) values
--      (1, 16, 14, '2021-05-14 17:25:58', '2022-04-02 11:23:40', 26083, 3280.88),
--      (2, 9, 4, '2022-02-18 20:11:20', '2022-04-04 12:31:29', 70936, 1314.96),
--      (3, 6, 2, '2022-04-02 04:13:12', '2021-10-31 20:26:12', 18851, 6050.41),
--      (4, 6, 3, '2021-04-14 01:35:34', '2022-12-17 02:44:11', 79695, 9845.44),
--      (5, 3, 8, '2021-11-12 01:51:52', '2021-09-25 03:02:12', 13768, 1182.0),
--      (6, 7, 2, '2021-11-23 21:34:19', '2022-10-19 11:19:46', 72251, 3218.07),
--      (7, 8, 11, '2021-11-27 04:03:02', '2022-05-28 21:42:46', 35446, 4355.3),
--      (8, 17, 15, '2021-05-06 04:55:14', '2021-08-07 20:45:23', 60303, 6178.04),
--      (9, 12, 5, '2021-06-09 04:33:42', '2022-09-17 14:51:54', 29562, 7630.64),
--      (10, 19, 8, '2022-04-05 01:40:06', '2022-06-27 00:12:33', 61605, 7072.67),
--      (11, 5, 12, '2021-03-14 05:59:05', '2022-09-10 06:48:38', 5232, 8574.09),
--      (12, 17, 20, '2021-11-06 14:35:22', '2022-10-02 03:50:52', 51610, 747.2),
--      (13, 7, 11, '2021-05-20 01:30:03', '2022-02-01 13:53:25', 91682, 8733.28),
--      (14, 8, 8, '2022-02-02 01:56:06', '2022-09-02 17:55:46', 69607, 3523.24),
--      (15, 2, 15, '2021-03-01 13:59:31', '2021-09-02 21:48:57', 6818, 6568.28),
--      (16, 17, 6, '2021-11-30 20:06:35', '2022-05-02 14:12:18', 29601, 526.12),
--      (17, 19, 19, '2021-09-25 08:08:56', '2021-12-15 14:02:32', 60384, 5196.2),
--      (18, 3, 1, '2022-01-20 13:41:04', '2022-11-02 03:49:04', 84651, 9412.77),
--      (19, 5, 13, '2021-06-09 11:27:36', '2021-09-22 15:00:06', 13499, 1880.99),
--      (20, 2, 5, '2021-04-08 17:14:19', '2021-11-06 11:05:40', 43447, 1324.83);

