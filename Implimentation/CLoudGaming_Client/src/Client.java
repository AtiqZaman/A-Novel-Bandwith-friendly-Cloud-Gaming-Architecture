import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.*;
import java.awt.EventQueue;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.net.InetAddress;

public class Client {

	static String ServerIp = "192.168.50.5";
	int ServerPort = 9023;

	// Java program to find IP address of your computer
	// java.net.InetAddress class provides method to get
	// IP of any host name

	public GUI_AVG_Rating window;

	public static void main(String args[]) throws Exception {

		// gui code

		Client dbs = new Client();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_AVG_Rating window = new GUI_AVG_Rating();
					window.frame.setVisible(true);

					dbs.window = window;

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// end gui code

		//

		// Returns the instance of InetAddress containing
		// local host name and address
		InetAddress localhost = InetAddress.getLocalHost();
		System.out.println("System IP Address : " + (localhost.getHostAddress()).trim());

		// Find public IP address
		String systemipaddress = "";
		try (final DatagramSocket socket = new DatagramSocket()) {
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			String clientip = socket.getLocalAddress().getHostAddress();
			System.out.println(clientip);
			Socket client = new Socket("192.168.50.5", 1203);

			DataInputStream dis = new DataInputStream(client.getInputStream());
			String VMAddress = dis.readUTF();
			String Country = dis.readUTF();

			int NoofplayerConnected = dis.read();
			String players = Integer.toString(NoofplayerConnected);

			int Rating = dis.read();
			String PlayersRating = Integer.toString(Rating);
			int VM_NO = dis.read();
			// double PortOfSuperNode = dis.readDouble();
			System.out.println("Status" + VMAddress);
			System.out.println("IP Of SuperNode" + Country);
			System.out.println("VM Address" + NoofplayerConnected);
			System.out.println("VM_No is" + ":" + VM_NO);
			String rate = Integer.toString(Rating);

			dbs.window.txtCanada.setText(VMAddress);
			dbs.window.textField.setText(rate);
			if (VM_NO == 0) {
				TimeUnit.SECONDS.sleep(50);
				System.out.println("Now Connection is eastablished");
				GameLuncherCanada GL = new GameLuncherCanada();
				Thread TVMUP = new Thread(GL);
				TVMUP.start();
			}

			else if (VM_NO == 1) {
				TimeUnit.SECONDS.sleep(50);
				System.out.println("Now Connection is eastablished");
				GameLauncherAustralia GL = new GameLauncherAustralia();
				Thread TVMUP = new Thread(GL);
				TVMUP.start();
			}

			else if (VM_NO == 2) {
				TimeUnit.SECONDS.sleep(50);
				System.out.println("Now Connection is eastablished");
				GameLauncherSingapore GL = new GameLauncherSingapore();
				Thread TVMUP = new Thread(GL);
				TVMUP.start();
			}

			else if (VM_NO == 3) {
				TimeUnit.SECONDS.sleep(50);
				System.out.println("Now Connection is eastablished");
				GameLauncherUK GL = new GameLauncherUK();
				Thread TVMUP = new Thread(GL);
				TVMUP.start();
			} else if (VM_NO == 5) {
				TimeUnit.SECONDS.sleep(50);
				System.out.println("Now Connection is eastablished");
				GameLauncherServer GL = new GameLauncherServer();
				Thread TVMUP = new Thread(GL);
				TVMUP.start();
			}
			dis.close();
			client.close();
			ClientOuter cout = new ClientOuter(ServerIp, 9023, VMAddress);
			Thread t1 = new Thread(cout);
			t1.start();

		}

		catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) { //
			e.printStackTrace();
		}

	}

}
