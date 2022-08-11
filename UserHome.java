
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


public class UserHome 
{
	JFrame frame;
	
	private JPanel panel1,panel2,panel3;
	private JLabel label1;
	private JButton homeButton,offlineButton,onlineButton, back_btn;
	
	
   public UserHome()
   {
 	    frame = new JFrame("UserHome");
	    frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		panel1=new JPanel();
		panel1.setBounds(20,50,750,80);
		
		panel2=new JPanel();
		panel2.setBounds(20,130,500,500);
		panel2.setLayout(null);
		
		label1=new JLabel("EMOTION BASED MUSIC PLAYER");
		label1.setBounds(10,10,500,40);
		label1.setFont(new Font("Arial", Font.BOLD, 22));
		label1.setForeground(Color.BLACK);
		panel1.add(label1);
		panel1.setOpaque(false);
		
		homeButton = new JButton("Home");
		homeButton.setBounds(30,60,200,40);
		homeButton.setForeground(Color.BLACK);
		//homeButton.setForeground(new java.awt.Color(51, 102, 255));
		panel2.add(homeButton);
		panel2.setOpaque(false);
		
		homeButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)	
		  {
			
			 Home home=new Home();
			 //login.setVisible(true);
			 frame.dispose();
		  }
		  
		}
		);
		
		offlineButton = new JButton("Processing");
		offlineButton.setBounds(30,120,200,40);
		offlineButton.setForeground(Color.BLACK);
			panel2.add(offlineButton);
			panel2.setOpaque(false);
			
			offlineButton.addActionListener(new ActionListener()
	       {
	         public void actionPerformed(ActionEvent e)	
	          {
		
		       ImageProcessing USI=new ImageProcessing();
		        USI.setVisible(true);
		        frame.dispose();
	          }
	  
	       }
		);
		
		
	    frame.setContentPane(new JLabel(new ImageIcon("images\\BG6.jpg")));
	 	frame.add(panel1);
	 	frame.add(panel2);
	 	    
	 	frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
     }
  
       /*public static void main(String args[])
        {
	       new Home();
        }*/
}
