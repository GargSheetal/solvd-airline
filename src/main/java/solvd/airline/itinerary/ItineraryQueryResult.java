package solvd.airline.itinerary;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import solvd.airline.dataaccess.model.Location.Location;

import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "itinerary_query_result")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItineraryQueryResult {

	@XmlElement(name = "origin_location")
	@JsonIgnore
	private Location originLocation;
	
	@XmlElement(name = "destination_location")
	@JsonIgnore
	private Location destinationLocation;
	
	@XmlElement(name = "cheapest_itinerary")
    @JsonProperty("cheapest_itinerary")
	private Itinerary cheapestItinerary;
	
	@XmlElement(name = "shortest_itinerary")
    @JsonProperty("shortest_itinerary")
	private Itinerary shortestItinerary;

	public ItineraryQueryResult(){

	}
	
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

	public List<ItineraryQueryResult> getDataList() {
		return Collections.emptyList();
	}

	public void setDataList(List<ItineraryQueryResult> dataList) {
		if (!dataList.isEmpty()) {
			ItineraryQueryResult other = dataList.get(0);
			this.originLocation = other.originLocation;
			this.destinationLocation = other.destinationLocation;
			this.cheapestItinerary = other.cheapestItinerary;
			this.shortestItinerary = other.shortestItinerary;
		}
	}


	@Override
	public String toString() {
		return "Origin = " + originLocation.getLocationName()
				+ " | Destination = " + destinationLocation.getLocationName()
				+ "\n[Cheapest Itinerary] " + (cheapestItinerary == null ? "No route found" : cheapestItinerary.toString())
				+ "\n[Shortest Itinerary] " + (shortestItinerary == null ? "No route found" : shortestItinerary.toString());
	}
	
	public String toStringCheapest() {
		return "Origin = " + originLocation.getLocationName() 
			+ " | Destination = " + destinationLocation.getLocationName()
			+ "\n[Cheapest Itinerary] " + cheapestItinerary.toString();
	}
	
	public String toStringShortest() {
		return "Origin = " + originLocation.getLocationName() 
			+ " | Destination = " + destinationLocation.getLocationName()
			+ "\n[Shortest Itinerary] " + shortestItinerary.toString();
	}
}
