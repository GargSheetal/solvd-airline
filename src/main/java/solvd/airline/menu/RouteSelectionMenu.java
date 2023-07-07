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
import solvd.airline.output.JsonParser;

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
		routeSelectionInput(itineraryQueryResult);
	}
	
	private static ItineraryQueryResult queryItinerary() {
		System.out.println("\n\n");
		System.out.println("*******************");
		System.out.println("* Query Itinerary *");
		System.out.println("*******************");
		System.out.println("\n-- Input Options --\n");
		printLocationList();	// to provide input options to the user
		JsonParser.saveDataToJson(locationList, "sampleOutput.json");       
		int originLocationId = requestInt("\nEnter Origin Location Id :");
		int destinationLocationId = requestInt("Enter Destination Location Id :");
		ItineraryQueryResult itineraryQueryResult = routeSelectionMenuHelper.getItineraryQueryResult(originLocationId, destinationLocationId);	
		return itineraryQueryResult;
	}
	
	private static void printLocationList() {
		locationList.forEach(location -> System.out.println(location));
	}
	
	private static int requestInt(String prompt) {
		logger.info(prompt);
		int id = scanner.nextInt();
		return id;
	}
	
	private static void routeSelectionInput(ItineraryQueryResult itineraryQueryResult){
		System.out.println("\n");
		System.out.println("********************");
		System.out.println("* Select Itinerary *");
		System.out.println("********************");
		logger.info("\n 1. Select Cheapest Itinerary");
		logger.info("\n 2. Select Shortest Itinerary");
		logger.info("\n 3. Go back to Main Menu");
		logger.info("\n 4. Exit");
		int selectedRoute = requestInt("\nEnter input :");
		
		switch(selectedRoute) {
		case 1 : logger.info("\n-- Selected Itinerary --\n\n" + itineraryQueryResult.toStringCheapest());
				 logger.info("\n\nGo back to main menu - to be implemented"); break;
		case 2 : logger.info("\n-- Selected Itinerary --\n\n" + itineraryQueryResult.toStringShortest());
				 logger.info("\n\nGo back to main menu - to be implemented"); break;
		case 3 : logger.info("\nGo back to main menu - to be implemented"); break;	// replace with mainMenu method (to be implemented)
		case 4 : logger.info("\nYou are exiting the app..."); break;
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
		
		locationList.add(miami);
		locationList.add(newyork);
		locationList.add(dallas);
		locationList.add(chicago);
		locationList.add(atlanta);
		
		airlineRouteList.add(new AirLineRoute(1, miami, newyork, 500, 600));
		airlineRouteList.add(new AirLineRoute(2, newyork, dallas, 200, 300));
		airlineRouteList.add(new AirLineRoute(3, dallas, chicago, 100, 200));
		airlineRouteList.add(new AirLineRoute(4, chicago, atlanta, 400, 500));
		airlineRouteList.add(new AirLineRoute(5, atlanta, miami, 300, 400));
		airlineRouteList.add(new AirLineRoute(6, chicago, newyork, 600, 700));
	}
	
}
