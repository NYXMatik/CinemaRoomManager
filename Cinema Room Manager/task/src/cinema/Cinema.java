import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();

        int totalSeats = rows * seats;
        int currentIncome = 0;
        int totalIncome = calculateTotalIncome(rows, seats);

        char[][] cinemaRoom = new char[rows][seats];

        // Initialize cinemaRoom with empty seats
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinemaRoom[i][j] = 'S';
            }
        }

        while (true) {
            System.out.println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            System.out.println("Enter your choice:");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Cinema:");
                    printCinema(cinemaRoom);
                    break;
                case 2:
                    currentIncome += buyTicket(cinemaRoom, rows, totalSeats);
                    break;
                case 3:
                    showStatistics(cinemaRoom, totalSeats, currentIncome, totalIncome);
                    break;
                case 0:
                    return; // Exit the program
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    // Function to print the seating arrangement
    public static void printCinema(char[][] cinemaRoom) {
        System.out.print("  ");
        for (int i = 1; i <= cinemaRoom[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < cinemaRoom.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < cinemaRoom[0].length; j++) {
                System.out.print(cinemaRoom[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Function to buy a ticket
    public static int buyTicket(char[][] cinemaRoom, int rows, int totalSeats) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a row number:");
            int chosenRow = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            int chosenSeat = sc.nextInt();

            if (chosenRow < 1 || chosenRow > rows || chosenSeat < 1 || chosenSeat > cinemaRoom[0].length) {
                System.out.println("Wrong input! Please enter valid seat coordinates.");
                continue;
            }

            if (cinemaRoom[chosenRow - 1][chosenSeat - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                int price = getPrice(rows, chosenRow, totalSeats);
                System.out.println("Ticket price: $" + price);
                cinemaRoom[chosenRow - 1][chosenSeat - 1] = 'B';
                return price;
            }
        }
    }

    // Function to get price of the ticket
    public static int getPrice(int rows, int chosenRow, int totalSeats) {
        if (totalSeats <= 60 || chosenRow <= rows / 2) {
            return 10;
        } else {
            return 8;
        }
    }

    // Function to calculate total income
    public static int calculateTotalIncome(int rows, int seats) {
        int totalSeats = rows * seats;
        if (totalSeats <= 60) {
            return totalSeats * 10;
        } else {
            int frontHalfRows = rows / 2;
            int backHalfRows = rows - frontHalfRows;
            return (frontHalfRows * seats * 10) + (backHalfRows * seats * 8);
        }
    }

    // Function to show statistics
    public static void showStatistics(char[][] cinemaRoom, int totalSeats, int currentIncome, int totalIncome) {
        int purchasedTickets = countPurchasedTickets(cinemaRoom);
        double occupancyPercentage = (double) purchasedTickets / totalSeats * 100;

        System.out.println("\nNumber of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", occupancyPercentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    // Function to count the number of purchased tickets
    public static int countPurchasedTickets(char[][] cinemaRoom) {
        int purchasedTickets = 0;
        for (int i = 0; i < cinemaRoom.length; i++) {
            for (int j = 0; j < cinemaRoom[0].length; j++) {
                if (cinemaRoom[i][j] == 'B') {
                    purchasedTickets++;
                }
            }
        }
        return purchasedTickets;
    }
}
