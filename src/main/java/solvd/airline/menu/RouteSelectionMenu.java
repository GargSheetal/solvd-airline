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
		logger.info("\n\nItinerary Query Result : -->\n" + itineraryQueryResult.toString());
		routeSelectionInput(itineraryQueryResult);
	}
	
	private static ItineraryQueryResult queryItinerary() {
		int originLocationId = requestInt("\nEnter Origin Location Id :");
		int destinationLocationId = requestInt("Enter Destination Location Id :");
		ItineraryQueryResult itineraryQueryResult = routeSelectionMenuHelper.getItineraryQueryResult(originLocationId, destinationLocationId);	
		return itineraryQueryResult;
	}
	
	private static int requestInt(String prompt) {
		logger.info(prompt);
		int id = scanner.nextInt();
		return id;
	}
	
	private static void routeSelectionInput(ItineraryQueryResult itineraryQueryResult){
		logger.info("\n\n ************ Presenting Route Selection Menu ************ ");
		logger.info("\n 1. Select Cheapest Route");
		logger.info("\n 2. Select Shortest Route");
		logger.info("\n 3. Go back to Main Menu");
		logger.info("\n 4. Exit");
		int selectedRoute = requestInt("\nSelect a Route :");
		
		switch(selectedRoute) {
		case 1 : logger.info(itineraryQueryResult.toStringCheapest()); break;
		case 2 : logger.info(itineraryQueryResult.toStringShortest()); break;
		case 3 : System.out.println("\nGo back to main menu - to be implemented"); break;	// replace with mainMenu method (to be implemented)
		case 4 : System.out.println("\nYou are exiting the app..."); break;
		default : System.out.println("\nPlease enter a valid input..."); routeSelectionInput(itineraryQueryResult);
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
