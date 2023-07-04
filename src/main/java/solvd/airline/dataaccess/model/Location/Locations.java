package solvd.airline.dataaccess.model.Location;
import java.util.ArrayList;
import java.util.List;

public class Locations {
    private List<Location> locations;

    public Locations() {
        locations = new ArrayList<>();
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public void removeLocation(Location location) {
        locations.remove(location);
    }

    public List<Location> getAllLocations() {
        return locations;
    }

    public Location getLocationById(int locationId) {
        for (Location location : locations) {
            if (location.getLocationId() == locationId) {
                return location;
            }
        }
        return null;
    }
}