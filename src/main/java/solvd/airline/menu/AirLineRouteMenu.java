package solvd.airline.menu;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import solvd.airline.dataaccess.model.AirlineRoute.AirLineRoute;
import solvd.airline.dataaccess.model.AirlineRoute.AirLineRouteMapper;
import solvd.airline.dataaccess.model.Location.Location;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solvd.airline.main.Main;

public class AirLineRouteMenu {

    private final SqlSessionFactory sqlSessionFactory;
    private static final Logger logger = LogManager.getLogger(Main.class);

    public AirLineRouteMenu(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void start() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AirLineRouteMapper mapper = session.getMapper(AirLineRouteMapper.class);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                try {
                    logger.info("---------------------------------------------------");
                    logger.info("Select an option:");
                    logger.info("1. View all routes");
                    logger.info("2. View a specific route");
                    logger.info("3. Add a new route");
                    logger.info("4. Update a route");
                    logger.info("5. Delete a route");
                    logger.info("6. Exit");
                    logger.info("---------------------------------------------------");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1 -> viewAllRoutes(mapper);

                        case 2 -> viewSpecificRoute(mapper, scanner);

                        case 3 -> {
                            AirLineRoute route = createRouteFromUserInput(scanner);
                            mapper.addRoute(route);
                            session.commit();
                            viewAllRoutes(mapper);
                            logger.info("\n>>> New Route has been successfully added!");
                        }

                        case 4 -> {
                            viewAllRoutes(mapper);
                            logger.info("\nEnter route ID to update:");
                            int routeId = scanner.nextInt();
                            AirLineRoute route = mapper.getRoute(routeId);
                            updateRouteFromUserInput(route, scanner);
                            mapper.updateRoute(route);
                            session.commit();
                            viewAllRoutes(mapper);
                            logger.info("\n>>> Route has been successfully updated!");
                        }
                        case 5 -> {
                            viewAllRoutes(mapper);
                            logger.info("\nEnter route ID to delete:");
                            int routeId = scanner.nextInt();
                            mapper.deleteRoute(routeId);
                            session.commit();
                            viewAllRoutes(mapper);
                            logger.info("\n>>> Route has been successfully deleted!");
                        }
                        case 6 -> {
                            return;
                        }
                        default -> logger.info("Invalid choice, please try again.");
                    }
                } catch (InputMismatchException e) {
                    logger.info("Invalid input. Please enter a number.");
                    scanner.nextLine();
                } catch (PersistenceException e) {
                    logger.info("Database operation failed: " + e.getMessage());
                }
            }
        }
    }

    private AirLineRoute createRouteFromUserInput(Scanner scanner) {
        logger.info("\n>>> Create New Route:");
        logger.info("Enter origin location ID:");
        int originLocationId = scanner.nextInt();
        logger.info("Enter destination location ID:");
        int destinationLocationId = scanner.nextInt();
        logger.info("Enter distance in miles:");
        int distance = scanner.nextInt();
        logger.info("Enter price in dollars:");
        double price = scanner.nextDouble();

        Location originLocation = new Location();
        originLocation.setLocationId(originLocationId);

        Location destinationLocation = new Location();
        destinationLocation.setLocationId(destinationLocationId);

        AirLineRoute route = new AirLineRoute();
        route.setOriginLocation(originLocation);
        route.setDestinationLocation(destinationLocation);
        route.setDistanceMiles(distance);
        route.setPriceDollars(price);

        return route;
    }

    private void updateRouteFromUserInput(AirLineRoute route, Scanner scanner) {
        logger.info("\n>>> Update Route:");
        logger.info("Enter new distance in miles:");
        int distance = scanner.nextInt();
        logger.info("Enter new price in dollars:");
        double price = scanner.nextDouble();

        route.setDistanceMiles(distance);
        route.setPriceDollars(price);
    }

    private void viewAllRoutes(AirLineRouteMapper mapper) {
        logger.info("\n>>> All Airline Routes:");
        List<AirLineRoute> routes = mapper.getAllRoutes();
        routes.forEach(System.out::println);
    }

    private void viewSpecificRoute(AirLineRouteMapper mapper, Scanner scanner) {
        logger.info("\nEnter route ID:");
        int routeId = scanner.nextInt();
        AirLineRoute route = mapper.getRoute(routeId);
        logger.info("\n>>> Selected Route Details:" + route);
    }

}
