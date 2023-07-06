package solvd.airline.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import solvd.airline.dataaccess.strategy.RouteSelectionSystem;
import solvd.airline.dataaccess.strategy.CheapestRouteSelectionStrategy;
import solvd.airline.dataaccess.strategy.ShortestRouteSelectionStrategy;

import solvd.airline.dataaccess.model.AirlineRoute.AirLineRoute;
import solvd.airline.dataaccess.model.Location.Location;

public class RouteSelectionMenuHelper {
	
	private final int INF = Integer.MAX_VALUE;	// Infinity value for unreachable routes
	
	private List<Location> locationList = new ArrayList<>();
	private List<AirLineRoute> airlineRouteList = new ArrayList<>();
	private int numLocations;
	private Location[] locations;
	private String[] locationNames;
	private int[][] distance;
	private double[][] price;
	private int[][] nextLocationIdx;
	
	private RouteSelectionSystem cheapestRoute = new RouteSelectionSystem();
	private RouteSelectionSystem shortestRoute = new RouteSelectionSystem();
	
	public RouteSelectionMenuHelper(List<Location> locationList, List<AirLineRoute> airlineRouteList) {
		this.locationList = locationList;
		this.airlineRouteList = airlineRouteList;
		this.numLocations = locationList.size();
		this.locations = locationList.toArray(new Location[numLocations]);
		this.locationNames = getLocationNames(locations);
		this.distance = new int[numLocations][numLocations];
		this.price = new double[numLocations][numLocations];
		this.nextLocationIdx = new int[numLocations][numLocations];
		
		setRouteMatrix();
		printIntMatrix("Distance (from DB)", locationNames, distance);
		printDoubleMatrix("Price (from DB)", locationNames, price);
		
		cheapestRoute.setRouteSelectionStrategy(new CheapestRouteSelectionStrategy(numLocations, distance, price, nextLocationIdx));
		printIntMatrix("Distance (cheapest)", locationNames, cheapestRoute.getDistanceMatrix());
		printDoubleMatrix("Price (cheapest)", locationNames, cheapestRoute.getPriceMatrix());

		shortestRoute.setRouteSelectionStrategy(new ShortestRouteSelectionStrategy(numLocations, distance, price, nextLocationIdx));
		printIntMatrix("Distance (shortest)", locationNames, shortestRoute.getDistanceMatrix());
		printDoubleMatrix("Price (shortest)", locationNames, shortestRoute.getPriceMatrix());
	}
	
