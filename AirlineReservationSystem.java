import java.util.Scanner;

class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private int capacity;
    private int seatsBooked;

    public Flight(String flightNumber, String origin, String destination, int capacity) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.capacity = capacity;
        this.seatsBooked = 0;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return capacity - seatsBooked;
    }

    public boolean bookSeats(int numSeats) {
        if (numSeats <= getAvailableSeats()) {
            seatsBooked += numSeats;
            return true;
        } else {
            return false;
        }
    }
}

class ReservationSystem {
    private Flight[] flights;

    public ReservationSystem() {
        flights = new Flight[3]; // Assume there are 3 flights
        flights[0] = new Flight("F001", "New York", "Los Angeles", 150);
        flights[1] = new Flight("F002", "Los Angeles", "Chicago", 200);
        flights[2] = new Flight("F003", "Chicago", "New York", 180);
    }

    public void displayFlights() {
        System.out.println("Available Flights:");
        System.out.println("Flight No. | Origin      | Destination | Available Seats");
        for (Flight flight : flights) {
            System.out.printf("%-10s | %-12s | %-12s | %-15d\n",
                    flight.getFlightNumber(), flight.getOrigin(), flight.getDestination(), flight.getAvailableSeats());
        }
    }

    public boolean bookFlight(String flightNumber, int numSeats) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight.bookSeats(numSeats);
            }
        }
        return false;
    }
}

public class AirlineReservationSystem {
    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n1. Display available flights");
            System.out.println("2. Book a flight");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reservationSystem.displayFlights();
                    break;
                case 2:
                    System.out.print("Enter the flight number: ");
                    String flightNumber = scanner.next();
                    System.out.print("Enter the number of seats to book: ");
                    int numSeats = scanner.nextInt();
                    if (reservationSystem.bookFlight(flightNumber, numSeats)) {
                        System.out.println("Booking successful!");
                    } else {
                        System.out.println("Booking failed. Not enough seats available.");
                    }
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        scanner.close();
        System.out.println("Thank you for using the reservation system.");
    }
}
