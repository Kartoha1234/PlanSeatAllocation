import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       TicketController ticketController = new TicketController();
        String comand = "";
        Scanner scanner = new Scanner(System.in);
        while (!comand.equals("exit")){
            System.out.println("\r\n\r\n" +
                    "Enter \"read\" to read all tickets from list \r\n" +
                    "Enter \"update\" to update a ticket in the list \r\n" +
                    "Enter \"book\" to book a ticket \r\n" +
                    "Enter \"return\" to return a ticket \r\n" +
                    "Enter \"exit\" to exit \r\n");
            comand = scanner.nextLine();
            switch (comand){
                case "read":{
                    var tickets = ticketController.ReadTickets();
                    for (Ticket ticket : tickets){
                        System.out.println("Id: " + ticket.getId() + ", Passenger name: " + ticket.getPassengerName() + ", Seat number: " + ticket.getSeatNumber() + ", Booked: " + ticket.getBooked());
                    }
                    break;}
                case "update":{
                    System.out.println("Please type the Id of ticket: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Please type the passenger name of ticket: ");
                    String passengerName = scanner.nextLine();
                    ticketController.UpdateTicket(id,passengerName);
                    break;}
                case "book":{
                    var tickets = ticketController.ReadTickets();
                    System.out.println("Please type the Id of ticket: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Please type your name: ");
                    String passengerName = scanner.nextLine();
                    ticketController.BookTicket(id,passengerName);
                    break;}
                case "return":{
                    System.out.println("Please type Id of ticket: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    ticketController.ReturnTicket(id);
                    break;}
            }
        }
    }
}

