package solvd.airline.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import solvd.airline.dataaccess.model.AirlineRoute.AirLineRoute;

import solvd.airline.dataaccess.model.Location.Location;

public class RouteSelectionMenu {
	
	private static final Logger logger = LogManager.getLogger(RouteSelectionMenu.class);
	private static Scanner scanner = new Scanner(System.in);
	private static RouteSelectionMenuHelper routeSelectionMenu;
	
	private static List<Location> locationList = new ArrayList<>();
	private static List<AirLineRoute> airlineRouteList = new ArrayList<>();
	
	public static void launch() throws IOException {
		loadData();
		routeSelectionMenu = new RouteSelectionMenuHelper(locationList, airlineRouteList);
//		AirLineRouteMybatisService airLineRouteMybatisService = new AirLineRouteMybatisService();
//		logger.info("Presenting All Routes : \n");
//		List<AirLineRoute> airlineRouteList = airLineRouteMybatisService.getAllRoutes();
//		airlineRouteList.forEach(airlineRoute -> System.out.println(airlineRoute));
		
		int originLocationId = requestPrompt("\nEnter Origin Location Id :");
		int destinationLocationId = requestPrompt("Enter Destination Location Id :");
		routeSelectionMenu.getItineraryQueryResult(originLocationId, destinationLocationId);	
	}
	
	public static int requestPrompt(String prompt) {
		logger.info(prompt);
		int id = scanner.nextInt();
		return id;
	}
	
	private static void loadData() {
		// to be replaced by LocationService
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
		// to be replaced by AirlineRouteService
		airlineRouteList.add(new AirLineRoute(1, miami, newyork, 500, 600));
		airlineRouteList.add(new AirLineRoute(2, newyork, dallas, 200, 300));
		airlineRouteList.add(new AirLineRoute(3, dallas, chicago, 100, 200));
		airlineRouteList.add(new AirLineRoute(4, chicago, atlanta, 400, 500));
		airlineRouteList.add(new AirLineRoute(5, atlanta, miami, 300, 400));
		airlineRouteList.add(new AirLineRoute(6, chicago, newyork, 600, 700));
	}
}
