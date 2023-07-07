package solvd.airline.dataaccess.model.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "locations")
@XmlAccessorType(XmlAccessType.FIELD)
public class Locations {
    @XmlElement(name = "location")
    @JsonProperty("locations")
    private List<Location> locations;

    public Locations() {
        locations = new ArrayList<>();
    }

    public List<Location> getAllLocations() {
        return locations;
    }


    /*public void addLocation(Location location) {
        locations.add(location);
    }

    public void removeLocation(Location location) {
        locations.remove(location);
    }


    public Location getLocationById(int locationId) {
        for (Location location : locations) {
            if (location.getLocationId() == locationId) {
                return location;
            }
        }
        return null;
    }*/
}