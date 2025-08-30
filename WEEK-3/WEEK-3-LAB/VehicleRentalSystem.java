public class VehicleRentalSystem {
    public static void main(String[] args) {
        // Set company name using static method
        Vehicle.setCompanyName("SuperDrive Rentals");

        // Create vehicles (instance members unique for each object)
        Vehicle v1 = new Vehicle("V001", "Toyota", "Corolla", 1200);
        Vehicle v2 = new Vehicle("V002", "Honda", "Civic", 1500);
        Vehicle v3 = new Vehicle("V003", "Ford", "Mustang", 2500);

        // Display all vehicles initially
        System.out.println("\n--- Initial Vehicle Info ---");
        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();

        // Renting vehicles
        System.out.println("\n--- Renting Vehicles ---");
        v1.rentVehicle(3); // 3 days
        v2.rentVehicle(5); // 5 days

        // Trying to rent an unavailable vehicle
        v1.rentVehicle(2); 

        // Return vehicle
        System.out.println("\n--- Returning Vehicles ---");
        v1.returnVehicle();
        v2.returnVehicle();

        // Rent again after return
        v1.rentVehicle(2);

        // Final info
        System.out.println("\n--- Final Vehicle Info ---");
        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();

        // Company-wide stats (static members shared by all objects)
        System.out.println("\n--- Company Stats ---");
        Vehicle.displayCompanyStats();
    }
}

// ================== Vehicle Class ==================
class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean isAvailable;
    private int timesRented;
    private int totalRentalDays;

    // Static variables (shared by all vehicles)
    private static int totalVehicles = 0;
    private static double totalRevenue = 0;
    private static String companyName;
    private static int rentalDays = 0;

    // Constructor
    public Vehicle(String vehicleId, String brand, String model, double rentPerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
        this.timesRented = 0;
        this.totalRentalDays = 0;
        totalVehicles++;
    }

    // Instance Method: Rent vehicle
    public void rentVehicle(int days) {
        if (isAvailable) {
            double rent = calculateRent(days);
            System.out.println("Vehicle " + vehicleId + " rented for " + days + " days. Total Rent: " + rent);
            isAvailable = false;
            timesRented++;
            totalRentalDays += days;
        } else {
            System.out.println("Vehicle " + vehicleId + " is not available right now.");
        }
    }

    // Instance Method: Return vehicle
    public void returnVehicle() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("Vehicle " + vehicleId + " has been returned and is now available.");
        } else {
            System.out.println("Vehicle " + vehicleId + " was not rented out.");
        }
    }

    // Instance Method: Calculate rent and update revenue
    public double calculateRent(int days) {
        double rent = rentPerDay * days;
        totalRevenue += rent;   // update static revenue
        rentalDays += days;     // update total rental days
        return rent;
    }

    // Instance Method: Display Vehicle info
    public void displayVehicleInfo() {
        System.out.println("ID: " + vehicleId + ", Brand: " + brand + ", Model: " + model +
                ", Rent/Day: " + rentPerDay + ", Available: " + isAvailable +
                ", Times Rented: " + timesRented + ", Total Rental Days: " + totalRentalDays);
    }

    // Static Methods
    public static void setCompanyName(String name) {
        companyName = name;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public static double getAverageRentPerDay() {
        if (rentalDays == 0) return 0;
        return totalRevenue / rentalDays;
    }

    public static void displayCompanyStats() {
        System.out.println("Company: " + companyName);
        System.out.println("Total Vehicles: " + totalVehicles);
        System.out.println("Total Revenue: " + totalRevenue);
        System.out.println("Total Rental Days: " + rentalDays);
        System.out.println("Average Rent Per Day: " + getAverageRentPerDay());
    }
}