	private void printIntMatrix(String label, String[] header, int[][] matrix) {
		String[][] display = new String[matrix.length + 1][matrix[0].length + 1];
		display[0][0] = String.format("%-15s", "");

		for (int s = 0; s < header.length; s++) {
			display[0][s+1] = String.format("%-15s", header[s]);
			display[s+1][0] = String.format("%-15s", header[s]);
		}

		for (int mrow = 0; mrow < matrix.length; mrow++) {
			for (int mcol = 0; mcol < matrix[0].length; mcol++) {
				if (matrix[mrow][mcol] == INF) {
					display[mrow + 1][mcol + 1] = String.format("%-15s", "Not Available");
				} else {
					display[mrow + 1][mcol + 1] = String.format("%-15s", matrix[mrow][mcol]);
				}
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
	
	private void printDoubleMatrix(String label, String[] header, double[][] matrix) {
		String[][] display = new String[matrix.length + 1][matrix[0].length + 1];
		display[0][0] = String.format("%-15s", "");

		for (int s = 0; s < header.length; s++) {
			display[0][s+1] = String.format("%-15s", header[s]);
			display[s+1][0] = String.format("%-15s", header[s]);
		}

		for (int mrow = 0; mrow < matrix.length; mrow++) {
			for (int mcol = 0; mcol < matrix[0].length; mcol++) {
				if (matrix[mrow][mcol] == INF) {
					display[mrow + 1][mcol + 1] = String.format("%-15s", "Not Available");
				} else {
					display[mrow + 1][mcol + 1] = String.format("%-15s", matrix[mrow][mcol]);
				}
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

	private void setRouteMatrix() {
		// Initialize distance, price, and nextLocationIdx matrices
		for(int i=0; i<numLocations; i++) {
			Arrays.fill(distance[i], INF);
			Arrays.fill(price[i], INF);
			Arrays.fill(nextLocationIdx[i], -1);
		}

		// Populate distance, price, and nextLocationIdx matrices with input data
		for(AirLineRoute route: airlineRouteList) {
			int airlineLocationId = route.getRouteId();
			int originLocationId = route.getOriginLocation().getLocationId();
			int destinationLocationId = route.getDestinationLocation().getLocationId();
			int distanceMiles = route.getDistanceMiles();
			double priceDollars = route.getPriceDollars();

			int originLocationIdx = getLocationIndex(originLocationId);
			int destinationLocationIdx = getLocationIndex(destinationLocationId);

			distance[originLocationIdx][destinationLocationIdx] = distanceMiles;
			distance[destinationLocationIdx][originLocationIdx] = distanceMiles;

			price[originLocationIdx][destinationLocationIdx] = priceDollars;
			price[destinationLocationIdx][originLocationIdx] = priceDollars;

			nextLocationIdx[destinationLocationIdx][originLocationIdx] = originLocationIdx;
			nextLocationIdx[originLocationIdx][destinationLocationIdx] = destinationLocationIdx;
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
	
	private String[] getLocationNames(Location[] locations) {
		String[] locationNames = new String[locations.length];
		for(int i=0; i<locations.length; i++) {
			locationNames[i] = locations[i].getLocationName();
		}
		return locationNames;
	}
	
    private Itinerary computeItinerary(int originLocationIdx, int destinationLocationIdx, RouteSelectionSystem routeSelectionSystem) {
    	int[][] nextLocationIdxMatrix = routeSelectionSystem.getNextLocationIdxMatrix();
        if (nextLocationIdxMatrix[originLocationIdx][destinationLocationIdx] == -1) {
            return null; // No route exists
        }
        

        List<Location> itineraryLocationList = new ArrayList<>();
        int currentLocationIdx = originLocationIdx;
   
        itineraryLocationList.add(locations[currentLocationIdx]);
        while (currentLocationIdx != destinationLocationIdx) {
        	currentLocationIdx = nextLocationIdxMatrix[currentLocationIdx][destinationLocationIdx];
        	itineraryLocationList.add(locations[currentLocationIdx]);
        }
        
        Itinerary itinerary = new Itinerary(
    		itineraryLocationList,
    		routeSelectionSystem.getDistanceMatrix()[originLocationIdx][destinationLocationIdx],
    		routeSelectionSystem.getPriceMatrix()[originLocationIdx][destinationLocationIdx]
		);
        return itinerary;
    }
    
	public void getItineraryQueryResult(int inputOriginLocationId, int inputDestinationLocationId) {
		int inputOriginLocationIdx = getLocationIndex(inputOriginLocationId);
		int inputDestinationLocationIdx = getLocationIndex(inputDestinationLocationId);
		Itinerary cheapestItinerary = computeItinerary(inputOriginLocationIdx, inputDestinationLocationIdx, cheapestRoute);
		Itinerary shortestItinerary = computeItinerary(inputOriginLocationIdx, inputDestinationLocationIdx, shortestRoute);
		ItineraryQueryResult itineraryQueryResult = new ItineraryQueryResult(
			locations[inputOriginLocationIdx],
			locations[inputDestinationLocationIdx],
			cheapestItinerary,
			shortestItinerary
		);
		System.out.println("\n\nItinerary Query Result : -->\n" + itineraryQueryResult.toString());
	}
}


class ItineraryQueryResult {
	
	private Location originLocation;
	private Location destinationLocation;
	private Itinerary cheapestItinerary;
	private Itinerary shortestItinerary;
	
	public ItineraryQueryResult(Location originLocation, Location destinationLocation, Itinerary cheapestItinerary,
			Itinerary shortestItinerary) {
		this.originLocation = originLocation;
		this.destinationLocation = destinationLocation;
		this.cheapestItinerary = cheapestItinerary;
		this.shortestItinerary = shortestItinerary;
	}

	public Location getOriginLocation() {
		return originLocation;
	}

	public Location getDestinationLocation() {
		return destinationLocation;
	}

	public Itinerary getCheapestItinerary() {
		return cheapestItinerary;
	}

	public Itinerary getShortestItinerary() {
		return shortestItinerary;
	}

	@Override
	public String toString() {
		return "Origin = " + originLocation.getLocationName()
				+ " | Destination = " + destinationLocation.getLocationName()
				+ "\n[Cheapest Itinerary] " + (cheapestItinerary == null ? "No route found" : cheapestItinerary.toString())
				+ "\n[Shortest Itinerary] " + (shortestItinerary == null ? "No route found" : shortestItinerary.toString());
	}
}

class Itinerary {
	
	private int totalDistance;
	private double totalPrice;
	private List<Location> locations;
	
	public Itinerary(List<Location> locations, int totalDistance, double totalPrice) {
		this.locations = locations;
		this.totalDistance = totalDistance;
		this.totalPrice = totalPrice;
	}

	public int getTotalDistance() {
		return totalDistance;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
	public List<Location> getLocations() {
		return locations;
	}

	@Override
	public String toString() {
		String locationString = locations.get(0).getLocationName();
		for (int i = 1; i < locations.size(); i++) {
			locationString = locationString + String.format(" -> %s", locations.get(i).getLocationName());
		}
		return "Locations = " + locationString + " | Distance = " + totalDistance + " miles | Price = $" + totalPrice;
	}
}

