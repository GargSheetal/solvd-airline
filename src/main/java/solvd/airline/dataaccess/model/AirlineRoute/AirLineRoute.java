package solvd.airline.dataaccess.model.AirlineRoute;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlRootElement(name = "airline_route")
@XmlAccessorType(XmlAccessType.FIELD)
public class AirLineRoute {
    @XmlElement(name = "route_id")
    private int routeId;
    @XmlElement(name = "origin_location_id")
    private int originLocationId;
    @XmlElement(name = "destination_location_id")
    private int destinationLocationId;
    @XmlElement(name = "distance_miles")
    private int distanceMiles;
    @XmlElement(name = "price_dollars")
    private double priceDollars;

    // Getters
    public int getRouteId() {
        return routeId;
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

    public double getPriceDollars() {
        return priceDollars;
    }

    // Setters
    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public void setOriginLocationId(int originLocationId) {
        this.originLocationId = originLocationId;
    }

    public void setDestinationLocationId(int destinationLocationId) {
        this.destinationLocationId = destinationLocationId;
    }

    public void setDistanceMiles(int distanceMiles) {
        this.distanceMiles = distanceMiles;
    }

    public void setPriceDollars(double priceDollars) {
        this.priceDollars = priceDollars;
    }

    @Override
    public String toString() {
        return "\nAirLineRoute{" +
                "routeId=" + routeId +
                ", originLocationId=" + originLocationId +
                ", destinationLocationId=" + destinationLocationId +
                ", distanceMiles=" + distanceMiles +
                ", priceDollars=" + priceDollars +
                "}";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirLineRoute that = (AirLineRoute) o;
        return routeId == that.routeId &&
                originLocationId == that.originLocationId &&
                destinationLocationId == that.destinationLocationId &&
                distanceMiles == that.distanceMiles &&
                Double.compare(that.priceDollars, priceDollars) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId, originLocationId, destinationLocationId, distanceMiles, priceDollars);
    }
}
