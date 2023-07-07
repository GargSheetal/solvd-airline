package solvd.airline.menu;
import solvd.airline.dataaccess.model.Location.Location;
import solvd.airline.dataaccess.service.LocationMyBatisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LocationManagementMenu {
    public static final Logger logger = LogManager.getLogger(LocationManagementMenu.class);
    private final LocationMyBatisService locationMyBatisService;
    private final Scanner scanner;

    public LocationManagementMenu(LocationMyBatisService locationMyBatisService) {
        this.locationMyBatisService = locationMyBatisService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            displayLocationMenu();
            try {
                int choice = getUserChoice();
                switch (choice) {
                    case 1:
                        displayLocations();
                        break;
                    case 2:
                        enterSourceAndDestination();
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        logger.info("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                logger.info("Invalid input. Please enter a valid choice.");
                scanner.nextLine(); // Consume the invalid input
            } catch (SQLException e) {
                logger.error("An error occurred while accessing the database: " + e.getMessage());
                exit = true; // Exit the menu loop to prevent further errors
            }
        }
        closeScanner();
    }

    private int getUserChoice() {
        logger.info("Enter your choice: ");
        return scanner.nextInt();
    }

    private void displayLocationMenu() {
        logger.info("----------- Location Menu -----------");
        logger.info("1. Display Locations");
        logger.info("2. Enter Source and Destination");
        logger.info("3. Exit and return to the main menu.");
    }

    private void displayLocations() throws SQLException {
        logger.info("=== All Locations ===");
        for (Location location : locationMyBatisService.getAllLocations()) {
            logger.info(location);
        }
    }

    private void enterSourceAndDestination() throws SQLException {
        logger.info("Enter the source location ID: ");
        int sourceId = scanner.nextInt();
        logger.info("Enter the destination location ID: ");
        int destinationId = scanner.nextInt();

        // Retrieve the source and destination locations based on the IDs
        Location sourceLocation = locationMyBatisService.getLocationById(sourceId);
        Location destinationLocation = locationMyBatisService.getLocationById(destinationId);

        if (sourceLocation != null && destinationLocation != null) {
            logger.info("Source: " + sourceLocation.getLocationName());
            logger.info("Destination: " + destinationLocation.getLocationName());
            // Perform desired operations with the source and destination locations
        } else {
            logger.info("Invalid source or destination location ID.");
        }
    }

    private void closeScanner() {
        scanner.close();
    }
}