INSERT INTO `solvd-airline`.`locations` (location_name) VALUES
('Atlanta'),
('Chicago'),
('Detroit'),
('New York'),
('Los Angeles');


INSERT INTO `solvd-airline`.`airline_routes`
(origin_location_id, destination_location_id, distance_miles, price)
VALUES
(1, 2, 300, 300),
(3, 2, 600, 500),
(3, 1, 1000, 750),
(1, 4, 2000, 1200),
(2, 5, 2500, 1500);
