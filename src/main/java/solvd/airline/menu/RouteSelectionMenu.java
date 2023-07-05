package solvd.airline.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import solvd.airline.dataaccess.strategy.CheapestRouteSelectionStrategy;
import solvd.airline.dataaccess.strategy.RouteSelectionSystem;
import solvd.airline.dataaccess.strategy.ShortestRouteSelectionStrategy;


public class RouteSelectionMenu {
	
	private static final Logger logger = LogManager.getLogger(RouteSelectionMenu.class);
	private static Scanner scanner = new Scanner(System.in);
	private final int INF = Integer.MAX_VALUE;	// Infinity value for unreachable routes
	
	private List<Location> locationList;
	private Location[] locations;
	private String[] locationNames;
	private List<AirlineRoute> airlineRouteList;
	private int numLocations;
	
	private int[][] distance;
	private double[][] price;
	private int[][] nextLocationIdx;
	
	private RouteSelectionSystem cheapestRoute = new RouteSelectionSystem();
	private RouteSelectionSystem shortestRoute = new RouteSelectionSystem();
	
	public RouteSelectionMenu() {
		setLocationList();
		setAirlineRouteList();
		numLocations = locationList.size();
		locations = locationList.toArray(new Location[numLocations]);
		locationNames = getLocationNames(locations);
		
		distance = new int[numLocations][numLocations];
		price = new double[numLocations][numLocations];
		nextLocationIdx = new int[numLocations][numLocations];
		setRouteMatrix();
		
		cheapestRoute.setRouteSelectionStrategy(new CheapestRouteSelectionStrategy(numLocations, distance, price, nextLocationIdx));
		shortestRoute.setRouteSelectionStrategy(new ShortestRouteSelectionStrategy(numLocations, distance, price, nextLocationIdx));
	}

	// to be replaced by LocationService
	private void setLocationList() {
		locationList.add(new Location(10, "Miami"));
		locationList.add(new Location(20, "Newyork"));
		locationList.add(new Location(30, "Dallas"));
		locationList.add(new Location(40, "Chicago"));
		locationList.add(new Location(50, "Atlanta"));
	}
	
	// to be replaced by AirlineRouteService
	private void setAirlineRouteList() {
		airlineRouteList.add(new AirlineRoute(1, 10, 20, 500, 600));
		airlineRouteList.add(new AirlineRoute(2, 20, 30, 200, 300));
		airlineRouteList.add(new AirlineRoute(3, 30, 40, 100, 200));
		airlineRouteList.add(new AirlineRoute(4, 40, 50, 400, 500));
		airlineRouteList.add(new AirlineRoute(5, 50, 10, 300, 400));
		airlineRouteList.add(new AirlineRoute(6, 40, 20, 600, 700));
	}
	
	private void setRouteMatrix() {
		// Initialize distance, price, and nextLocationIdx matrices
		for(int i=0; i<numLocations; i++) {
			Arrays.fill(distance[i], INF);
			Arrays.fill(price[i], INF);
			Arrays.fill(nextLocationIdx[i], -1);
		}
		
		// Populate distance, price, and nextLocationIdx matrices with input data
		for(AirlineRoute route: airlineRouteList) {
			int airlineLocationId = route.getAirlineRouteId();
			int originLocationId = route.getOriginLocationId();
			int destinationLocationId = route.getDestinationLocationId();
			int distanceMiles = route.getDistanceMiles();
			double priceDollars = route.getPriceDollars();
			
			int originLocationIdx = getLocationIndex(originLocationId);
			int destinationLocationIdx = getLocationIndex(destinationLocationId);
			
			distance[originLocationIdx][destinationLocationIdx] = distanceMiles;
			distance[destinationLocationIdx][originLocationIdx] = distanceMiles;
			
			price[originLocationIdx][destinationLocationIdx] = priceDollars;
			price[destinationLocationIdx][originLocationIdx] = priceDollars;
			
			nextLocationIdx[originLocationIdx][destinationLocationIdx] = destinationLocationIdx;
			nextLocationIdx[destinationLocationIdx][originLocationIdx] = destinationLocationIdx;  // ckeck this for debugging
		}
	}
	
