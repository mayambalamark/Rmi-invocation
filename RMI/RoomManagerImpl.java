
import java.rmi.*;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class RoomManagerImpl extends java.rmi.server.UnicastRemoteObject implements RoomManager{

    final static int[] capacity = {10, 20, 5,3,2};
    final static double[] price = {55000, 75000, 80000, 150000, 230000};
    
    ArrayList<String>[] rooms = (ArrayList<String>[]) new ArrayList[] 
    {new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(),
        new ArrayList<String>(),new ArrayList<String>()};
         
        public RoomManagerImpl ()
            throws RemoteException 
            {
            super();
            }
            
     
     // list of available rooms per type.
    
    public int[] list() throws RemoteException
    {
        int[] freeRooms = {capacity[0] - rooms[0].size(),
                           capacity[1] - rooms[1].size(),
                           capacity[2] - rooms[2].size(),
                           capacity[3] - rooms[3].size(),
                           capacity[4] - rooms[4].size()
                        };
        return freeRooms;
    }

   
     //Book a room of a certain type for a guest.
  
    public boolean book(int type, String guest) throws RemoteException
    {
        if(type < 0 || type > 4)
            return false;
 
        
        if(rooms[type].size() < capacity[type]) {
            if(rooms[type].add(guest)) {
                System.out.println(guest +" booked room type "+ type);
                return true;
            }
        }

        return false;
    }

  
     //Return a list of guests.
     
    public String[] guests() throws RemoteException
    {
        int i, j, ctr = 0;
        String[] guestList = new String[rooms[0].size() + rooms[1].size() + rooms[2].size() + rooms[3].size() + rooms[4].size()];
        for(i = 0; i < 5; i++) {
            for(j = 0; j < rooms[i].size(); j++) {
                guestList[ctr++] = rooms[i].get(j);
            }
        }
        System.out.println("The following are the guests");
        return guestList;
    }

    // Total revenue generated
    @Override
    public double[] revenue() throws RemoteException {

        double[] sum = {
            (rooms[0].size() * price[0]),
            (rooms[1].size() * price[1]),
            (rooms[2].size() * price[2]),
            (rooms[3].size() * price[3]),
            (rooms[4].size() * price[4]) 
        };

         return sum;
    }

    // Total number of rooms booked
    @Override
    public int[] occupied() throws RemoteException {

        int[] takenRooms = {
            rooms[0].size(),
            rooms[1].size(),
            rooms[2].size(),
            rooms[3].size(),
            rooms[4].size()
           
         };
        return takenRooms;
    }
}
