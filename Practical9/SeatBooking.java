public class SeatBooking {
    private int seatsLeft = 5;

    public boolean bookUnsafe(String passenger) {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            seatsLeft--;
            System.out.println(passenger + " successfully booked a seat. Remaining: " + seatsLeft);
            return true;
        } else {
            System.out.println(passenger + " failed to book. Sold out!");
            return false;
        }
    }

    public synchronized boolean bookSafe(String passenger) {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            seatsLeft--;
            System.out.println(passenger + " successfully booked a seat. Remaining: " + seatsLeft);
            return true;
        } else {
            System.out.println(passenger + " failed to book. Sold out!");
            return false;
        }
    }

    public void reset() {
        seatsLeft = 5;
    }

    public int getSeatsLeft() {
        return seatsLeft;
    }
}
