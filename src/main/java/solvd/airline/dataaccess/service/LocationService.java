package solvd.airline.dataaccess.service;
import solvd.airline.dataaccess.model.Location.Location;
import solvd.airline.dataaccess.model.Location.LocationMapper;
import java.util.List;

public class LocationService {
    private LocationMapper locationMapper;

    public LocationService(LocationMapper locationMapper) {
        this.locationMapper = locationMapper;
    }

    public Location getLocationById(int id) {
        return locationMapper.getLocationById(id);
    }
    public List<Location> getAllLocations() {
        return locationMapper.getAllLocations();
    }
    public void addLocation(Location location) {
        locationMapper.addLocation(location);
    }

    public void updateLocation(Location location) {
        locationMapper.updateLocation(location);
    }

    public void deleteLocation(int id) {
        locationMapper.deleteLocation(id);
    }
}