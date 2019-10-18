import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class SuperNodeSelectionAvgR implements Runnable {

	Socket client;
	String Ip = null;
	int port = 0;
     int vm_no = 0;
     String supernodeIp = null;
		String vm_country= null;
		int noofconnection = 0;
		String vm_address = null;
		int noofplayerplayed = 0;
		 int ConnectedClient[];
		 String ratingbasesn = null;
		int rating = 0;
		int Rating = 0;
	public SuperNodeSelectionAvgR(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		
		ResultSet resulttoexceute;
		
		try {
			String Url = "jdbc:mysql://localhost:3306/avgratingdb";
			String username = "root";
			String password = "";
			Connection myconnection = DriverManager.getConnection(Url, username, password);
			Statement statement = myconnection.createStatement();
			String sqlfetchingalldata = "select * from avgratingdb.infoofsn";
			resulttoexceute = statement.executeQuery(sqlfetchingalldata);
             ConnectedClient = new int[5];
             int number = 0;
			while (resulttoexceute.next()) {
				ConnectedClient[number] = resulttoexceute.getInt("connected");
				number++;

			}
			System.out.println(number);
			int lessusers = smallest(ConnectedClient);
			System.out.println("LessUsers"+lessusers);
			if(lessusers==0) {
			String userlesssupernode =  Integer.toString(lessusers);
			///////////////////////
			  String sqlcollectinfoofselectednode = "select * from avgratingdb.infoofsn where connected = '" +  userlesssupernode + "'";
			  resulttoexceute = statement.executeQuery(sqlcollectinfoofselectednode); 
			  while(resulttoexceute.next()) {	  
			  noofconnection = resulttoexceute.getInt("connected");
			  supernodeIp = resulttoexceute.getString("ipaddress");
			  vm_address = resulttoexceute.getString("vmip");
			  vm_country = resulttoexceute.getString("country");
			  Rating = resulttoexceute.getInt("avgrating");
			  noofplayerplayed = resulttoexceute.getInt("connected");
			  vm_no =  resulttoexceute.getInt("id");
			  }		
			    String sqlIncreaseUsers = "UPDATE avgratingdb.infoofsn SET connected = connected+1 where vmip = '" +   vm_address + "'";
				statement.executeUpdate(sqlIncreaseUsers);
			try {
				System.out.println("Selected SuperNode For User" + supernodeIp);
				Socket Sn = new Socket(supernodeIp, 1284);
				DataOutputStream DoustoSN = new DataOutputStream(Sn.getOutputStream());
				DoustoSN.write(vm_no);
				DoustoSN.flush();			
				System.out.println("Conformation Messgae From SuperNode");
				DataInputStream Dis = new DataInputStream(Sn.getInputStream());	
				String Conformation = Dis.readUTF();
				System.out.println("Status of Super Node"+ Conformation);
				DataOutputStream Dous = new DataOutputStream(client.getOutputStream());
				Dous.writeUTF(vm_address);
				Dous.writeUTF(vm_country);
				Dous.write(noofplayerplayed);
				Dous.write(Rating);
				Dous.write(vm_no);
				Dous.flush();		 
			} catch (IOException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			else if(lessusers<2){
				System.out.println("Agya h Waha Jaha Zyada  USERS H ");
				 int AvgRating[] = new int[5];
				 int rate= 0 ;
				System.out.println("Sorry SuperNode Don't have too much space");
				System.out.println("Super Node WIth  Less Users" + lessusers);
				String userlesssupernode =  Integer.toString(lessusers);
				///////////////////////
				  String sqlRatingBaseSelectio = "select * from avgratingdb.infoofsn";  
				  resulttoexceute = statement.executeQuery(sqlRatingBaseSelectio);
				  while(resulttoexceute.next()) {	  
				  rating = resulttoexceute.getInt("avgrating");
				  System.out.println("Rating"+ rating);
				  AvgRating[rate] = rating;
				  rate++;
				  }	
				int bestrate = greatestrating(AvgRating);
				String bestrunrate = Integer.toString(bestrate);
				System.out.println("Best RUn Rate"+ bestrunrate);
				    String sqlBestRating = "select * from avgratingdb.infoofsn where avgrating = '" +  bestrunrate + "'";    
				    resulttoexceute  = statement.executeQuery(sqlBestRating);
					 while(resulttoexceute.next()) {
						
						 ratingbasesn = resulttoexceute.getString("ipaddress");
						  vm_address = resulttoexceute.getString("vmip");
						  vm_country = resulttoexceute.getString("country");
						  Rating = resulttoexceute.getInt("avgrating");
						  noofplayerplayed = resulttoexceute.getInt("connected");
						  
						  vm_no =  resulttoexceute.getInt("id");
						  System.out.println("Ye H VM NO"+vm_no);
					 }
					 String sql5 = "UPDATE avgratingdb.infoofsn SET connected = connected+1 where vmip = '" +   vm_address  + "'";
						statement.executeUpdate(sql5);
				try {
					Socket Sn = new Socket(ratingbasesn, 1284);
					System.out.println("Conformation Messgae From SuperNode");
					DataOutputStream doutoSN = new DataOutputStream(Sn.getOutputStream());
					doutoSN.write(vm_no);
					doutoSN.flush();
					
					DataInputStream Dis = new DataInputStream(Sn.getInputStream());
					  String ConformationofVMUP = Dis.readUTF();
					  System.out.println("Status of Super Node"+ ConformationofVMUP);
					DataOutputStream Dous = new DataOutputStream(client.getOutputStream());
					Dous.writeUTF(vm_address);
					Dous.writeUTF(vm_country);
					Dous.write(noofplayerplayed);
					Dous.write(Rating);
					Dous.write(vm_no);
					Dous.flush();
				} catch (IOException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(lessusers==2){
				System.out.println("BecauseEvery Node is Busy SO server will Server It ");
				
				try {
					Socket Sn = new Socket("localhost", 3000);
					System.out.println("Conformation Messgae From SuperNode");
					DataOutputStream Dous = new DataOutputStream(client.getOutputStream());
					Dous.writeUTF("192.168.50.17");
					Dous.writeUTF("LocalServer");
					Dous.write(0);
					Dous.write(0);
					Dous.write(5);
					Dous.flush();
				} catch (IOException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int smallest(int ConnectedClient[]) {
		int temp;  
		for (int i = 0; i < 4 ; i++)   
		        {  
		            for (int j = i + 1; j < 4 ; j++)   
		            {  
		                if (ConnectedClient[i] > ConnectedClient[j])   
		                {  
		                    temp = ConnectedClient[i];  
		                    ConnectedClient[i] = ConnectedClient[j];  
		                    ConnectedClient[j] = temp;  
		                }  
		            } 
		        } 
		return ConnectedClient[0];
	}
	
	public int greatestrating(int ConnectedClient[]) {
		int temp;  
		for (int i = 0; i < 4 ; i++)   
		        {  
		            for (int j = i + 1; j < 4 ; j++)   
		            {  
		                if (ConnectedClient[i] > ConnectedClient[j])   
		                {  
		                    temp = ConnectedClient[i];  
		                    ConnectedClient[i] = ConnectedClient[j];  
		                    ConnectedClient[j] = temp;  
		                }  
		            } 
		        } 
		return ConnectedClient[3];
	}
}
