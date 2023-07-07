package solvd.airline.menu;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import solvd.airline.dataaccess.model.AirlineRoute.AirLineRoute;
import solvd.airline.dataaccess.model.AirlineRoute.AirLineRouteMapper;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solvd.airline.main.Main;

public class AirLineRouteMenuHelper {

    private final SqlSessionFactory sqlSessionFactory;
    private static final Logger logger = LogManager.getLogger(Main.class);

    public AirLineRouteMenuHelper(SqlSessionFactory sqlSessionFactory) {
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
                    logger.info("3. Exit");
                    logger.info("---------------------------------------------------");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1 -> viewAllRoutes(mapper);

                        case 2 -> viewSpecificRoute(mapper, scanner);

                        case 3 -> {
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
