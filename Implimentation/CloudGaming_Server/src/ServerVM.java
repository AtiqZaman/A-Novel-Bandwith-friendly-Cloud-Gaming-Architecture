import java.io.DataInputStream;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.virtualbox_5_2.IVirtualBox;
import org.virtualbox_5_2.VBoxException;
import org.virtualbox_5_2.VirtualBoxManager;

public class ServerVM {

   
    public static void main(String[] args) throws IOException {
   
        // TODO Auto-generated method stub
        ServerSocket superNodes = null;
        Socket client;
       
        String SuperNodeIp = null;
           InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("System IP Address : " +
                          (localhost.getHostAddress()).trim());
     
            // Find public IP address
            String systemipaddress = "";
            try(final DatagramSocket socket = new DatagramSocket()){
                  socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                  SuperNodeIp = socket.getLocalAddress().getHostAddress();
                 
                          }
       
        try {
            superNodes = new ServerSocket(3000);
            System.out.println("ServerVM Is Running");
            superNodes.setReuseAddress(true);
            while(true) {
               
                 client = superNodes.accept();
               
                 
                 
                 
                //VM up code starts from here
                    
                 VirtualBoxManager mgr = VirtualBoxManager.createInstance(null);
                 
                 try
                    {
                        IVirtualBox vbox = mgr.getVBox();
                        if (vbox != null)
                        {
                            System.out.println("VirtualBox version: " + vbox.getVersion() + "\n");
                            VMup.testEnumeration(mgr, vbox);
                            VMup.testReadLog(mgr, vbox);
                            VMup.testStart(mgr, vbox);
                            VMup.testEvents(mgr, vbox.getEventSource());

                            System.out.println("done, press Enter...");
                            System.out.println("SuperNode Running after That Thing"); DataOutputStream
                              Dous = new DataOutputStream(client.getOutputStream());
                            Dous.writeUTF("VM is Up and running");
                              Dous.flush();
                              Dous.close();
                            int ch = System.in.read();
                        }
                    }
                    catch (VBoxException e)
                    {
                        VMup.printErrorInfo(e);
                        System.out.println("Java stack trace:");
                        e.printStackTrace();
                    }
                    catch (RuntimeException e)
                    {
                        System.out.println("Runtime error: " + e.getMessage());
                        e.printStackTrace();
                    }
                    catch (java.io.IOException e)
                    {
                        e.printStackTrace();
                    }
            }
           
           
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
       

    }

