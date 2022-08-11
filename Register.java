package com.project.view;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.commons.io.FileUtils;

import com.project.bean.UserBean;
import com.project.dao.UserDao;
import com.project.service.EmailDemo;
import com.project.validation.EmailAndMobile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
public class Register
{
	private JFrame frame;
	private JLabel headerLabel,userLabel,addressLabel,emailLabel,mobnoLabel,RegisterLabel;
	private JLabel passwordLabel;
	private JTextField userText,addressText,emailText,mobnoText;
	
	private JPasswordField passwordText;
	private JButton registerButton,clearButton, loginButton, backButton;
	
    private InputStream is;
    private JPanel panel1,panel2;
    Boolean resultStatus=Boolean.FALSE;
	

	public Register()
	 {
		frame = new JFrame("REGISTRATION FORM");
		frame.setSize(800,600);
		//frame.setLocation(250,200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel1=new JPanel();
		panel1.setBounds(20,50,750,80);
		
		panel2 = new JPanel();
		panel2.setBounds(250,150,300,300);
		
		
		panel1.setOpaque(false);
		panel2.setOpaque(false);
		frame.setLayout(null);
		frame.setContentPane(new JLabel(new ImageIcon("images\\BG6.jpg")));
		
		panel2.setLayout(null);
		
		headerLabel=new JLabel("EMOTION BASED MUSIC PLAYER");
		headerLabel.setBounds(110, 0, 160, 25);
		headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
		headerLabel.setForeground(Color.BLACK);
		panel1.add(headerLabel);
		
		RegisterLabel=new JLabel("REGISTRATION");
		RegisterLabel.setBounds(90, 0, 200, 20);
		RegisterLabel.setFont(new Font("Arial", Font.BOLD, 22));
		panel2.add(RegisterLabel);

		userLabel = new JLabel("Username");
		userLabel.setBounds(10, 30, 80, 25);
		panel2.add(userLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 30, 160, 25);
		panel2.add(userText);

		addressLabel = new JLabel("Address");
		addressLabel.setBounds(10, 70, 80, 25);
		panel2.add(addressLabel);

		addressText = new JTextField(20);
		addressText.setBounds(100, 70, 160, 25);
		panel2.add(addressText);
		
		emailLabel = new JLabel("Email");
		emailLabel.setBounds(10, 110, 80, 25);
		panel2.add(emailLabel);

		emailText = new JTextField(20);
		emailText.setBounds(100, 110, 160, 25);
		panel2.add(emailText);
		
		mobnoLabel = new JLabel("Mobno");
		mobnoLabel.setBounds(10, 150, 80, 25);
		panel2.add(mobnoLabel);

		mobnoText = new JTextField(20);
		mobnoText.setBounds(100, 150, 160, 25);
		panel2.add(mobnoText);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 190, 80, 25);
		panel2.add(passwordLabel);

		passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 190, 160, 25);
		panel2.add(passwordText);
		
		registerButton = new JButton("Register");
		registerButton.setBounds(10, 230, 100, 25);
		registerButton.setForeground(Color.BLACK);
		panel2.add(registerButton);
		
		
		clearButton = new JButton("Clear");
		clearButton.setBounds(180, 230, 80, 25);
		clearButton.setForeground(Color.BLACK);
		panel2.add(clearButton);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(10, 270, 100, 25);
		loginButton.setForeground(Color.BLACK);
		panel2.add(loginButton);
		
		backButton= new JButton("Back");
		backButton.setBounds(180, 270, 80, 25);
		backButton.setForeground(Color.BLACK);
		panel2.add(backButton);
		
		registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	registerButtonActionPerformed(evt);
            }
        });
		
		
		clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	clearButtonActionPerformed(evt);
            }
        });
		
		loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	loginButtonActionPerformed(evt);
            }
        });
		
		passwordText.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	passwordTextActionPerformed(evt);
	            }
	        });
		
		backButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	
	        	try {
				    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			        } catch (Exception e1)
			         {
				     e1.printStackTrace();
		           	}
	        	new Home();
	        	frame.dispose();
	        }
	    });
		
		frame.add(panel1);
		frame.add(panel2);
		frame.setVisible(true);
	 }
		
	
	
	    
		private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) 
		{
			String uname=userText.getText();
	        boolean validName =EmailAndMobile.isValidName(uname);
	        
	        String address=addressText.getText();
	       
	        String email=emailText.getText();
	        boolean validEmail =EmailAndMobile.isValidEmailAddress(email);
	        
	        String mobNo=mobnoText.getText();
	        boolean validMobilNo =EmailAndMobile.isValidMobilNumber(mobNo);
	        
	        String password=passwordText.getText();
	        boolean validpass= EmailAndMobile.isValidPassword(password);
		    
		   /* if(passwordText.getText().length()==0)
	         {
	            
	            JOptionPane.showMessageDialog(frame,"Please Enter Password");
	             
	         }
		    else if(validpass==false) {
	        	JOptionPane.showMessageDialog(frame,"Please Enter Valid Password ['a','A','0-9','! @ # & ( )','Min 8 characters']");
	        }*/
	        
	        if(userText.getText().length()==0)
	        {
	            JOptionPane.showMessageDialog(frame,"Please Enter User Name");
	        }
	        else if(validName==false)
	        {
	                    
	            
	            JOptionPane.showMessageDialog(frame,"Please Enter Valid Name"); 
	                          
	            
	        }
	       
	        else if(addressText.getText().length()==0)
	        {
	            
	            JOptionPane.showMessageDialog(frame,"Please Enter Address"); 
	        
	        }
	       
	        else if(emailText.getText().length()==0)
	        {
	            JOptionPane.showMessageDialog(frame,"Please Enter Email");
	            
	        }
	        else if(validEmail==false)
	        {
	                    
	            
	            JOptionPane.showMessageDialog(frame,"Please Enter Valid Email Id"); 
	                          
	            
	        }
	        else if(mobnoText.getText().length()==0)
	        {
	            JOptionPane.showMessageDialog(frame,"Please Enter Mobile");
	            
	        }
	        else if(validMobilNo==false)
	        {
	                    
	            
	            JOptionPane.showMessageDialog(frame,"Please Enter Valid Mobile Number"); 
	                          
	            
	        }
	        else if(passwordText.getText().length()==0)
	        {
	           
	            JOptionPane.showMessageDialog(frame,"Please Enter Password");
	            
	        }
	        
	        else if(validpass==false) {
	        	JOptionPane.showMessageDialog(frame,"Please Enter Valid Password ['a','A','0-9','!/@/#/&/(/)','Min 8 characters']");
	        }
	                        
	        else
	        {
	        	 UserBean bean=new UserBean();
		         bean.setUname(uname);
		         bean.setAddress(address);
		         bean.setEmail(email);
		         bean.setMobNo(mobNo);
		         String encodedString;
					try {
						password = Base64.getEncoder().encodeToString(password.getBytes("utf-8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		         bean.setPassword(password);
		        
		         UserDao ud=new UserDao();
		         
		 		 
		        if(ud.userRegistration(bean))
		        {
		        	
		             JOptionPane.showMessageDialog(frame,"User Register Sucessfully !!!");
		         
		         try {
						UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
		          LoginView login=new LoginView();
		          //login.setVisible(true);
		          frame.dispose();
		        }
		        else
		        {
		        	JOptionPane.showMessageDialog(frame,"Email Sending Fail !!!");
		        }
	        
	        }
		}
		
		private void passwordTextActionPerformed(java.awt.event.ActionEvent evt) 
		 {
			//GEN-FIRST:event_jPasswordField1ActionPerformed
		        // TODO add your handling code here:
		 }
	       
		private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) 
		{
	        
			userText.setText("");
	        passwordText.setText("");
	        addressText.setText("");
	        emailText.setText("");
	        mobnoText.setText("");
	      
	     }
		
		 private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) 
		   {
			 try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			    LoginView l=new LoginView();
				frame.dispose();
		     
         }
		 
		 
		
		/* public static void main(String[] args)
			{
				
				new Register();
			}

	*/
}
