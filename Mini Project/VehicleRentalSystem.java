// Main application class to run the system
import java.util.ArrayList; // For dynamic lists of objects
import java.util.InputMismatchException; // For handling incorrect input types
import java.util.Scanner; // For user input

// --- Custom Exceptions (Chapter 9: Exception Handling) ---
// Custom exception for when a vehicle is unavailable for an operation (e.g., removal if rented)
class VehicleInUseException extends Exception {
    public VehicleInUseException(String message) {
        super(message);
    }
}

// Custom exception for when a vehicle is not found
class VehicleNotFoundException extends Exception {
    public VehicleNotFoundException(String message) {
        super(message);
    }
}

// --- Chapter 7: Inheritance ---
// Superclass: Represents a generic vehicle in the rental system.
class Vehicle {
    // Common attributes for all vehicles
    protected String make;
    protected String model;
    protected int year;
    protected double rentalRatePerDay;
    protected boolean isAvailable; // Tracks availability for rental
    protected String registrationNumber; // Unique identifier for each vehicle

    // Constructor
    public Vehicle(String make, String model, int year, double rentalRatePerDay, String registrationNumber) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.rentalRatePerDay = rentalRatePerDay;
        this.isAvailable = true; // By default, a new vehicle is available
        this.registrationNumber = registrationNumber;
    }

    // Getters for common attributes
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getRentalRatePerDay() {
        return rentalRatePerDay;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    // Setters for availability (important for rental logic)
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // --- Chapter 8: Polymorphism ---
    // Method to calculate rental cost, can be overridden by subclasses
    public double calculateRentalCost(int days) throws IllegalArgumentException {
        // --- Chapter 9: Exception Handling ---
        // Basic validation for rental days
        if (days <= 0) {
            throw new IllegalArgumentException("Rental days must be positive.");
        }
        return rentalRatePerDay * days;
    }

    // Method to display vehicle details (can be overridden)
    @Override
    public String toString() {
        return "Registration: " + registrationNumber + ", Make: " + make + ", Model: " + model + ", Year: " + year +
               ", Rate: $" + String.format("%.2f", rentalRatePerDay) + "/day, Available: " + (isAvailable ? "Yes" : "No");
    }
}

// Subclass Car: Inherits from Vehicle
class Car extends Vehicle {
    private int numberOfDoors;
    private String fuelType; // e.g., "Petrol", "Diesel", "Electric"

    // Constructor
    public Car(String make, String model, int year, double rentalRatePerDay, String registrationNumber,
               int numberOfDoors, String fuelType) {
        // Call superclass constructor
        super(make, model, year, rentalRatePerDay, registrationNumber);
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
    }

    // Specific getters for Car attributes
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public String getFuelType() {
        return fuelType;
    }

    // --- Chapter 8: Polymorphism ---
    // Override calculateRentalCost to potentially add car-specific charges
    @Override
    public double calculateRentalCost(int days) throws IllegalArgumentException {
        // Call superclass method for base calculation
        double baseCost = super.calculateRentalCost(days);
        // Add a small premium for cars (example of polymorphism)
        return baseCost + (days * 5.0); // $5 extra per day for cars
    }

    @Override
    public String toString() {
        return "Car - " + super.toString() + ", Doors: " + numberOfDoors + ", Fuel: " + fuelType;
    }
}

// Subclass Motorcycle: Inherits from Vehicle
class Motorcycle extends Vehicle {
    private int engineDisplacementCC; // Cubic centimeters
    private boolean helmetProvided;

    // Constructor
    public Motorcycle(String make, String model, int year, double rentalRatePerDay, String registrationNumber,
                      int engineDisplacementCC, boolean helmetProvided) {
        // Call superclass constructor
        super(make, model, year, rentalRatePerDay, registrationNumber); // Corrected typo here (rentalRateRatePerDay -> rentalRatePerDay)
        this.engineDisplacementCC = engineDisplacementCC;
        this.helmetProvided = helmetProvided;
    }

    // Specific getters for Motorcycle attributes
    public int getEngineDisplacementCC() {
        return engineDisplacementCC;
    }

    public boolean hasHelmet() {
        return helmetProvided;
    }

    // --- Chapter 8: Polymorphism ---
    // Override calculateRentalCost to potentially add motorcycle-specific charges
    @Override
    public double calculateRentalCost(int days) throws IllegalArgumentException {
        double baseCost = super.calculateRentalCost(days);
        // Add a different premium or discount for motorcycles
        return baseCost + (days * 2.0); // $2 extra per day for motorcycles
    }

