public class Ticket {
    private int id;
    private String passengerName;
    private int seatNumber;
    private String booked;

    public Ticket(int id, String passengerName, int seatNumber, String booked) {
        this.id = id;
        this.passengerName = passengerName;
        this.seatNumber = seatNumber;
        this.booked = booked;
    }

    public int getId() {
        return id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getBooked(){ return booked;}

    public void setId(int id) { this.id = id; }

    public void setPassengerName(String name){
        this.passengerName = passengerName;
    }

    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }

    public void setBooked(String booked){
        this.booked = booked;
    }


}
