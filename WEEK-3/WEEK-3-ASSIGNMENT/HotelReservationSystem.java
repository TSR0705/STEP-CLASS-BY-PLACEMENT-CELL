import java.util.Scanner;

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialize hotel details
        Booking.setHotelName("Grand Palace Hotel");

        // Create some rooms
        Room[] rooms = new Room[6];
        rooms[0] = new Room("R101", "Single", 2000, true, 1);
        rooms[1] = new Room("R102", "Single", 2000, true, 1);
        rooms[2] = new Room("R201", "Double", 3500, true, 2);
        rooms[3] = new Room("R202", "Double", 3500, true, 2);
        rooms[4] = new Room("R301", "Suite", 7000, true, 4);
        rooms[5] = new Room("R302", "Suite", 7000, true, 4);

        // Create some guests
        Guest[] guests = new Guest[3];
        guests[0] = new Guest("G001", "Amit Kumar", "9876543210", "amit@email.com");
        guests[1] = new Guest("G002", "Priya Sharma", "9123456780", "priya@email.com");
        guests[2] = new Guest("G003", "Raj Singh", "9988776655", "raj@email.com");

        // Storage for bookings
        Booking[] bookings = new Booking[20];
        int bookingCount = 0;

        int choice;
        do {
            System.out.println("\n===== " + Booking.getHotelName() + " Reservation Menu =====");
            System.out.println("1. View All Rooms");
            System.out.println("2. Check Room Availability");
            System.out.println("3. Make Reservation");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. View All Bookings");
            System.out.println("6. Hotel Reports");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Room List ---");
                    for (Room r : rooms) {
                        r.displayRoomInfo();
                    }
                    break;

                case 2:
                    System.out.print("Enter room type (Single/Double/Suite): ");
                    String type = sc.nextLine();
                    boolean found = false;
                    for (Room r : rooms) {
                        if (r.getRoomType().equalsIgnoreCase(type) && r.isAvailable()) {
                            r.displayRoomInfo();
                            found = true;
                        }
                    }
                    if (!found) System.out.println("No available rooms of this type.");
                    break;

                case 3:
                    System.out.print("Enter Guest ID: ");
                    String guestId = sc.nextLine();
                    Guest guest = null;
                    for (Guest g : guests) {
                        if (g.getGuestId().equals(guestId)) guest = g;
                    }
                    if (guest == null) {
                        System.out.println("Guest not found!");
                        break;
                    }

                    System.out.print("Enter Room Number: ");
                    String roomNo = sc.nextLine();
                    Room room = null;
                    for (Room r : rooms) {
                        if (r.getRoomNumber().equals(roomNo)) room = r;
                    }
                    if (room == null || !room.isAvailable()) {
                        System.out.println("Room not available!");
                        break;
                    }

                    System.out.print("Enter Check-In Date (dd-mm-yyyy): ");
                    String checkIn = sc.nextLine();
                    System.out.print("Enter Check-Out Date (dd-mm-yyyy): ");
                    String checkOut = sc.nextLine();
                    System.out.print("Enter number of nights: ");
                    int nights = sc.nextInt();
                    sc.nextLine();

                    Booking newBooking = new Booking("B" + (bookingCount + 1), guest, room, checkIn, checkOut);
                    newBooking.makeReservation(nights);

                    bookings[bookingCount++] = newBooking;
                    guest.addBooking(newBooking.getBookingId());
                    break;

                case 4:
                    System.out.print("Enter Booking ID to cancel: ");
                    String cancelId = sc.nextLine();
                    boolean cancelled = false;
                    for (int i = 0; i < bookingCount; i++) {
                        if (bookings[i].getBookingId().equals(cancelId)) {
                            bookings[i].cancelReservation();
                            cancelled = true;
                        }
                    }
                    if (!cancelled) System.out.println("Booking not found!");
                    break;

                case 5:
                    System.out.println("\n--- All Bookings ---");
                    for (int i = 0; i < bookingCount; i++) {
                        bookings[i].displayBookingInfo();
                    }
                    break;

                case 6:
                    System.out.println("\n--- Hotel Reports ---");
                    System.out.println("Total Bookings: " + Booking.getTotalBookings());
                    System.out.println("Total Revenue: " + Booking.getTotalRevenue());
                    System.out.println("Occupancy Rate: " + Booking.getOccupancyRate(rooms) + "%");
                    System.out.println("Most Popular Room Type: " + Booking.getMostPopularRoomType(bookings));
                    break;

                case 0:
                    System.out.println("Exiting system. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        sc.close();
    }
}

