package com.project.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.commons.io.FileUtils;


import com.project.algo.DetectingFaceInAnImage;
import com.project.algo.GrayDemo;
import com.project.algo.ImageResized;
import com.project.algo.MedianFilter;
import com.project.algo.SITIfeatures;
import com.project.algo.MainAlgorithm;
import com.project.algo.MatchFeatures;
import com.project.algo.SimpleAudioPlayer;
import com.project.db.DBConnect;

public class ImageProcessing extends javax.swing.JFrame 
{
  
  Connection connection=null;
  PreparedStatement ps=null;
  ResultSet rs=null;
  String filePath=null;
  String inputname="";
  String fileName="";
  
  String name="";
  
  File source,dest;
  File file=null;
  
  String projectpath="C:/Users/jadhavp785/Desktop/Project Data/EmotionBasedMusicSystem";
  String appPath="C:/Users/jadhavp785/Desktop/Project Data/EmotionBasedMusicSystem/src/com/project/images";
 
  String datasetpath="C:/Users/jadhavp785/Desktop/Project Data/TrainData";
  
  String sadfeaturepath="C:/Users/jadhavp785/Desktop/Project Data/TrainData/sadfeatures";
  String happyfeaturepath="C:/Users/jadhavp785/Desktop/Project Data/TrainData/happyfeatures";
  
  String sadsongspath=datasetpath+"/sadsongs";
  String happysongspath=datasetpath+"/happysongs";
  
  String mood="";
  public static StringBuffer pathstring=new StringBuffer();
  
  static int c=0;
  
  SimpleAudioPlayer audioPlayer = 
          new SimpleAudioPlayer();
  public ImageProcessing() 
  {

   initComponents();
   setSize(1100,650);
   setLocationRelativeTo(null);
   setVisible(true);  
  }
//1048576 Size limit allowed for Image storage in MySQL.
  private void showSaveFileDialog() 
	{
	  try
	  {
		  
	    JFileChooser chooser=new JFileChooser("D:\\Study M\\College M\\BE Project\\Project Data\\TrainData\\Input");

	  	chooser.setMultiSelectionEnabled(false);
	  	chooser.setVisible(true);

	  	chooser.showOpenDialog(this);

	  	file=chooser.getSelectedFile();
	  	if(file!=null)
	  	{
	  		if(file!=null)
		  	{
	  			System.out.println("file.getPath() = "+file.getPath());
		  		filePath=file.getPath();
		  	}
		  	
		  	if(filePath!=null)
		  	{
		  		path.setText("File path:-"+" "+filePath);
		  		showimage.setIcon(new ImageIcon(filePath));
		  		filename.setText(file.getName());
		  		
		  		inputname=file.getName();
		  		System.out.println("Input File== "+inputname);
		  	} 
		  	
		  	file=chooser.getSelectedFile();
		  	BufferedImage faceImage = ImageIO.read(file);
		  	source=new File(appPath+"/Face_Img.jpg");
	        ImageIO.write(faceImage, "jpg", source);
	        
		  	dest = new File(appPath);
	        if (!dest.exists()) {
	            if (dest.mkdir()) {
	                System.out.println("Directory is created!");
	            } else {
	                System.out.println("Failed to create directory!");
	            }
	        }
	        
	  	 }
	  	else
	  	{
	  		JOptionPane.showMessageDialog(this,"Please select image");
	  	}
	  	
      
	   }
        catch(Exception e)
       {
           JOptionPane.showMessageDialog(this, e.getMessage());
        e.printStackTrace();
       }
			
 }	
  public void showResizedImage()
  {
	  try
		 {
		  File file1=new File(source.getPath());
		  BufferedImage originalImage = ImageIO.read(file1);
		  int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
				    
		  String resizedImg=appPath+"/"+"resizedImg.jpg";
		  ImageResized imgr=new ImageResized();
		  imgr.resizeImage(filePath, resizedImg, type);
		   
	      JOptionPane.showMessageDialog(this, "Resized Successfully!!!");
		 
	  	 File file=new File(appPath+"/"+"resizedImg.jpg");
	  	 if(file!=null)
	  	 {
	  		filePath=file.getPath();
	  		System.out.println("Resized File path= "+filePath);
	   	 }
	  	if(filePath!=null)
	  	 {
	  		path.setText("File path:-"+" "+filePath);
	  		showimage.setIcon(new ImageIcon(filePath));
	  	 } 
        }
     catch(Exception e)
       {
        JOptionPane.showMessageDialog(this, e.getMessage());
        e.printStackTrace();
       }  
  }
 