	private int getLocationIndex(int locationId) {
		for(int i=0; i<locations.length; i++) {
			if(locations[i].getLocationId() == locationId) {
				return i;
			}
		}
		return -1;	// location not found
	}
	
	private static void printMatrix(String[] header, int[][] matrix, String label) {
		String[][] display = new String[matrix.length + 1][matrix[0].length + 1];
		display[0][0] = String.format("%-15s", "");

		for (int s = 0; s < header.length; s++) {
			display[0][s+1] = String.format("%-15s", header[s]);
			display[s+1][0] = String.format("%-15s", header[s]);
		}

		for (int mrow = 0; mrow < matrix.length; mrow++) {
			for (int mcol = 0; mcol < matrix[0].length; mcol++) {
				display[mrow + 1][mcol + 1] = String.format("%-15s", Integer.toString(matrix[mrow][mcol]));
			}
		}

		System.out.println("\n" + label + " : -->");    

		for (int i = 0; i < display.length; i++) {
			for (int j = 0; j < display[i].length; j++) {
				System.out.print(display[i][j]);
			}
			System.out.println();
		}
	}
	
	private String[] getLocationNames(Location[] locations) {
		String[] locationNames = new String[locations.length];
		for(int i=0; i<locations.length; i++) {
			locationNames[i] = locations[i].getLocationName();
		}
		return locationNames;
	}
	
	public void findRoutes(int inputOriginLocationId, int inputDestinationLocationId) {
		int inputOriginLocationIdx = getLocationIndex(inputOriginLocationId);
		int inputDestinationLocationIdx = getLocationIndex(inputOriginLocationIdx);
	}
}


// to be replaced by respective model class
class AirlineRoute {

	private int airlineRouteId;
	private int originLocationId;
	private int destinationLocationId;
	private int distanceMiles;
	private int priceDollars;

	public AirlineRoute(int airlineRouteId, int originLocationId, int destinationLocationId, int distanceMiles, int priceDollars) {

		this.airlineRouteId = airlineRouteId;
		this.originLocationId = originLocationId;
		this.destinationLocationId = destinationLocationId;
		this.distanceMiles = distanceMiles;
		this.priceDollars = priceDollars;
	}

	public int getAirlineRouteId() {
		return airlineRouteId;
	}

	public int getOriginLocationId() {
		return originLocationId;
	}

	public int getDestinationLocationId() {
		return destinationLocationId;
	}

	public int getDistanceMiles() {
		return distanceMiles;
	}

	public int getPriceDollars() {
		return priceDollars;
	}
}

//to be replaced by respective model class
class Location {

	private int locationId;
	private String locationName;

	public Location(int locationId, String locationName) {
		this.locationId = locationId;
		this.locationName = locationName;
	}

	public int getLocationId() {
		return locationId;
	}

	public String getLocationName() {
		return locationName;
	}

}

class QueryOutput {
	
	private int originLocationId;
	private int destinationLocationId;
	private Itinerary cheapestItinerary;
	private Itinerary shortestItinerary;
	
	public QueryOutput(int originLocationId, int destinationLocationId, Itinerary cheapestItinerary,
			Itinerary shortestItinerary) {
		this.originLocationId = originLocationId;
		this.destinationLocationId = destinationLocationId;
		this.cheapestItinerary = cheapestItinerary;
		this.shortestItinerary = shortestItinerary;
	}

	public int getOriginLocationId() {
		return originLocationId;
	}

	public int getDestinationLocationId() {
		return destinationLocationId;
	}

	public Itinerary getCheapestItinerary() {
		return cheapestItinerary;
	}

	public Itinerary getShortestItinerary() {
		return shortestItinerary;
	}
	
}

class Itinerary {
	
	private int totalDistance;
	private double totalPrice;
	private AirlineRoute[] routes;
	
	public Itinerary(int totalDistance, double totalPrice, AirlineRoute[] routes) {
		this.totalDistance = totalDistance;
		this.totalPrice = totalPrice;
		this.routes = routes;
	}

	public int getTotalDistance() {
		return totalDistance;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public AirlineRoute[] getRoutes() {
		return routes;
	}
	
}

