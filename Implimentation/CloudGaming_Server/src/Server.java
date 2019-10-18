import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Server {

	public static void main(String[] args) {
		
		
		ServerSocket serverSocket = null;
		
		//initialize a client socket  
		Socket client; 
		  try { 
		  serverSocket = new ServerSocket(1203);
		  
		  System.out.println("Server Is Running");
		  
		  serverSocket.setReuseAddress(true);
		  while(true) {
		  client= serverSocket.accept();
		  
		  SuperNodeSelectionAvgR SNSBasedOnAvgRating = new SuperNodeSelectionAvgR(client);
		   Thread Selectionthread = new Thread(SNSBasedOnAvgRating);
		   Selectionthread.start();
		   ServerDataBaseOfAvgRating SDB =new  ServerDataBaseOfAvgRating ();
		   Thread databasethread = new Thread(SDB);
		   databasethread.start();	  
		  }
		  } catch (IOException e) { // TODO Auto-generated catch block
		  e.printStackTrace(); }
		 
		

	}

}
