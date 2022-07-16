
import java.rmi.*;


public interface RoomManager extends Remote {

   public int[] list() throws RemoteException; 

   public boolean book(int type, String guest) throws RemoteException; 

   public String[] guests() throws RemoteException;

   public double[] revenue() throws RemoteException;
 
   public int[] occupied() throws RemoteException; 


}
