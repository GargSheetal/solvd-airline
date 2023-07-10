package solvd.airline.menu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import solvd.airline.dataaccess.model.AirlineRoute.AirLineRoute;

import solvd.airline.dataaccess.model.Location.Location;
import solvd.airline.dataaccess.model.Location.Locations;
import solvd.airline.dataaccess.service.AirLineRouteMybatisService;
import solvd.airline.dataaccess.service.LocationMyBatisService;
import solvd.airline.exceptions.InvalidInputException;
import solvd.airline.itinerary.ItineraryQueryResult;
import solvd.airline.output.JsonParser;
import solvd.airline.output.XmlParser;

public class RouteSelectionMenu {
	
	private static final Logger logger = LogManager.getLogger(RouteSelectionMenu.class);
	private static Scanner scanner = new Scanner(System.in);
	private static RouteSelectionMenuHelper routeSelectionMenuHelper;
	private static LocationMyBatisService locationMyBatisService = new LocationMyBatisService();
	private static AirLineRouteMybatisService airLineRouteMybatisService = new AirLineRouteMybatisService();
	
	private static List<Location> locationList = new ArrayList<>();
	private static List<AirLineRoute> airlineRouteList = new ArrayList<>();
	
	public static void launch() throws IOException {
		loadData();
//		loadTestData();
		routeSelectionMenuHelper = new RouteSelectionMenuHelper(locationList, airlineRouteList);
		ItineraryQueryResult itineraryQueryResult = queryItinerary();
		logger.info("\n\n-- Query Result --\n\n" + itineraryQueryResult.toString());
		JsonParser.saveDataToJson(itineraryQueryResult, "itineraryQueryResult.json");
		XmlParser.saveListToXml(Collections.singletonList(itineraryQueryResult), "itineraryQueryResult.xml", ItineraryQueryResult.class);
		routeSelectionInput(itineraryQueryResult);
	}
	
	public static void refreshItineraryQueryResult(int originLocationId, int destinationLocationId) throws IOException {
		logger.info("\n... reloading data from Database");
		loadData();
//		loadTestData();
		routeSelectionMenuHelper = new RouteSelectionMenuHelper(locationList, airlineRouteList);
		ItineraryQueryResult itineraryQueryResult = routeSelectionMenuHelper.getItineraryQueryResult(originLocationId, destinationLocationId);
		logger.info("\n\n-- Query Result --\n\n" + itineraryQueryResult.toString());
		JsonParser.saveDataToJson(itineraryQueryResult, "itineraryQueryResult.json");
		XmlParser.saveListToXml(Collections.singletonList(itineraryQueryResult), "itineraryQueryResult.xml", ItineraryQueryResult.class);
		routeSelectionInput(itineraryQueryResult);
	}
	
	private static ItineraryQueryResult queryItinerary() {
		System.out.println("\n\n");
		System.out.println("*******************");
		System.out.println("* Query Itinerary *");
		System.out.println("*******************");
		System.out.println("\n-- Input Options --\n");
		printLocationList();	// to provide input options to the user
		JsonParser.saveDataToJson(locationList, "locations.json"); 
		XmlParser.saveListToXml(locationList, "locations.xml", Locations.class);
		
		int originLocationId = requestLocationIdInput("\nEnter Origin Location Id :");
		int destinationLocationId = requestLocationIdInput("Enter Destination Location Id :");
		ItineraryQueryResult itineraryQueryResult = routeSelectionMenuHelper.getItineraryQueryResult(originLocationId, destinationLocationId);
		return itineraryQueryResult;
	}
	
	private static void printLocationList() {
		locationList.forEach(location -> System.out.println(location));
	}
	
	public static void printAilrlineRouteList() {
		logger.info("\n***** Displaying All Routes *****");
		airlineRouteList.forEach(airlineRoute -> System.out.println(airlineRoute));
	}
	
	public static int requestInt(String prompt) {
		logger.info(prompt);
		int id = scanner.nextInt();
		return id;
	}
	
