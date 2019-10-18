																																																														import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class ServerwithDB implements Runnable  {

	
	
		// TODO Auto-generated method stub

					/// Atiq's PC ip = "172.16.11.82"
					/// Here Will Be DataBase Code //
				
	ServerSocket ServerSocket=null;
	Socket Client;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

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
							int UserRate= 0;
							int NoOfUsers = 0;
							
							System.out.println("Conformation Messgae From SuperNode");
							DataInputStream Dis = new DataInputStream(Client.getInputStream());
							String VMAddress = Dis.readUTF();
							System.out.println("Ye H Upar wali IpAddress"+ VMAddress);
							String Rate = Dis.readUTF();
			
			  String RateCome = "Select * from avgratingdb.infoofsn  where vmip = '" +
					  VMAddress+ "'";
			  
			  resulttoexceute = statement.executeQuery(RateCome); while
			  (resulttoexceute.next()) { UserRate = resulttoexceute.getInt("avgrating");
			  NoOfUsers = resulttoexceute.getInt("connected");
			  
			  } System.out.println("Ye H Connected User"+ NoOfUsers);
			  System.out.println("Ye H Rate User"+ UserRate ); 
			  int NawaRate =
			  Integer.parseInt(Rate);
			  System.out.println("Ye H Nawa Rate"+ NawaRate );
			  int  AvgRate = (NawaRate +UserRate)/NoOfUsers+1;
			  String AvgRateS = Integer.toString(AvgRate); 
			  System.out.println("Ye H Avg Rate"+ AvgRate);
			  System.out.println("Ye H VMAddress"+  VMAddress);
			  System.out.println("Ye H Rate"+ Rate );
			  
			  
			  String sqlt =
			  "UPDATE avgratingdb.infoofsn SET avgrating = avgrating + Rate2 where vmip = '"
			  +  VMAddress+ "'"; String Sql5 =
			  "UPDATE avgratingdb.infoofsn SET avgrating ='"+
			  AvgRateS+"'"+"where vmip = '" +  VMAddress+ "'" ;
			  statement.executeUpdate(Sql5);
			 
					}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						
						//e.printStackTrace();
						System.out.println("There is No Problem");
					}	
		           
					
		

 } catch (SQLException e) { // TODO Auto-generated catch block
 e.printStackTrace(); }

				
				
						/*
						 * while(true) { if(Conformation == "VMUp") { System.out.println("VM Is Up");
						 * break; } }
						 */
					//Dous.writeDouble(1285); // Dous.write(1285); Dous.flush(); Dous.close();
					
				}
		
	
						
						 
					
					
}						
					// Here End of the DataBase Code //

					// Socket outer = new Socket(Ip,port);

				
				
			

			
			
			
			
			
			
			
			
	