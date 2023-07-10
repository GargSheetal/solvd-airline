package solvd.airline.dataaccess.model.AirlineRoute;

import com.fasterxml.jackson.annotation.JsonProperty;
import solvd.airline.dataaccess.model.Location.Location;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "airline_route")
@XmlAccessorType(XmlAccessType.FIELD)
public class AirLineRoute {
    @XmlElement(name = "route_id")
    @JsonProperty("route_id")
    private int routeId;
    @XmlElement(name = "origin_location")
    @JsonProperty("origin_location")
    private Location originLocation;
    @XmlElement(name = "destination_location")
    @JsonProperty("destination_location")
    private Location destinationLocation;
    @XmlElement(name = "distance_miles")
    @JsonProperty("distance_miles")
    private int distanceMiles;
    @XmlElement(name = "price_dollars")
    @JsonProperty("price_dollars")
    private double priceDollars;

    public AirLineRoute() {

    }

    public AirLineRoute(Location originLocation, Location destinationLocation, int distanceMiles, double priceDollars) {
        this.originLocation = originLocation;
        this.destinationLocation = destinationLocation;
        this.distanceMiles = distanceMiles;
        this.priceDollars = priceDollars;
    }

    public AirLineRoute(int routeId,Location originLocation, Location destinationLocation, int distanceMiles, double priceDollars) {
        this.routeId = routeId;
        this.originLocation = originLocation;
        this.destinationLocation = destinationLocation;
        this.distanceMiles = distanceMiles;
        this.priceDollars = priceDollars;
    }

    // Getters
    public int getRouteId() {
        return routeId;
    }

    public Location getOriginLocation() {
        return originLocation;
    }

    public Location getDestinationLocation() {
        return destinationLocation;
    }

    public int getDistanceMiles() {
        return distanceMiles;
    }

    public double getPriceDollars() {
        return priceDollars;
    }

    // Setters
    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public void setOriginLocation(Location originLocation) {
        this.originLocation = originLocation;
    }

    public void setDestinationLocation(Location destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public void setDistanceMiles(int distanceMiles) {
        this.distanceMiles = distanceMiles;
    }

    public void setPriceDollars(double priceDollars) {
        this.priceDollars = priceDollars;
    }

    @Override
    public String toString() {
        return String.format("%-3s", routeId) +
                " | Origin = " + String.format("%-15s", originLocation.getLocationName()) +
                " | Destination = " + String.format("%-15s", destinationLocation.getLocationName()) +
                " | Distance = " + String.format("%-4s", distanceMiles) +
                " miles | Price = $" + String.format("%-6s", priceDollars);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirLineRoute that = (AirLineRoute) o;
        return routeId == that.routeId &&
                distanceMiles == that.distanceMiles &&
                Double.compare(that.priceDollars, priceDollars) == 0 &&
                Objects.equals(originLocation, that.originLocation) &&
                Objects.equals(destinationLocation, that.destinationLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId, originLocation, destinationLocation, distanceMiles, priceDollars);
    }
}