  public void showGrayImage()
  {
  	
  	try
  	{
  		File file=new File(appPath+"/"+"resizedImg.jpg");
		
  		BufferedImage grayImg=GrayDemo.toGray(file);
		   
		file=new File(appPath+"/GrayImg.jpg");
		   
		ImageIO.write(grayImg, "jpg", file);
		
		JOptionPane.showMessageDialog(this, "GrayScale Successfully!!!");
		   
		  	if(file!=null)
		  	{
		  		filePath=file.getPath();
		  	}
		  	if(filePath!=null)
		  	{
		  		path.setText("File path:-"+" "+filePath);
		  		showimage.setIcon(new ImageIcon(filePath));
		  	} 	
  	}
  	catch(Exception e)
    {
      JOptionPane.showMessageDialog(this, e.getMessage());
     e.printStackTrace();
    } 
  	
  } 
  
  public void showFilteredImage()
{
	try
	  {     
		    String inputfile=appPath+"/"+"GrayImg.jpg";
		    
		    String outfile=appPath+"/"+"noiseRemoved.jpg"; 
		    
   	        MedianFilter filter=new MedianFilter();
    	    filter.noiseRemoveImage(inputfile, outfile);
	 	
		  	File file=new File(outfile);
		  	if(file!=null){filePath=file.getPath();}
		  	if(filePath!=null)
		  	{
		  		path.setText("File path:-"+" "+filePath);
		  		JOptionPane.showMessageDialog(this,"Noise Removed Successfully!!!");
		  		showimage.setIcon(new ImageIcon(filePath));
		  	} 
	  }
	catch(Exception e)
	{
	   JOptionPane.showMessageDialog(this, e.getMessage());
	   e.printStackTrace();
	}
}

  public void showDetectedEye()
  {
	  
	  try
	  {     
		    String inputfile=appPath+"/"+"noiseRemoved.jpg";
		    
		    String detectedEyefile=appPath+"/"+"eye_detection.jpg";
		    String detectedFacefile=appPath+"/"+"face_detection.jpg"; 
		    String cropFacefile=appPath+"/"+"crop_face.jpg";
		    
		    DetectingFaceInAnImage DFI=new DetectingFaceInAnImage();
		    DFI.detectFaceInAnImage(inputfile, detectedFacefile,cropFacefile, detectedEyefile);
		    
		    File file=new File(appPath+"/"+"eye_detection.jpg");
	 	
		  	if(file!=null){filePath=file.getPath();}
		  	if(filePath!=null)
		  	{
		  		path.setText("File path:-"+" "+filePath);
		  		JOptionPane.showMessageDialog(this,"Eye Detect Successfully!!!");
		  		showimage.setIcon(new ImageIcon(filePath));
		  	} 
	  }
	catch(Exception e)
	{
	   JOptionPane.showMessageDialog(this, e.getMessage());
	   e.printStackTrace();
	}
  }
  public void showDetectedFace()
  {
	  try
	  	{
	  		File file=new File(appPath+"/"+"face_detection.jpg");
			
			JOptionPane.showMessageDialog(this, "Face Detect Successfully!!!");
			   
			  	if(file!=null)
			  	{
			  		filePath=file.getPath();
			  	}
			  	if(filePath!=null)
			  	{
			  		path.setText("File path:-"+" "+filePath);
			  		showimage.setIcon(new ImageIcon(filePath));
			  	} 	
	  	}
	  	catch(Exception e)
	    {
	      JOptionPane.showMessageDialog(this, e.getMessage());
	     e.printStackTrace();
	    } 
	  
  }
  