// ========== ROOM CLASS ==========
class Room {
    private String roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean isAvailable;
    private int maxOccupancy;

    public Room(String roomNumber, String roomType, double pricePerNight, boolean isAvailable, int maxOccupancy) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;
        this.maxOccupancy = maxOccupancy;
    }

    public void displayRoomInfo() {
        System.out.println("Room: " + roomNumber + " | Type: " + roomType +
                           " | Price: " + pricePerNight + " | Available: " + isAvailable);
    }

    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { this.isAvailable = available; }
}

// ========== GUEST CLASS ==========
class Guest {
    private String guestId;
    private String guestName;
    private String phoneNumber;
    private String email;
    private String[] bookingHistory;
    private int bookingCount;

    public Guest(String guestId, String guestName, String phoneNumber, String email) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bookingHistory = new String[10];
        this.bookingCount = 0;
    }

    public void addBooking(String bookingId) {
        bookingHistory[bookingCount++] = bookingId;
    }

    public String getGuestId() { return guestId; }
    public String getGuestName() { return guestName; }
}

// ========== BOOKING CLASS ==========
class Booking {
    private String bookingId;
    private Guest guest;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private double totalAmount;

    private static int totalBookings = 0;
    private static double hotelRevenue = 0;
    private static String hotelName;

    public Booking(String bookingId, Guest guest, Room room, String checkInDate, String checkOutDate) {
        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public void makeReservation(int nights) {
        if (!room.isAvailable()) {
            System.out.println("Room not available!");
            return;
        }
        totalAmount = nights * room.getPricePerNight();
        room.setAvailable(false);
        hotelRevenue += totalAmount;
        totalBookings++;
        System.out.println("Reservation Successful! Booking ID: " + bookingId);
        displayBookingInfo();
    }

    public void cancelReservation() {
        if (!room.isAvailable()) {
            room.setAvailable(true);
            hotelRevenue -= totalAmount;
            totalBookings--;
            System.out.println("Reservation " + bookingId + " cancelled.");
        } else {
            System.out.println("This booking is already cancelled.");
        }
    }

    public void displayBookingInfo() {
        System.out.println("BookingID: " + bookingId + " | Guest: " + guest.getGuestName() +
                           " | Room: " + room.getRoomNumber() +
                           " | Dates: " + checkInDate + " to " + checkOutDate +
                           " | Amount: " + totalAmount);
    }

    public String getBookingId() { return bookingId; }
    public Room getRoom() { return room; }
    public String getRoomType() { return room.getRoomType(); }

    // Static Methods
    public static void setHotelName(String name) { hotelName = name; }
    public static String getHotelName() { return hotelName; }
    public static int getTotalBookings() { return totalBookings; }
    public static double getTotalRevenue() { return hotelRevenue; }

    public static double getOccupancyRate(Room[] rooms) {
        int occupied = 0;
        for (Room r : rooms) {
            if (!r.isAvailable()) occupied++;
        }
        return (occupied * 100.0) / rooms.length;
    }

    public static String getMostPopularRoomType(Booking[] bookings) {
        int single = 0, dbl = 0, suite = 0;
        for (Booking b : bookings) {
            if (b == null) continue;
            switch (b.getRoomType().toLowerCase()) {
                case "single": single++; break;
                case "double": dbl++; break;
                case "suite": suite++; break;
            }
        }
        if (single >= dbl && single >= suite) return "Single";
        if (dbl >= single && dbl >= suite) return "Double";
        return "Suite";
    }
}
