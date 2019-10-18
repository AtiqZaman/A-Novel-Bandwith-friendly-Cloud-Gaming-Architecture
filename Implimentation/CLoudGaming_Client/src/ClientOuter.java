import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientOuter implements Runnable {
    String ipOfSuperNode = null;
    double supernodeport = 0;
    String VMAddress = null;
    String ServerIp = null;
    int ServerPort = 0;
	public ClientOuter(String ServerIp ,int ServerPort ,String VMAddress) {	
		this.VMAddress = VMAddress;
		this.ServerIp = ServerIp;
		this.ServerPort = ServerPort;	
	}

	@Override
	public void run() {
		try {		
			  Scanner input = new Scanner(System.in);
			  System.out.println("IpOfSuperNode"+ipOfSuperNode);
			  System.out.println("Write Your INput"); 
			  String Rate = input.next();
			  Socket clientwithDB = new Socket("192.168.50.5",9022); 
			  DataOutputStream DouswithDB = new  DataOutputStream(clientwithDB.getOutputStream()); 
			  DouswithDB.writeUTF(VMAddress);
			  DouswithDB.writeUTF(Rate); 
			  DouswithDB.close(); 
			  clientwithDB.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
