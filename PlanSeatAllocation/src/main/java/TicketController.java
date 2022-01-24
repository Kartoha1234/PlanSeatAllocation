import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketController {
    public ArrayList<Ticket> ReadTickets(){
        ArrayList<Ticket>tickets = new ArrayList<Ticket>();
        try {
            Scanner sc = new Scanner(new File("Tickets.csv"));
            sc.useDelimiter(",");
            sc.nextLine();
            while (sc.hasNextLine())
            {
                tickets.add(getDataFromLine(sc.nextLine()));
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return tickets;
    }

    private Ticket getDataFromLine(String line){
        int id;
        String passengerName;
        int seatNumber;
        String booked;
        Ticket result = null;
        try {
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            id = lineScanner.nextInt();
            passengerName = lineScanner.next();
            seatNumber = lineScanner.nextInt();
            booked = lineScanner.nextLine();
            result = new Ticket(id,passengerName,seatNumber,booked);
        }catch (Exception e){

        }
        return result;
    }

    public void WriteTickets(ArrayList<Ticket> tickets){
        try (PrintWriter writer = new PrintWriter("Tickets.csv")) {

            StringBuilder sb = new StringBuilder();
            sb.append("Id,");
            sb.append("Passenger name,");
            sb.append("Seat number,");
            sb.append("Booked\r\n");
            for (Ticket ticket : tickets){
                sb.append(ticket.getId());
                sb.append(',');
                sb.append(ticket.getPassengerName());
                sb.append(',');
                sb.append(ticket.getSeatNumber());
                sb.append(",");
                sb.append(ticket.getBooked());
                sb.append("\r\n");

            }
            writer.write(sb.toString());

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void UpdateTicket(int id,String passengerName){
        var tickets = ReadTickets();
        int indexOfTicket = -1;
        for (int i = 0; i < tickets.size(); i++){
            if (tickets.get(i).getId() == id){
                indexOfTicket= i;
            }
        }
        if (indexOfTicket != -1){
            var original = tickets.get(indexOfTicket);
            tickets.set(indexOfTicket,new Ticket(original.getId(),passengerName,original.getSeatNumber(),"Not_Booked"));
        }
        WriteTickets(tickets);
    }

    public void BookTicket(int id,String passengerName){
        var tickets = ReadTickets();
        int indexOfTicket = -1;
        for (int i = 0; i < tickets.size(); i++){
            if (tickets.get(i).getId() == id){
                indexOfTicket = i;
            }
        }
        Ticket original = null;
        if (indexOfTicket != -1){
            String bookedBy = tickets.get(indexOfTicket).getBooked();
            if (bookedBy.equals("Not_Booked")){
                original = tickets.get(indexOfTicket);
                tickets.set(indexOfTicket,new Ticket(original.getId(),passengerName, original.getSeatNumber(),"Not_Booked"));
            }
        }
        WriteTickets(tickets);
        RegisterEvent("Ticket booked by " + passengerName,original);
    }

    public void ReturnTicket(int id){
        var tickets = ReadTickets();
        int indexOfTicket = -1;
        for (int i = 0; i < tickets.size(); i++){
            if (tickets.get(i).getId() == id){
                indexOfTicket = i;
            }
        }
       Ticket original = null;
        if (indexOfTicket != -1){
            String bookedBy = tickets.get(indexOfTicket).getBooked();
            if (!bookedBy.equals("Not_Booked")){
                original = tickets.get(indexOfTicket);
                tickets.set(indexOfTicket,new Ticket(original.getId(),original.getPassengerName(), original.getSeatNumber(),"Not_Booked"));
            }
        }
        WriteTickets(tickets);
        RegisterEvent("Ticket returned ",original);
    }

    public void RegisterEvent(String event, Ticket ticket){
        ArrayList<String>records = new ArrayList<String>();

        try {
            Scanner sc = new Scanner(new File("Tickets_Records.csv"));
            while (sc.hasNextLine())
            {
                records.add(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try (PrintWriter writer = new PrintWriter("Tickets_Records.csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append(event +",");
            sb.append(" Time: " + LocalDateTime.now());
            sb.append(" Ticket Id: " + ticket.getId() + ",");
            sb.append(" Passenger name: " + ticket.getPassengerName() + ",");
            sb.append(" Booked: " +ticket.getBooked() + "\r\n");
            records.add(sb.toString());
            StringBuilder sb1 = new StringBuilder();
            for (String record : records){
                sb1.append(record + "\r\n");
            }
            writer.write(sb1.toString());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
