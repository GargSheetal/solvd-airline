package solvd.airline.menu;
import solvd.airline.dataaccess.model.location.Location;
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
            System.out.println("=== Location Management Menu ===");
            System.out.println("1. Add Location");
            System.out.println("2. View All Locations");
            System.out.println("3. Find Location by ID");
            System.out.println("4. Update Location");
            System.out.println("5. Delete Location");
            System.out.println("0. Exit");

            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    addLocation();
                    break;
                case 2:
                    viewAllLocations();
                    break;
                case 3:
                    findLocationById();
                    break;
                case 4:
                    updateLocation();
                    break;
                case 5:
                    deleteLocation();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private void addLocation() {
        scanner.nextLine(); // Consume the newline character
        System.out.println("Enter location name:");
        String locationName = scanner.nextLine();
        Location location = new Location(locationName);
        locationMyBatisService.addLocation(location);
        System.out.println("Location added successfully!");
    }

    private void viewAllLocations() {
        System.out.println("=== All Locations ===");
        for (Location location : locationMyBatisService.getAllLocations()) {
            System.out.println(location);
        }
        System.out.println();
    }

    private void findLocationById() {
        System.out.print("Enter location ID: ");
        int locationId = scanner.nextInt();
        Location location = locationMyBatisService.getLocationById(locationId);
        if (location != null) {
            System.out.println("Location found:");
            System.out.println(location);
        } else {
            System.out.println("Location not found.");
        }
    }

    private void updateLocation() {
        System.out.print("Enter location ID: ");
        int locationId = scanner.nextInt();
        Location location = locationMyBatisService.getLocationById(locationId);
        if (location != null) {
            scanner.nextLine(); // Consume the newline character
            System.out.println("Enter new location name:");
            String locationName = scanner.nextLine();
            location.setLocationName(locationName);
            locationMyBatisService.updateLocation(location);
            System.out.println("Location updated successfully!");
        } else {
            System.out.println("Location not found.");
        }
    }

    private void deleteLocation() {
        System.out.print("Enter location ID: ");
        int locationId = scanner.nextInt();
        Location location = locationMyBatisService.getLocationById(locationId);
        if (location != null) {
            locationMyBatisService.deleteLocation(locationId);
            System.out.println("Location deleted successfully!");
        } else {
            System.out.println("Location not found.");
        }
    }
}