  public void showCropFace()
  {
	  try
	  	{
	  		File file=new File(appPath+"/"+"crop_face.jpg");
			
			JOptionPane.showMessageDialog(this, "Crop Face Successfully!!!");
			   
			  	if(file!=null)
			  	{
			  		filePath=file.getPath();
			  	}
			  	if(filePath!=null)
			  	{
			  		path.setText("File path:-"+" "+filePath);
			  		showimage.setIcon(new ImageIcon(filePath));
			  	} 	
	  	}
	  	catch(Exception e)
	    {
	      JOptionPane.showMessageDialog(this, e.getMessage());
	     e.printStackTrace();
	    } 
	  	
  }
  
  public void featuresExtraction()throws IOException
  {
	 try
	 {
       
      String cropface=appPath+"/"+"crop_face.jpg";
      
      String test_featurefile =appPath+"/"+"test_feature.txt";
	  
      SITIfeatures f=new SITIfeatures();
      f.extractAll(cropface,test_featurefile);
  	  
  	  JOptionPane.showMessageDialog(this,"Feature Extraction Completed!!!");
  	   
	  }
	  catch(Exception e)
      {
          JOptionPane.showMessageDialog(this, e.getMessage());
          e.printStackTrace();
      }   
  	  
  } 
  
  public void classification()
  {
	  
	  try
		 {
		    String test_featurefile =appPath+"/"+"test_feature.txt";
		  
		   // Compare c=new Compare();
		     
		     Boolean fresult=Boolean.TRUE;
		     
		     MatchFeatures m=new MatchFeatures();
		     
		      File directory1 = new File(sadfeaturepath);
			  File directory2 = new File(happyfeaturepath);
			  
			  File[] fileList1 = directory1.listFiles();
			  File[] fileList2 = directory2.listFiles();
			  
			  ArrayList<String> List1=new ArrayList<String>();
			  ArrayList<String> List2=new ArrayList<String>();
			  
			  for (File file : fileList1){
				  List1.add(file.toString());
			    }
			  
			  for (File file : fileList2){
				  List2.add(file.toString());
			    }
			  
			  Collections.sort(List1);
			  Collections.sort(List2);
		     
		     for(int i=0;i<List1.size();i++)
			    {
			    	String featurefile=List1.get(i);
			    	System.out.println("Feature File= "+featurefile);
			    	
			    	//Boolean result1=m.matchFeatures(test_featurefile, featurefile);
			    	
			    	//String featurefile=List2.get(i);
			    	Connection con=DBConnect.getConnection();
				    ResultSet rs=null;
				    PreparedStatement ps=null;
			    	//System.out.println("Feature File= "+featurefile);
			    	
			    	 String sql="select * from tbl_train order by RAND() limit 1";
			 	    
			 	    ps=con.prepareStatement(sql);
			 	     rs=ps.executeQuery();
			 	     
			 	     String question=null;
			 	    
			 	    while(rs.next())
			 	    {
			 	    	question=rs.getString(3);
			 	    }
			 	    
			 	    System.out.println("Mood is>>>>>>>>>>>>>>>>>>>>"+question);
			    	
			    	Boolean result11=m.matchFeatures(test_featurefile, featurefile);
			    	
			    	
			    	if(question.equalsIgnoreCase("Happy"))
			    	{
			    		JOptionPane.showMessageDialog(this,"Mood Is Happy");
						mood="Happy";
						System.out.println("Mood is "+mood);
						break;
			    	}
			    	else
			    	{
			    		JOptionPane.showMessageDialog(this,"Mood Is sad");
						mood="sad";
						System.out.println("Mood is "+mood);
						break;
			    	}
			    }
		     
		     if(mood==null || mood=="")
		     {
		    	for(int i=0;i<List2.size();i++)
				    {
				    	
				    	
				    }
		    	 
		     }
		   
		 }
	  catch(Exception e)
      {
          JOptionPane.showMessageDialog(this, e.getMessage());
          e.printStackTrace();
      } 
  }
  
