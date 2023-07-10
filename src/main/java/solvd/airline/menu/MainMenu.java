package solvd.airline.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import solvd.airline.dataaccess.model.AirlineRoute.AirLineRoute;

import solvd.airline.dataaccess.model.AirlineRoute.AirLineRoutes;
import solvd.airline.dataaccess.service.AirLineRouteMybatisService;
import solvd.airline.output.JsonParser;
import solvd.airline.output.XmlParser;

public class MainMenu {
	
	private static final Logger logger = LogManager.getLogger(MainMenu.class);
	private static Scanner scanner = new Scanner(System.in);
	private static AirLineRouteMybatisService airLineRouteMybatisService = new AirLineRouteMybatisService();
	private static List<AirLineRoute> airlineRouteList = new ArrayList<>();
	
	public static void launchMainMenu() throws IOException {
		logger.info("\n\n*************");
		logger.info("* Main Menu *");
		logger.info("*************");
		logger.info("\n1. View All Routes");
		logger.info("\n2. Select Itinerary");
		logger.info("\n3. Exit");
		int response = requestInt("\n\nEnter input :");

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
	
	public static int requestInt(String prompt) {
		logger.info(prompt);
		int id = scanner.nextInt();
		return id;
	}
}