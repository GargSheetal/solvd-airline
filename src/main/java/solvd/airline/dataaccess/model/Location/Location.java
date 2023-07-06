package solvd.airline.dataaccess.model.Location;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class Location {

    private int locationId;

    private String locationName;

    public Location() {
    }
    
    public Location(String locationName) {
        this.locationName = locationName;
    }
    
    public Location(int locationId, String locationName) {
        this.locationId = locationId;
        this.locationName = locationName;
    }
    
    @XmlElement(name = "location_id")
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    @XmlElement(name = "location_name")
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", locationName='" + locationName + '\'' +
                '}';
    }
}