    @Override
    public String toString() {
        return "Motorcycle - " + super.toString() + ", Engine: " + engineDisplacementCC + "cc, Helmet Included: " + (helmetProvided ? "Yes" : "No");
    }
}

// --- Chapter 6: Class Relationships (Association) ---
// Customer class
class Customer {
    private String customerId; // Unique ID for customer
    private String name;
    private String contactNumber;

    // Constructor
    public Customer(String customerId, String name, String contactNumber) {
        this.customerId = customerId;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    // Getters
    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + name + ", Contact: " + contactNumber;
    }
}

// --- Chapter 6: Class Relationships (Association/Aggregation) ---
// Rental class: Represents a specific rental transaction
// It associates a Customer with a Vehicle.
class Rental {
    private String rentalId; // Unique ID for the rental
    private Customer customer; // Association: A Rental has a Customer
    private Vehicle vehicle;   // Association: A Rental has a Vehicle
    private String startDate; // For simplicity, using String for dates
    private String endDate;   // In a real system, use java.time.LocalDate
    private int rentalDays;
    private double totalCost;

    // Constructor
    public Rental(String rentalId, Customer customer, Vehicle vehicle, String startDate, String endDate, int rentalDays, double totalCost) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentalDays = rentalDays;
        this.totalCost = totalCost;
    }

    // Getters
    public String getRentalId() {
        return rentalId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "Rental ID: " + rentalId + "\n" +
               "  Customer: " + customer.getName() + " (" + customer.getCustomerId() + ")\n" +
               "  Vehicle: " + vehicle.getMake() + " " + vehicle.getModel() + " (" + vehicle.getRegistrationNumber() + ")\n" +
               "  Period: " + startDate + " to " + endDate + " (" + rentalDays + " days)\n" +
               "  Total Cost: $" + String.format("%.2f", totalCost);
    }
}


// Main system class that manages all operations
// --- Chapter 5: ArrayList / Vector ---
// --- Chapter 6: Class Relationships (Aggregation) ---
// Aggregation: The system 'has' vehicles, customers, and rentals.
// They can exist independently.
class VehicleRentalSystem {
    private ArrayList<Vehicle> vehicles; // Stores all vehicles
    private ArrayList<Customer> customers; // Stores all registered customers
    private ArrayList<Rental> rentals; // Stores all active/past rentals
    private int nextCustomerId = 1001; // Simple ID generator
    private int nextRentalId = 1;

    // Constructor
    public VehicleRentalSystem() {
        this.vehicles = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }

    // --- Vehicle Management Methods ---
    // Method to add a new vehicle to the system
    public void addVehicle(Vehicle vehicle) {
        // --- Chapter 9: Exception Handling ---
        // Check if a vehicle with the same registration number already exists
        if (findVehicleByRegistrationNumber(vehicle.getRegistrationNumber()) != null) {
            System.err.println("Error: Vehicle with registration number " + vehicle.getRegistrationNumber() + " already exists.");
            return;
        }
        vehicles.add(vehicle);
        System.out.println("Vehicle added: " + vehicle.getRegistrationNumber());
    }

    // Method to remove a vehicle from the system
    public void removeVehicle(String regNumber) {
        try {
            Vehicle vehicleToRemove = findVehicleByRegistrationNumber(regNumber);
            if (vehicleToRemove == null) {
                // Throw custom exception if vehicle not found
                throw new VehicleNotFoundException("Vehicle with registration " + regNumber + " not found.");
            }

            if (!vehicleToRemove.isAvailable()) {
                // Throw custom exception if vehicle is currently rented
                throw new VehicleInUseException("Vehicle " + regNumber + " is currently rented and cannot be removed.");
            }

            vehicles.remove(vehicleToRemove);
            System.out.println("Vehicle " + regNumber + " successfully removed.");
        } catch (VehicleNotFoundException | VehicleInUseException e) {
            System.err.println("Removal Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during vehicle removal: " + e.getMessage());
        }
    }

    public Vehicle findVehicleByRegistrationNumber(String regNumber) {
        for (Vehicle v : vehicles) {
            if (v.getRegistrationNumber().equalsIgnoreCase(regNumber)) {
                return v;
            }
        }
        return null; // Vehicle not found
    }

