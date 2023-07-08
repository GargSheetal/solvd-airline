package solvd.airline.itinerary;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import solvd.airline.dataaccess.model.Location.Location;

@XmlRootElement(name = "itinerary")
@XmlAccessorType(XmlAccessType.FIELD)
public class Itinerary {

	@XmlElement(name = "total_distance")
    @JsonProperty("total_distance")
	private int totalDistance;
	
	@XmlElement(name = "total_price")
    @JsonProperty("total_price")
	private double totalPrice;
	
	@XmlElement(name = "location")
    @JsonProperty("locations")
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
