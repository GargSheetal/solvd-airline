package solvd.airline.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import solvd.airline.dataaccess.model.AirlineRoute.AirLineRoute;

import solvd.airline.dataaccess.model.Location.Location;
import solvd.airline.dataaccess.service.AirLineRouteMybatisService;
import solvd.airline.dataaccess.service.LocationMyBatisService;

public class RouteSelectionMenu {
	
	private static final Logger logger = LogManager.getLogger(RouteSelectionMenu.class);
	private static Scanner scanner = new Scanner(System.in);
	private static RouteSelectionMenuHelper routeSelectionMenu;
	private static List<Location> locationList = new ArrayList<>();
	private static List<AirLineRoute> airlineRouteList = new ArrayList<>();


	public static void launch() {
		try {
			loadData();
			routeSelectionMenu = new RouteSelectionMenuHelper(locationList, airlineRouteList);

			int option = 0;
			do {
				try {
					logger.info("Please choose an option:");
					logger.info("1. Check routes");
					logger.info("2. Exit");
					option = scanner.nextInt();

					if (option == 1) {
						checkRoutes();
					} else if (option != 2) {
						logger.info("Invalid option, please enter 1 to check routes or 2 to exit.");
					}
				} catch (Exception e) {
					logger.error("An error occurred during user input", e);
					logger.info("Invalid input, please try again.");
				}
			} while (option != 2);
		} catch (IOException e) {
			logger.error("An error occurred while launching the route selection menu", e);
		}
	}

	private static void checkRoutes() {
		try {
			// Print all the routes
			airlineRouteList.forEach(System.out::println);
			System.out.println();

			// Print all the locations
			locationList.forEach(System.out::println);
			System.out.println();

			int originId = requestPrompt("Please enter your current location id:");
			int destinationId = requestPrompt("Please enter your destination location id:");

			logger.info("Please choose an option:");
			logger.info("1. Fastest route");
			logger.info("2. Cheapest route");

			int option = scanner.nextInt();

			if (option == 1 || option == 2) {
				routeSelectionMenu.getItineraryQueryResult(originId, destinationId);
			} else {
				logger.info("Invalid option, please enter 1 for fastest route or 2 for cheapest route.");
			}
		} catch (Exception e) {
			logger.error("An error occurred during route selection", e);
			logger.info("An error occurred, please try again.");
		}
	}

	public static int requestPrompt(String prompt) {
		logger.info(prompt);
		int id = scanner.nextInt();
		return id;
	}

	private static void loadData() throws IOException {
		LocationMyBatisService locationService = new LocationMyBatisService();
		AirLineRouteMybatisService airlineRouteService = new AirLineRouteMybatisService();

		try {
			locationList = locationService.getAllLocations();
			airlineRouteList = airlineRouteService.getAllRoutes();
		} catch (Exception e) {
			logger.error("Error loading data from the database", e);
			throw new RuntimeException("Failed to load data", e);
		} finally {
			locationService.closeSession();
			airlineRouteService.closeSession();
		}
	}
}