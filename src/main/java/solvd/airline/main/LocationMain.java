package solvd.airline.main;
import solvd.airline.dataaccess.model.location.Location;
import solvd.airline.dataaccess.service.LocationMyBatisService;
import solvd.airline.menu.LocationManagementMenu;
import solvd.airline.output.JsonParser;
import java.util.List;

public class LocationMain {
    private static final String JSON_FILENAME = "sampleOutput.json";

    public static void main(String[] args) {
        LocationMyBatisService locationService = new LocationMyBatisService();

        // Create the menu and pass the LocationMyBatisService instance
        LocationManagementMenu menu = new LocationManagementMenu(locationService);
        menu.start();

        // Retrieve all locations after menu operations
        List<Location> allLocations = locationService.getAllLocations();
        System.out.println("All Locations:");
        for (Location loc : allLocations) {
            System.out.println(loc);
        }

        // Save data to JSON file
        JsonParser.saveDataToJson(allLocations, JSON_FILENAME);
        System.out.println("Data saved to JSON file: " + JSON_FILENAME);

        // Load data from JSON file
        List<Location> loadedLocations = JsonParser.loadDataFromJson(JSON_FILENAME, Location.class);
        System.out.println("Loaded Locations from JSON file:");
        if (loadedLocations != null) {
            for (Location loc : loadedLocations) {
                System.out.println(loc);
            }
        }

        // Close the session
        locationService.closeSession();
    }
}