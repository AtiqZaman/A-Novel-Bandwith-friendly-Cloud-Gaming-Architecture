																																																														import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ServerDataBaseOfAvgRating implements Runnable  {			
	ServerSocket ServerSocket=null;
	Socket Client;
	
	@Override
	public void run() {
		

		  String Url = "jdbc:mysql://localhost:3306/avgratingdb"; String username =
		  "root"; String password = ""; Connection myconnection;	
			ResultSet resulttoexceute;
			try {
	  myconnection = DriverManager.getConnection(Url, username, password);
	  Statement statement = myconnection.createStatement();
	 	
					try {
						ServerSocket = new ServerSocket(9022);
						ServerSocket.setReuseAddress(true);
                     while(true) {
							Client  = ServerSocket.accept();
							int userrate= 0;
							int noOfusers = 0;
							
							System.out.println("Conformation Messgae From SuperNode");
							DataInputStream Dis = new DataInputStream(Client.getInputStream());
							String vmaddress = Dis.readUTF();
							System.out.println("Ye H Upar wali IpAddress"+ vmaddress);
							String rate = Dis.readUTF();
			
			  String rateCome = "Select * from avgratingdb.infoofsn  where vmip = '" +
					  vmaddress+ "'";
			  
			  resulttoexceute = statement.executeQuery(rateCome); while
			  (resulttoexceute.next()) {  userrate = resulttoexceute.getInt("avgrating");
			  noOfusers = resulttoexceute.getInt("connected");
			  
			  } 
			  int newrate =
			  Integer.parseInt(rate);
			  System.out.println("Ye H Nawa rate"+ newrate );
			  int  avgrate = (newrate +userrate)/noOfusers+1;
			  String avgrateupdate = Integer.toString(avgrate); 
			  String SqlUpdateDB =
			  "UPDATE avgratingdb.infoofsn SET avgrating ='"+
					  avgrateupdate+"'"+"where vmip = '" +  vmaddress+ "'" ;
			      statement.executeUpdate(SqlUpdateDB);
			 
					}
					} catch (IOException e) {
						
						
						//e.printStackTrace();
						System.out.println("There is No Problem");
					}	
 } catch (SQLException e) { 
 e.printStackTrace(); }
					
				}
		
	
						
						 
					
					
}						
					

				
				
			

			
			
			
			
			
			
			
			
	