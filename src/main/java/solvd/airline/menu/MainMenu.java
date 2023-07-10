package solvd.airline.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import solvd.airline.dataaccess.model.AirlineRoute.AirLineRoute;

import solvd.airline.dataaccess.model.AirlineRoute.AirLineRoutes;
import solvd.airline.dataaccess.model.Location.Location;
import solvd.airline.dataaccess.service.AirLineRouteMybatisService;
import solvd.airline.dataaccess.service.LocationMyBatisService;
import solvd.airline.output.JsonParser;
import solvd.airline.output.XmlParser;

public class MainMenu {
	
	private static final Logger logger = LogManager.getLogger(MainMenu.class);
	private static AirLineRouteMybatisService airLineRouteMybatisService = new AirLineRouteMybatisService();
	private static List<AirLineRoute> airlineRouteList = new ArrayList<>();
	
	public static void launchMainMenu() throws IOException {
		logger.info("\n\n*************");
		logger.info("* Main Menu *");
		logger.info("*************");
		logger.info("\n1. View All Routes");
		logger.info("\n2. Select Itinerary");
		logger.info("\n3. Exit");
		int response = RouteSelectionMenu.requestInt("\n\nEnter input :");

		switch(response) {
			case 1 : printAirlineRouteList();
				JsonParser.saveDataToJson(airlineRouteList, "JsonairlineRoutes.json");
				XmlParser.saveListToXml(airlineRouteList, "XmlairlineRoutes.xml", AirLineRoutes.class);
				launchMainMenu();
				break;
			case 2 : RouteSelectionMenu.launch(); break;
			case 3 : logger.info("\nBye for now !!"); break;
			default : logger.info("\nPlease enter a valid input..."); launchMainMenu();
		}
	}

	private static void printAirlineRouteList() {
		logger.info("\n---  Displaying All Routes  ---\n");
		airlineRouteList = getAllAirlineRoutes();
		airlineRouteList.forEach(System.out::println);
	}
	
	private static List<AirLineRoute> getAllAirlineRoutes() {
		return airLineRouteMybatisService.getAllRoutes();
	}
	
//	private static void checkRoutes() {
//		try {
//			// Print all the routes
//			logger.info("\n--------Display--AlL--Routes--------------");
//			airlineRouteList.forEach(System.out::println);
//			System.out.println();
//
//			logger.info("\n--------Display--All--Locations-----------");
//			locationList.forEach(System.out::println);
//			System.out.println();
//
//			int originId = requestPrompt("\nPlease enter your current location id:");
//			int destinationId = requestPrompt("\nPlease enter your destination location id:");
//
//			logger.info("\n-----------------------------------------");
//			logger.info("Please choose your route:");
//			logger.info("1. Fastest route");
//			logger.info("2. Cheapest route");
//			logger.info("\n-----------------------------------------");
//
//			int option = scanner.nextInt();
//
//			if (option == 1) {
//				routeSelectionMenuHelper.getItineraryQueryResult(originId, destinationId);// replace after fastest route
//			}
//			else if (option == 2){
//				routeSelectionMenuHelper.getItineraryQueryResult(originId, destinationId);// replace after cheapest route
//			}
//			else {
//				logger.info("Invalid option, please enter 1 for fastest route or 2 for cheapest route.");
//			}
//		} catch (Exception e) {
//			logger.error("An error occurred during route selection", e);
//			logger.info("An error occurred, please try again.");
//		}
//	}
//
//	public static int requestPrompt(String prompt) {
//		logger.info(prompt);
//		int id = scanner.nextInt();
//		return id;
//	}
}