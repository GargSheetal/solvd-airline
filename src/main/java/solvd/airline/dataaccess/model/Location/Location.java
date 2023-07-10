package solvd.airline.dataaccess.model.Location;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "location")
@XmlAccessorType(XmlAccessType.FIELD)

public class Location {
	
    @XmlElement(name = "location_id")
    @JsonProperty("location_id")
    private int locationId;

    @XmlElement(name = "location_name")
    @JsonProperty("location_name")
    private String locationName;
    
    public Location() {
    }

    public Location(int locationId) {
        this.locationId = locationId;
    }
    
    public Location(int locationId, String locationName) {
        this.locationId = locationId;
        this.locationName = locationName;
    }

    public Location( String locationName) {
        this.locationName = locationName;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return String.format("%-3s", locationId) + " | " + locationName;
    }

	@Override
	public int hashCode() {
		return Objects.hash(locationId);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == this)
		{
			return true;
		}
		if(!(obj instanceof Location))
		{
			return false;
		}
		Location other = (Location)obj;
		return locationId == other.locationId;	
	}
}