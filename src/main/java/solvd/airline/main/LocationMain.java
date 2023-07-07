package solvd.airline.main;
import solvd.airline.dataaccess.model.Location.Location;
import solvd.airline.dataaccess.service.LocationMyBatisService;
import solvd.airline.menu.LocationManagementMenu;
import solvd.airline.output.JsonParser;
import java.util.List;
import static solvd.airline.menu.LocationManagementMenu.logger;

public class LocationMain {
    private static final String JSON_FILENAME = "sampleOutput.json";

    public static void main(String[] args) {
        LocationMyBatisService locationService = new LocationMyBatisService();

        // Create the menu and pass the LocationMyBatisService instance
        LocationManagementMenu menu = new LocationManagementMenu(locationService);
        menu.start();

        // Retrieve all locations after menu operations
        List<Location> allLocations = locationService.getAllLocations();
        logger.info("All Locations:");
        for (Location loc : allLocations) {
            logger.info(loc);
        }

        // Save data to JSON file
        JsonParser.saveDataToJson(allLocations, JSON_FILENAME);
        logger.info("Data saved to JSON file: " + JSON_FILENAME);

        // Load data from JSON file
        List<Location> loadedLocations = JsonParser.loadDataFromJson(JSON_FILENAME, Location.class);
        logger.info("Loaded Locations from JSON file:");
        if (loadedLocations != null) {
            for (Location loc : loadedLocations) {
                logger.info(loc);
            }
        }

        // Close the session
        locationService.closeSession();
    }
}