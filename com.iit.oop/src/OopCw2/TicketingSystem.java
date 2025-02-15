



package OopCw2;

import java.util.Scanner;

public class TicketingSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Configuration setup with input validation
        int vendors = getValidatedInput(scanner, "Enter the number of vendors:");
        int customers = getValidatedInput(scanner, "Enter the number of customers:");
        int totalTickets = getValidatedInput(scanner, "Enter total tickets:");
        int maxPoolTickets = getValidatedInput(scanner, "Enter max pool size:");
        int ticketReleaseRate = getValidatedInput(scanner, "Enter ticket release rate:");
        int customerRetrievalRate = getValidatedInput(scanner, "Enter ticket retrieval rate:");

        // Setup configuration and ticket pool
        Configuration config = new Configuration(totalTickets, maxPoolTickets, ticketReleaseRate, customerRetrievalRate);
        TicketPool ticketPool = new TicketPool(config.getMaxPoolTickets(), config.getTotalTickets());

        // Prompt the user for simulation control
        int command = getCommandInput(scanner);

        if (command == 1) {
            System.out.println("Simulation started...");

            // Loop to create and start vendor threads
            int ticketsPerVendor = totalTickets / vendors;
            for (int i = 1; i <= vendors; i++) {
                Vendor vendor = new Vendor(ticketPool, config.getTicketReleaseRate(), ticketsPerVendor);
                Thread vendorThread = new Thread(vendor, "Vendor-" + i);
                vendorThread.start();
            }

            // Loop to create and start customer threads
            for (int i = 1; i <= customers; i++) {
                Customer customer = new Customer(ticketPool, config.getCustomerRetrievalRate());
                Thread customerThread = new Thread(customer, "Customer-" + i);
                customerThread.start();
            }

            // Wait for threads to complete
            try {
                System.out.println("Simulation running. Waiting for threads to complete...");
                Thread.sleep(5000); // Adjust delay time as per your simulation duration
            } catch (InterruptedException e) {
                System.out.println("Simulation interrupted!");
            }

            System.out.println("Simulation completed. Exiting the program...");
            System.exit(0);

            // Optional: Add logic to monitor threads or gracefully terminate them
        } else if (command == 2) {
            System.out.println("Simulation terminated by user.");
        } else {
            System.out.println("Invalid input. Exiting.");
        }

        scanner.close();
    }

    // Method to validate inputs for configuration
    private static int getValidatedInput(Scanner scanner, String prompt) {
        int value;
        while (true) {
            try {
                System.out.println(prompt);
                value = scanner.nextInt();
                if (value <= 0) {
                    System.out.println("Error: Value must be greater than zero. Please try again.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error: Invalid input. Please enter a positive integer.");
                scanner.next(); // Clear invalid input
            }
        }
        return value;
    }

    // Method to validate command input (1 or 2)
    private static int getCommandInput(Scanner scanner) {
        int command;
        while (true) {
            try {
                System.out.println("Enter 1 to simulate, 2 to quit:");
                command = scanner.nextInt();
                if (command == 1 || command == 2) {
                    break;
                } else {
                    System.out.println("Error: Invalid command. Please enter 1 or 2.");
                }
            } catch (Exception e) {
                System.out.println("Error: Invalid input. Please enter 1 or 2.");
                scanner.next(); // Clear invalid input
            }
        }
        return command;
    }
}

