package com.project.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class Home 
{
	JFrame frame;
	
	private JPanel panel1,panel2,panel3;
	private JLabel label1;
	private JButton homeButton,loginButton,RegisterButton;
	
	
   public Home()
   {
 	    frame = new JFrame("HomePage");
	    frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		panel1=new JPanel();
		panel1.setBounds(20,50,750,100);
		
		panel2=new JPanel();
		panel2.setBounds(10, 170, 1275, 60);
		panel2.setLayout(null);
		
		label1=new JLabel("EMOTION BASED MUSIC PLAYER");
		label1.setBounds(150, 0, 160, 25);
		label1.setFont(new Font("Arial", Font.BOLD, 28));
		label1.setForeground(Color.BLACK);
		panel1.add(label1);
		panel1.setOpaque(false);
			
		homeButton = new JButton("Refresh");
		homeButton.setBounds(200, 10, 110, 50);
		homeButton.setForeground(Color.black);
		panel2.add(homeButton);
		panel2.setOpaque(false);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(350, 10, 110, 50);
		loginButton.setForeground(Color.black);
		panel2.add(loginButton);
		panel2.setOpaque(false);
		
			
		RegisterButton = new JButton("Register");
		RegisterButton.setBounds(500, 10, 110, 50);
		RegisterButton.setForeground(Color.black);
		panel2.add(RegisterButton);
		panel2.setOpaque(false);
		
		homeButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)	
		  {
			  try {
					UIManager
							.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			 Home home=new Home();
			 //login.setVisible(true);
			 frame.dispose();
		  }
		  
		}
		);
		 
	
		
		loginButton.addActionListener(new ActionListener()
			{
			  public void actionPerformed(ActionEvent e)	
			  {
				  try {
						UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				LoginView l=new LoginView();
				frame.dispose();
			  }
			  
			}
			);
		
         RegisterButton.addActionListener(new ActionListener()
	       {
	         public void actionPerformed(ActionEvent e)	
	          {
	        	 try {
						UIManager
								.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
		        Register r =new Register();
		        frame.dispose();
	          }
	  
	       }
		);
            
	        frame.setContentPane(new JLabel(new ImageIcon("Images/BG6.jpg")));
	 	    frame.add(panel1);
	 	    frame.add(panel2);
	 	    //frame.add(panel3);
	 	    frame.setLocationRelativeTo(null);
	 		frame.setVisible(true);
     }
  
       public static void main(String args[])
        {
    	   try 
    	   {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
           } 
    	   catch (Exception e1) {
	            e1.printStackTrace();
}
	       new Home();
        }
}
