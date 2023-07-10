USE `solvd-airline` ;
INSERT INTO `solvd-airline`.`locations` (location_name) VALUES 
('Atlanta'), 
('Chicago'), 
('Detroit'), 
('New York'), 
('Los Angeles'),
('San Francisco'),
('Dallas'),
('Miami'),
('Houston'),
('Phoenix');


INSERT INTO `solvd-airline`.`airline_routes` 
(origin_location_id, destination_location_id, distance_miles, price) 
VALUES 
(1, 2, 600, 80), 
(2, 3, 300, 50), 
(3, 1, 800, 130), 
(1, 4, 900, 120), 
(2, 5, 1800, 220),
(5, 6, 400, 70),
(6, 2, 1900, 230),
(7, 1, 800, 110),
(7, 3, 1200, 150),
(8, 1, 700, 100),
(8, 7, 300, 50),
(8, 9, 1200, 150),
(9, 10, 1000, 130),
(10, 6, 800, 110),
(5, 9, 1500, 190);

SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE TABLE `solvd-airline`.`airline_routes`;
TRUNCATE TABLE `solvd-airline`.`locations`;
SET FOREIGN_KEY_CHECKS = 1;

UPDATE `solvd-airline`.`airline_routes` SET distance_miles=1600,price=300 WHERE airline_route_id=7;

DELETE FROM `solvd-airline`.`airline_routes`
WHERE (origin_location_id, destination_location_id) IN (
    (1, 5), (5, 8), (2, 6), (6, 9), (3, 7), (7, 10), 
    (4, 1), (4, 2), (9, 4), (10, 5), (1, 6), 
    (2, 7), (3, 8), (4, 9), (5, 10)
);