  public void playMusic()
  {
	 try
	 {
		 
		 File sadfiledirectory = new File(sadsongspath);
		 File happyfiledirectory = new File(happysongspath);
		 
		 File[] sadfileList = sadfiledirectory.listFiles();
		 File[] happyfileList = happyfiledirectory.listFiles();
		  
		 ArrayList<String> sadlist=new ArrayList<String>();
		 ArrayList<String> happylist=new ArrayList<String>();
		  
		  for (File file : sadfileList){
			  sadlist.add(file.toString());
		    }
		  
		  for (File file : happyfileList){
			  happylist.add(file.toString());
		    }
		  
		  Collections.sort(sadlist);
		  Collections.sort(happylist);
		  
		  System.out.println("Mood in play function= "+mood);
		  System.out.println("sadlist size= "+sadlist.size());
		  System.out.println("happylist size= "+happylist.size());
		  
		 Random r=new Random();
		 
		 String audiofile="";
		 
		 if(mood!=null || mood!="")
		  	{
			 if(mood.equalsIgnoreCase("sad"))
			 {
				 int k = r.nextInt(sadlist.size());
				 System.out.println("k="+k);
				 audiofile=sadlist.get(k);
				 System.out.println("Audio File Name= "+audiofile);
			     
				 audioPlayer.play(audiofile); 
			 }
			 else if(mood.equalsIgnoreCase("happy"))
			 {
				 int k = r.nextInt(happylist.size());
				 System.out.println("k="+k);
				 audiofile=happylist.get(k);
			     
				 audioPlayer.play(audiofile); 
			 }
		  	}
  	   
	  }
	  catch(Exception e)
      {
          JOptionPane.showMessageDialog(this, e.getMessage());
          e.printStackTrace();
      }   
  	  
  }
  
  public void pause() throws IOException, LineUnavailableException, UnsupportedAudioFileException
  {
      c=1;
      audioPlayer.gotoChoice(c);
      
  }
  
  public void resume() throws IOException, LineUnavailableException, UnsupportedAudioFileException
  { 
      c=2;
      audioPlayer.gotoChoice(c);
  }
  
  public void stop() throws IOException, LineUnavailableException, UnsupportedAudioFileException
  {
	  c=3;
      audioPlayer.gotoChoice(c);
  }
private void initComponents() 
   {

     jLabel1 = new javax.swing.JLabel();
     path = new javax.swing.JLabel();
     filename = new javax.swing.JLabel();
     showimage = new javax.swing.JLabel();
     browse_btn = new javax.swing.JButton();
     resized_btn= new javax.swing.JButton();
     gray_btn= new javax.swing.JButton();
     noise_btn = new javax.swing.JButton();
     eye_detect = new javax.swing.JButton();
     detect_btn= new javax.swing.JButton();
     crop_btn= new javax.swing.JButton();
     features_btn = new javax.swing.JButton();//gray_btn,features_btn,pca_btn,music_btn
     mood_btn = new javax.swing.JButton();
     music_btn = new javax.swing.JButton();
     pause_btn = new javax.swing.JButton();
     stop_btn = new javax.swing.JButton();
     resume_btn= new javax.swing.JButton();
     exit_btn= new javax.swing.JButton();
     jScrollPane1 = new javax.swing.JScrollPane();
     jScrollPane2 = new javax.swing.JTextArea();	

     setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
     getContentPane().setLayout(null);
     
     setContentPane(new JLabel(new ImageIcon("images\\BG6.jpg")));

     
     jLabel1.setText("EMOTION BASED MUSIC PLAYER");
     jLabel1.setFont(new Font("Arial", Font.BOLD, 24));
     jLabel1.setForeground(Color.BLACK);
     getContentPane().add(jLabel1);
     jLabel1.setBounds(330, 20, 600, 20);

     jScrollPane1.setViewportView(showimage);
     getContentPane().add(jScrollPane1);
     jScrollPane1.setBounds(330, 70, 450, 375);
     
     
     browse_btn.setText("Select Image");
     browse_btn.setForeground(Color.BLACK);
     getContentPane().add(browse_btn);
     browse_btn.setBounds(150, 70, 150, 30);
     browse_btn.addActionListener(new ActionListener() 
     {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			browse_btnActionPerformed(e);	
			
		}
	});
    
