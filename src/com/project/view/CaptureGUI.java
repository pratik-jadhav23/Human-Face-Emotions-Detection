
package com.project.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class CaptureGUI implements ActionListener{
	private JFrame mainFrame;
	private JPanel panel1, panel2, panel3, panel4;
	
	static JLabel lblHeading, lbUserName, lbAddress, lbUserEmail;
	private JButton btnStop, btnNext;
    private JTextField txtUserName,txtUserAddress,txtUserEmail;
    static JPanel face,fdetect,eyeDetect;
    static JLabel status,msg;
    static int rectX=0;static int rectY=0;
	static int rectWidth=0,rectHeight=0;
	
	String storedpath="E:/TrainData/Captured";
	//String checkpath="E:/SmartMirrorData/Upload/Check";

	public static String preference=null;
	public static String interest=null;
	public static String username=null;
    
    private Boolean isAlive=false;
    Thread thread = null;
    CaptureGUI()
    {
    	prepareGui();
    }
    public void prepareGui()
    {

    	mainFrame = new JFrame("EMOTION BASED MUSIC PLAYER");
		mainFrame.setSize(670, 550);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(null);
		mainFrame.setResizable(false);
		Container container = mainFrame.getContentPane();
		container.setBackground(Color.black);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	panel1 = new JPanel();
		panel1.setBounds(10, 5, 650, 80);
		panel1.setLayout(null);
		panel1.setOpaque(false);
		panel1.setBorder(BorderFactory.createLineBorder(Color.black));

		panel2 = new JPanel();
		panel2.setBounds(10, 90, 650, 350);
		panel2.setLayout(null);
		panel2.setBorder(BorderFactory.createLineBorder(Color.black));

		
		panel4 = new JPanel();
		panel4.setBounds(10, 450, 650, 60);
		panel4.setLayout(null);
		
		// Initialize Components
		lblHeading = new JLabel("EMOTION BASED MUSIC PLAYER");
		lblHeading.setBounds(150, 20, 1000, 30);
		lblHeading.setFont(new Font("Arial", Font.BOLD, 28));
		lblHeading.setForeground(Color.BLACK);
				
		btnStop = new JButton("Stop");
		btnStop.setBounds(150, 10, 110, 50);
		panel4.add(btnStop);
		panel4.setOpaque(false);
		
		btnNext= new JButton("Next");
		btnNext.setBounds(350, 10, 110, 50);
		panel4.add(btnNext);
		panel4.setOpaque(false);
				
		mainFrame.setContentPane(new JLabel(new ImageIcon("Images/BG6.jpg")));
		mainFrame.add(lblHeading);
		mainFrame.add(panel1);
		mainFrame.add(panel2);
		mainFrame.add(panel4);
		
		
		face = new JPanel();
        fdetect = new JPanel();
       
        face.setBounds(10, 40, 300, 250);
        face.setBorder(BorderFactory.createLineBorder(Color.red));
        
        fdetect.setBounds(330, 40, 300, 250);
        fdetect.setBorder(BorderFactory.createLineBorder(Color.blue));
        
        try
        {
        Boolean isAlive=true;
		if(isAlive)
        {
        CaptureThread myThread = new CaptureThread();
    	//frame.dispose();
        thread = new Thread(myThread);
        thread.setDaemon(true);
        myThread.runnable = true;
        thread.start();
        
        
        }
		 panel3.repaint();
        } catch (Exception e) {
			e.printStackTrace();
		}
		
        btnStop.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				
				System.out.println("Stopped");
	        	
				thread.stop();
					 //System.out.println(isAlive);
			   
											
			}		
	 
		});

        btnNext.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				
				System.out.println("Next pressed");
	        	
				OnlineProcessing op=new OnlineProcessing();
				op.setVisible(true);	
				mainFrame.dispose();
			}		
	 
		});

        
        
        panel2.add(face);
        panel2.add(fdetect);
             
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
    }
    
      
	/**************** Main Program Starts here *************************/
    public static void main(String[] args) {
		

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new CaptureGUI();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
