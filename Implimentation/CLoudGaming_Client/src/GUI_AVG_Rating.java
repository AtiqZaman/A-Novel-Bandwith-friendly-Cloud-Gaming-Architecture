import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.TextArea;
import java.awt.TextField;

public class GUI_AVG_Rating {

    public JFrame frame;
    public JTextField txtCanada;
    String clientIp = null;
    String countryName = null;
    String vMAddress = null;
    String country = null;
    public JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        
    }

    /**
     * Create the application.
     * @wbp.parser.entryPoint
     */
    public GUI_AVG_Rating() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     * @param country 
     * @param vMAddress 
     * @param countryName 
     * @param clientIp 
     */
    public void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(0, 128, 128));
        frame.setBounds(100, 100, 449, 530);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        this.clientIp = clientIp;
        this.countryName = countryName;
        this.vMAddress = vMAddress;
        this.country = country;
        
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 450, 39);
        frame.getContentPane().add(panel);
        
        JLabel lblCloudGamingArchitecture = new JLabel("Cloud Gaming Architecture");
        lblCloudGamingArchitecture.setFont(new Font("Dialog", Font.BOLD, 24));
        panel.add(lblCloudGamingArchitecture);
        
        Component glue = Box.createGlue();
        glue.setBounds(156, 157, 1, 1);
        frame.getContentPane().add(glue);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(10, 51, 206, 39);
        frame.getContentPane().add(panel_2);
        
        JLabel lblClientC = new JLabel("Super Node IP");
        lblClientC.setFont(new Font("Dialog", Font.BOLD, 18));
        panel_2.add(lblClientC);
        
        txtCanada = new JTextField();
        txtCanada.setText(countryName);
        txtCanada.setColumns(10);
        txtCanada.setBounds(228, 51, 198, 39);
        frame.getContentPane().add(txtCanada);
        
        JPanel panel_5 = new JPanel();
        panel_5.setBounds(125, 184, 206, 39);
        frame.getContentPane().add(panel_5);
        
        JLabel lblVmSpecs = new JLabel("VM Specs");
        lblVmSpecs.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_5.add(lblVmSpecs);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(10, 119, 206, 39);
        frame.getContentPane().add(panel_1);
        
        JLabel lblAverageRating = new JLabel("Average Rating");
        lblAverageRating.setFont(new Font("Dialog", Font.BOLD, 18));
        panel_1.add(lblAverageRating);
        
        textField = new JTextField();
        textField.setText((String) null);
        textField.setColumns(10);
        textField.setBounds(228, 119, 198, 39);
        frame.getContentPane().add(textField);
        
        TextArea textArea = new TextArea();
        textArea.setText("VirtualBox version: 6.0.6\n\n192.168.50.23_CA\n1495\nUbuntu_64\norg.virtualbox_6_0.IGuestOSType@4e50df2e\nVM name: 192.168.50.23_CA\n RAM size: 1495MB, HWVirt: true, Nested Paging: true, PAE: false\n192.168.50.24_AU\n1495\ntrue\nfalse\nAttempting to start VM '192.168.50.26_SG'\n");
        textArea.setBounds(11, 229, 439, 254);
        frame.getContentPane().add(textArea);
    }
}