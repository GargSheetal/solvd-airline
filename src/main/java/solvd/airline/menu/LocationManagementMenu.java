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

}