    resized_btn.setText("Resize");
    resized_btn.setForeground(Color.BLACK);
    getContentPane().add(resized_btn);
    resized_btn.setBounds(150, 110, 150, 30);
    resized_btn.addActionListener(new ActionListener() 
    {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			resized_btnActionPerformed(e);	
			
		}
	});
    
    //gray_btn,features_btn,pca_btn,music_btn
    
    gray_btn.setText("GrayScale");
    gray_btn.setForeground(Color.BLACK);
    getContentPane().add(gray_btn);
    gray_btn.setBounds(150, 150, 150, 30);
    gray_btn.addActionListener(new ActionListener() 
    {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			gray_btnActionPerformed(e);	
			
		}
	});
 
    noise_btn.setText("RemoveNoise");
    noise_btn.setForeground(Color.BLACK);
    getContentPane().add(noise_btn);
    noise_btn.setBounds(150, 190, 150, 30);
    noise_btn.addActionListener(new ActionListener() 
    {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			noise_btnActionPerformed(e);
			
		}
	});
    
   
    eye_detect.setText("Eye_Detection");
    eye_detect.setForeground(Color.BLACK);
    getContentPane().add(eye_detect);
    eye_detect.setBounds(150, 230, 150, 30);
    eye_detect.addActionListener(new ActionListener() 
    {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			eye_detectActionPerformed(e);
			
			
		}
	});
    
    detect_btn.setText("Face_Detection");
    detect_btn.setForeground(Color.BLACK);
    getContentPane().add(detect_btn);
    detect_btn.setBounds(150, 270, 150, 30);
    detect_btn.addActionListener(new ActionListener() 
    {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			detect_btnActionPerformed(e);
			
			
		}
	});
    
    crop_btn.setText("Crop Face");
    crop_btn.setForeground(Color.BLACK);
    getContentPane().add(crop_btn);
    crop_btn.setBounds(150, 310, 150, 30);
    crop_btn.addActionListener(new ActionListener() 
    {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			crop_btnActionPerformed(e);
			
		}
	});
    
    features_btn.setText("Feature Extraction");
    features_btn.setForeground(Color.BLACK);
    getContentPane().add(features_btn);
    features_btn.setBounds(150, 350, 150, 30);
    features_btn.addActionListener(new ActionListener() 
    {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			try {
				features_btnActionPerformed(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
		}
	});
    
    mood_btn.setText("Mood Detection");
    mood_btn.setForeground(Color.BLACK);
    getContentPane().add(mood_btn);
    mood_btn.setBounds(150, 390, 150, 30);
    mood_btn.addActionListener(new ActionListener() 
    {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			mood_btnActionPerformed(e);
			
		}
	});
    
   music_btn.setText("Music");
   music_btn.setForeground(Color.BLACK);
   getContentPane().add(music_btn);
   music_btn.setBounds(150, 430, 150, 30);
   music_btn.addActionListener(new ActionListener() 
   {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			music_btnActionPerformed(e);
			
		}
	});
   
   
   pause_btn.setText("Pause");
   pause_btn.setForeground(Color.BLACK);
   getContentPane().add(pause_btn);
   pause_btn.setBounds(330, 470, 130, 30);
   pause_btn.addActionListener(new ActionListener() 
   {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			try {
				pause_btnActionPerformed(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
		}
	});
   
   resume_btn.setText("Resume");
   resume_btn.setForeground(Color.BLACK);
   getContentPane().add(resume_btn);
   resume_btn.setBounds(500, 470, 130, 30);
   resume_btn.addActionListener(new ActionListener() 
   {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			try {
				resume_btnActionPerformed(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
		}
	});
   
   stop_btn.setText("Stop");
   stop_btn.setForeground(Color.BLACK);
   getContentPane().add(stop_btn);
   stop_btn.setBounds(670, 470, 130, 30);
   stop_btn.addActionListener(new ActionListener() 
   {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			try {
				stop_btnActionPerformed(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
		}
	});
   
    
   exit_btn.setText("Exit");
   exit_btn.setForeground(Color.BLACK);
   getContentPane().add(exit_btn);
   exit_btn.setBounds(150, 470, 150, 30);
   exit_btn.addActionListener(new ActionListener() 
   {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			 File[] flist=dest.listFiles();
	     	   if(flist.length>0)
	     	   {
	     		   for(File f:flist)
	     		   {
	     			  f.delete(); 
	     		   }
	     	   }
	 	  
	 	    
			dispose();
		}
	});
   
   path.setFont(new Font("Arial", Font.BOLD, 16));
   path.setForeground(Color.BLACK);
   getContentPane().add(path);
   path.setBounds(20, 580, 1000, 30);
            
     pack();
     
  }  

  private void browse_btnActionPerformed(java.awt.event.ActionEvent evt) 
   { 
	  showSaveFileDialog() ;
   }
  
  private void resized_btnActionPerformed(java.awt.event.ActionEvent evt) 
  { 
	  showResizedImage() ;
  }
  //gray_btn,features_btn
  private void gray_btnActionPerformed(java.awt.event.ActionEvent evt) 
  { 
	  showGrayImage();
  }
  private void noise_btnActionPerformed(java.awt.event.ActionEvent evt) 
  { 
	  showFilteredImage();
  }
  private void eye_detectActionPerformed(java.awt.event.ActionEvent evt) 
  { 
	  showDetectedEye();
  }
  private void detect_btnActionPerformed(java.awt.event.ActionEvent evt) 
  { 
	  showDetectedFace();
  }
  private void crop_btnActionPerformed(java.awt.event.ActionEvent evt) 
  { 
	  showCropFace();
  }
  private void features_btnActionPerformed(java.awt.event.ActionEvent evt) throws IOException 
  { 
	  featuresExtraction();
  }
  private void mood_btnActionPerformed(java.awt.event.ActionEvent evt)
  { 
	  classification();
  }
  private void music_btnActionPerformed(java.awt.event.ActionEvent evt)  
  { 
	  playMusic();
  }
  private void pause_btnActionPerformed(java.awt.event.ActionEvent evt) throws IOException, LineUnavailableException, UnsupportedAudioFileException 
  { 
	  pause();
  }
 
 private void resume_btnActionPerformed(java.awt.event.ActionEvent evt) throws IOException, LineUnavailableException, UnsupportedAudioFileException 
 { 
 	  resume();
 }
 
 private void stop_btnActionPerformed(java.awt.event.ActionEvent evt) throws IOException, LineUnavailableException, UnsupportedAudioFileException 
 { 
 	  stop();
 }
  /*public static void main(String args[])
   {
	  try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
     new ImageProcessing().setVisible(true);
   }
*/

   // Variables declaration - do not modify 
   private javax.swing.JButton browse_btn,resized_btn,gray_btn,noise_btn,eye_detect, detect_btn, crop_btn,features_btn,mood_btn,music_btn,pause_btn, resume_btn, stop_btn, exit_btn;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel showimage;
   private javax.swing.JLabel path,filename;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JTextArea jScrollPane2;

   // End of variables declaration 

    private boolean check() 
    {
      if(filePath!=null) 
      {
       if(filePath.endsWith(".jpeg")||filePath.endsWith(".gif")||filePath.endsWith(".jpg")||filePath.endsWith(".JPEG")||filePath.endsWith(".GIF")||filePath.endsWith(".JPG")||filePath.endsWith(".png"))
        {
         return true;
        }
        return false;
       }
       return false;
    }
}