	private static int requestLocationIdInput(String prompt) {
		int inputId = requestInt(prompt);
		try {
			if(!(locationList.contains(new Location(inputId)))) {
				throw new InvalidInputException("\nPlease enter a valid input...");
			}
		} catch(InvalidInputException e) {
			logger.info("\nInvalidInputException : " + e.getMessage());
			inputId = requestLocationIdInput(prompt);
		}
		return inputId;
	}
	
	private static void routeSelectionInput(ItineraryQueryResult itineraryQueryResult) throws IOException{
		System.out.println("\n");
		System.out.println("********************");
		System.out.println("* Select Itinerary *");
		System.out.println("********************");
		logger.info("\n 1. Select Cheapest Itinerary");
		logger.info("\n 2. Select Shortest Itinerary");
		logger.info("\n 3. Refresh Itinerary");
		logger.info("\n 4. Go back to Main Menu");
		logger.info("\n 5. Exit");
		int selectedRoute = requestInt("\nEnter input :");
		
		switch(selectedRoute) {
		case 1 : logger.info("\n-- Selected Itinerary --\n\n" + itineraryQueryResult.toStringCheapest());
				 JsonParser.saveDataToJson(itineraryQueryResult.getCheapestItinerary(), "selectedCheapestItinerary.json");
				 XmlParser.writeXml(new File("src/main/resources/output/selectedCheapestItinerary.xml"), itineraryQueryResult.getCheapestItinerary());
				 MainMenu.launchMainMenu(); break;
		case 2 : logger.info("\n-- Selected Itinerary --\n\n" + itineraryQueryResult.toStringShortest());
				 JsonParser.saveDataToJson(itineraryQueryResult.getShortestItinerary(), "selectedShortestItinerary.json");
				 XmlParser.writeXml(new File("src/main/resources/output/selectedShortestItinerary.xml"), itineraryQueryResult.getShortestItinerary());
				 MainMenu.launchMainMenu(); break;
		case 3 : refreshItineraryQueryResult(itineraryQueryResult.getOriginLocation().getLocationId(), itineraryQueryResult.getDestinationLocation().getLocationId()); break; // implement refresh logic
		case 4 : MainMenu.launchMainMenu(); break;	// replace with mainMenu method (to be implemented)
		case 5 : logger.info("\nBye for now !!"); break;
		default : logger.info("\nPlease enter a valid input..."); routeSelectionInput(itineraryQueryResult);
		}
	}
	
	private static void loadData() {
		locationList = locationMyBatisService.getAllLocations();
		airlineRouteList = airLineRouteMybatisService.getAllRoutes();
	}
	
	private static void loadTestData() {
		Location miami = new Location(10, "Miami");
		Location newyork = new Location(20, "Newyork");
		Location dallas = new Location(30, "Dallas");
		Location chicago = new Location(40, "Chicago");
		Location atlanta = new Location(50, "Atlanta");
		
		locationList.clear();
		locationList.add(miami);
		locationList.add(newyork);
		locationList.add(dallas);
		locationList.add(chicago);
		locationList.add(atlanta);
		
		airlineRouteList.clear();
		airlineRouteList.add(new AirLineRoute(1, miami, newyork, 500, 600));
		airlineRouteList.add(new AirLineRoute(2, newyork, dallas, 200, 200));
		airlineRouteList.add(new AirLineRoute(3, dallas, chicago, 100, 900));
		airlineRouteList.add(new AirLineRoute(4, chicago, atlanta, 400, 500));
		airlineRouteList.add(new AirLineRoute(5, atlanta, miami, 300, 400));
		airlineRouteList.add(new AirLineRoute(6, chicago, newyork, 600, 500));
		airlineRouteList.add(new AirLineRoute(7, miami, atlanta, 600, 900));
		airlineRouteList.add(new AirLineRoute(8, miami, chicago, 400, 300));
	}
}
