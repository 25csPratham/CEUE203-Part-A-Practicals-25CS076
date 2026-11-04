public class DriverSeatBooking {
    public static void main(String[] args) throws InterruptedException {
        SeatBooking booking = new SeatBooking();
        int totalRequests = 10;

        System.out.println("=== Unsynchronized Seat Booking ===");
        Thread[] threads = new Thread[totalRequests];
        for (int i = 0; i < totalRequests; i++) {
            final String passenger = "Passenger-" + (i + 1);
            threads[i] = new Thread(() -> booking.bookUnsafe(passenger));
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("Final seats left (unsafe): " + booking.getSeatsLeft());

        System.out.println("\n=== Synchronized Seat Booking ===");
        booking.reset();
        for (int i = 0; i < totalRequests; i++) {
            final String passenger = "Passenger-" + (i + 1);
            threads[i] = new Thread(() -> booking.bookSafe(passenger));
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("Final seats left (safe): " + booking.getSeatsLeft());
    }
}
