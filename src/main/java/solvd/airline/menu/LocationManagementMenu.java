package solvd.airline.menu;

import solvd.airline.dataaccess.model.Location.Location;
import solvd.airline.dataaccess.service.LocationMyBatisService;
import java.util.Scanner;


public class LocationManagementMenu {
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
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    private int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }
    private void displayLocationMenu() {
        System.out.println("----------- Location Menu -----------");
        System.out.println("1. Display Locations");
        System.out.println("2. Enter Source and Destination");
        System.out.println("3. Exit and return to the main menu.");
    }

    private void displayLocations() {
        System.out.println("=== All Locations ===");
        for (Location location : locationMyBatisService.getAllLocations()) {
            System.out.println(location);
        }
        System.out.println();
    }

    private void enterSourceAndDestination() {
        System.out.print("Enter the source location ID: ");
        int sourceId = scanner.nextInt();
        System.out.print("Enter the destination location ID: ");
        int destinationId = scanner.nextInt();


        // Retrieve the source and destination locations based on the IDs
        Location sourceLocation = locationMyBatisService.getLocationById(sourceId);
        Location destinationLocation = locationMyBatisService.getLocationById(destinationId);

        if (sourceLocation != null && destinationLocation != null) {
            System.out.println("Source: " + sourceLocation.getLocationName());
            System.out.println("Destination: " + destinationLocation.getLocationName());
            // Perform desired operations with the source and destination locations
        } else {
            System.out.println("Invalid source or destination location ID.");
        }
    }
}