    public void displayAllVehicles() {
        int i = 1;
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }
        System.out.println("\n--- All Vehicles ---");
        for (Vehicle v : vehicles) {
            System.out.println(i + "." + v); // Demonstrates polymorphism via toString()
            System.out.println();
            i++;
        }
        System.out.println("--------------------");
    }

    public void displayAvailableVehicles() {
        System.out.println("\n--- Available Vehicles ---");
        boolean found = false;
        int i = 1;
        for (Vehicle v : vehicles) {
            if (v.isAvailable()) {
                System.out.println(i + ". " + v);
                i++;
                found = true;
            }
        }
        if (!found) {
            System.out.println("No vehicles currently available.");
        }
        System.out.println("------------------------");
    }

    // --- Customer Management Methods ---
    public void addCustomer(String name, String contactNumber) {
        String customerId = "CUST" + (nextCustomerId++);
        Customer newCustomer = new Customer(customerId, name, contactNumber);
        customers.add(newCustomer);
        System.out.println("New customer registered: ");
        System.out.println(newCustomer);
    }

    public Customer findCustomerById(String customerId) {
        for (Customer c : customers) {
            if (c.getCustomerId().equalsIgnoreCase(customerId)) {
                return c;
            }
        }
        return null; // Customer not found
    }

    public void displayAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
            return;
        }
        System.out.println("\n--- All Customers ---");
        for (Customer c : customers) {
            System.out.println();
            System.out.println(c);
            System.out.println();
        }
        System.out.println("---------------------");
    }

    // --- Rental Management Methods ---
    public void rentVehicle(String customerId, String registrationNumber, int days, String startDate, String endDate) {
        // --- Chapter 9: Exception Handling ---
        try {
            Customer customer = findCustomerById(customerId);
            if (customer == null) {
                // Use a more specific exception for clarity
                throw new IllegalArgumentException("Error: Customer with ID " + customerId + " not found.");
            }

            Vehicle vehicle = findVehicleByRegistrationNumber(registrationNumber);
            if (vehicle == null) {
                // Use a more specific exception for clarity
                throw new VehicleNotFoundException("Error: Vehicle with registration " + registrationNumber + " not found.");
            }

            if (!vehicle.isAvailable()) {
                // Use a more specific exception for clarity
                throw new VehicleInUseException("Error: Vehicle " + registrationNumber + " is currently not available for rent.");
            }

            // Calculate cost using polymorphic method
            double cost = vehicle.calculateRentalCost(days);

            // Mark vehicle as unavailable
            vehicle.setAvailable(false);

            String rentalId = "RENT" + (nextRentalId++);
            Rental newRental = new Rental(rentalId, customer, vehicle, startDate, endDate, days, cost);
            rentals.add(newRental);

            System.out.println("\n--- Rental Successful! ---");
            System.out.println(newRental);
            System.out.println("--------------------------");

        } catch (IllegalArgumentException | VehicleNotFoundException | VehicleInUseException e) {
            // Catch custom exceptions specifically
            System.err.println("Rental Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during rental: " + e.getMessage());
        }
    }

    public void returnVehicle(String rentalId) {
        // --- Chapter 9: Exception Handling ---
        try {
            Rental rentalToReturn = null;
            for (Rental r : rentals) {
                if (r.getRentalId().equalsIgnoreCase(rentalId)) {
                    rentalToReturn = r;
                    break;
                }
            }

            if (rentalToReturn == null) {
                throw new IllegalArgumentException("Error: Rental with ID " + rentalId + " not found.");
            }

            // Mark vehicle as available again
            rentalToReturn.getVehicle().setAvailable(true);
            rentals.remove(rentalToReturn); // Remove from active rentals

            System.out.println("\n--- Vehicle Returned Successfully! ---");
            System.out.println("Rental ID: " + rentalId + " for " + rentalToReturn.getVehicle().getRegistrationNumber() + " returned.");
            System.out.println("------------------------------------");

        } catch (IllegalArgumentException e) {
            System.err.println("Return Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during return: " + e.getMessage());
        }
    }

    public void displayAllRentals() {
        if (rentals.isEmpty()) {
            System.out.println("No active rentals.");
            return;
        }
        System.out.println("\n--- Active Rentals ---");
        for (Rental r : rentals) {
            System.out.println(r + "\n");
        }
        System.out.println("----------------------");
    }


    // Main method to run the application (for demonstration)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleRentalSystem system = new VehicleRentalSystem();

        // Adding some initial data
        system.addVehicle(new Car("Toyota", "Camry", 2022, 50.00, "ABC-123", 4, "Petrol"));
        system.addVehicle(new Motorcycle("Honda", "CBR500R", 2021, 35.00, "MOTO-456", 500, false));
        system.addVehicle(new Car("Honda", "CRV", 2023, 65.00, "XYZ-789", 5, "Petrol"));
        system.addVehicle(new Motorcycle("Kawasaki", "Ninja 400", 2020, 30.00, "BIKE-007", 400, false));

        system.addCustomer("Alice Smith", "123-456-7890"); // CUST1001
        system.addCustomer("Bob Johnson", "098-765-4321"); // CUST1002

        int choice;
        do {
            System.out.println("\n--- Vehicle Rental System Menu ---");
            System.out.println("1. Display All Vehicles");
            System.out.println("2. Display Available Vehicles");
            System.out.println("3. Display All Customers");
            System.out.println("4. Register New Customer");
            System.out.println("5. Rent Vehicle");
            System.out.println("6. Return Vehicle");
            System.out.println("7. Display All Rentals");
            System.out.println("8. Add New Vehicle"); // New option
            System.out.println("9. Remove Vehicle");  // New option
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.println();

                switch (choice) {
                    case 1:
                        system.displayAllVehicles();
                        break;
                    case 2:
                        system.displayAvailableVehicles();
                        break;
                    case 3:
                        system.displayAllCustomers();
                        break;
                    case 4:
                        System.out.print("Enter customer name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter contact number: ");
                        String contact = scanner.nextLine();
                        system.addCustomer(name, contact);
                        break;
                    case 5:
                        System.out.print("Enter Customer ID: ");
                        String custId = scanner.nextLine();
                        System.out.print("Enter Vehicle Registration Number: ");
                        String regNum = scanner.nextLine();
                        System.out.print("Enter Rental Days: ");
                        int days = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Start Date (YYYY-MM-DD): ");
                        String startDate = scanner.nextLine();
                        System.out.print("Enter End Date (YYYY-MM-DD): ");
                        String endDate = scanner.nextLine();
                        system.rentVehicle(custId, regNum, days, startDate, endDate);
                        break;
                    case 6:
                        System.out.print("Enter Rental ID to return: ");
                        String rentId = scanner.nextLine();
                        system.returnVehicle(rentId);
                        break;
                    case 7:
                        system.displayAllRentals();
                        break;
                    case 8: // New Add Vehicle option
                        System.out.println("\n--- Add New Vehicle ---");
                        System.out.print("Enter Vehicle Type (Car/Motorcycle): ");
                        String vehicleType = scanner.nextLine();
                        System.out.print("Enter Maker: ");
                        String make = scanner.nextLine();
                        System.out.print("Enter Model: ");
                        String model = scanner.nextLine();
                        System.out.print("Enter Year: ");
                        int year = scanner.nextInt();
                        System.out.print("Enter Rental Rate Per Day: ");
                        double rate = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Registration Number: ");
                        String newRegNum = scanner.nextLine();

                        if (vehicleType.equalsIgnoreCase("Car")) {
                            System.out.print("Enter Number of Doors: ");
                            int doors = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Enter Fuel Type: ");
                            String fuel = scanner.nextLine();
                            system.addVehicle(new Car(make, model, year, rate, newRegNum, doors, fuel));
                        } else if (vehicleType.equalsIgnoreCase("Motorcycle")) {
                            System.out.print("Enter Engine Displacement (CC): ");
                            int displacement = scanner.nextInt();
                            System.out.print("Has Helmet (true/false): ");
                            boolean hasHelmet = scanner.nextBoolean();
                            scanner.nextLine(); // Consume newline
                            system.addVehicle(new Motorcycle(make, model, year, rate, newRegNum, displacement, hasHelmet));
                        } else {
                            System.out.println("Invalid vehicle type. Please enter 'Car' or 'Motorcycle'.");
                        }
                        break;
                    case 9: // New Remove Vehicle option
                        System.out.print("Enter Registration Number of vehicle to remove: ");
                        String removeRegNum = scanner.nextLine();
                        system.removeVehicle(removeRegNum);
                        break;
                    case 0:
                        System.out.println("Exiting Vehicle Rental System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a valid data type (e.g., a number for choices/numeric fields, true/false for boolean).");
                scanner.nextLine(); // Consume the invalid input
                choice = -1; // Set choice to an invalid value to continue loop
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
                choice = -1; // Set choice to an invalid value to continue loop
            }
        } while (choice != 0);

        scanner.close();
    }
}
