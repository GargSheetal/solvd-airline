package solvd.airline.dataaccess.model.location;
import java.util.List;

public interface LocationMapper {
    Location getLocationById(int id);

    List<Location> getAllLocations();

    void addLocation(Location location);

    void updateLocation(Location location);

    void deleteLocation(int id);
}