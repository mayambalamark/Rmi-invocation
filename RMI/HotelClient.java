
import java.rmi.Naming;
import java.util.Arrays;

public class HotelClient {
    public static void main (String [] args) {
        
      
            // Argument parsing
            if(args.length < 2)
                commandUsage();
    
            try
            {
                RoomManager room = (RoomManager) Naming.lookup("rmi://localhost:1099/RoomManagerService"); 
                if(args[0].compareTo("list") == 0)
                    List(room);
                else if(args[0].compareTo("book") == 0)
                    Book(args, room);
                else if(args[0].compareTo("guests") == 0)
                    Guests(room);
                else if(args[0].compareTo("revenue") == 0)
                    Revenue(room);
                else
                    commandUsage();
            }
            catch (Exception e)
            {
                System.out.println("Received Exception:");
                System.out.println(e); 
            }
        }
    
        
         // Handle the request for a list of free rooms.
         
        static private void List(RoomManager room) {
            int[] rooms = new int[5];
            try {
                rooms = room.list();
            } catch (Exception e) {
                System.out.println("Received Exception:"); 
                System.out.println(e); 
            }
            System.out.println(
                "\n\t"  + rooms[0] + " rooms of type 0 are available for 55,000 UGX per night" + 
                "\n\t"  + rooms[1] + " rooms of type 1 are available for 75,000 UGX per night" +
                 "\n\t" + rooms[2] + " rooms of type 2 are available for 80,000 UGX per night" + 
                 "\n\t" + rooms[3] + " rooms of type 3 are available for 150,000 UGX per night" + 
                 "\n\t" + rooms[4] + " rooms of type 4 are available for 230,000 UGX per night" + "\n"
                 );
        }
    
       
         //  the request for booking a room.
         
        static private void Book(String[] args, RoomManager room) {
            String name = "";
            if(args.length < 4)
                commandUsage();
    
            for(int i = 3; i < args.length; i++) {
                if(i != 3)
                    name += " ";
                name += args[3];
            }
    
            try {
                if(!room.book(Integer.parseInt(args[2]), name))
                    System.out.println("Failed to book");
            } catch (Exception e) {
                System.out.println("Received Exception:");
                System.out.println(e); 
            }
        }
    
        
         // Handle the request for a list of guests.
        
        static private void Guests(RoomManager room) {
            try {
                String[] guests = room.guests();
                for(int i = 0; i < guests.length; i++)
                    System.out.println(guests[i]);
            } catch (Exception e) {
                System.out.println("Received Exception:");
                System.out.println(e);
            }
        }
     
         
        //  Handle the request for revenue.
        
         
        static private void Revenue(RoomManager room) {
            try {
                
              
                double[] sum = room.revenue();
                

                int[] rooms = new int[5];
                rooms = room.occupied();
                System.out.println(
                "\n\t"  + rooms[0] + " room(s) of type 0 were booked for 55,000 UGX per night" + 
                "\n\t"  + rooms[1] + " room(s) of type 1 were booked for 75,000 UGX per night" +
                 "\n\t" + rooms[2] + " room(s) of type 2 were booked for 80,000 UGX per night" + 
                 "\n\t" + rooms[3] + " room(s) of type 3 were booked for 150,000 UGX per night" + 
                 "\n\t" + rooms[4] + " room(s) of type 4 were booked for 230,000 UGX per night" + "\n");

              
                System.out.println("Total revenue is UGX "+ Arrays.stream(sum).sum());
                
            } catch (Exception e) {
                System.out.println("Received Exception:");
                System.out.println(e);
            }
        }
    
        
         // printing how to use the command
        
        static private void commandUsage() {
            System.out.println("Available options:\n\tHotelClient list <server address>\n" +
                               "\tHotelClient book <server address> <room type> <guest name>\n" +
                               "\tHotelClient guests <server address>\n" +
                               "\tHotelClient revenue <server address>\n");
            System.exit(1);
        }
    
}
