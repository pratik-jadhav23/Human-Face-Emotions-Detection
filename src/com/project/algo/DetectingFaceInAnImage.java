package com.project.algo;

import org.opencv.core.Core;  
import org.opencv.core.Mat; 
import org.opencv.core.MatOfRect; 
import org.opencv.core.Point; 
import org.opencv.core.Rect; 
import org.opencv.core.Scalar; 
import org.opencv.highgui.Highgui; 
import org.opencv.core.Core;
import org.opencv.imgproc.Imgproc; 
import org.opencv.objdetect.CascadeClassifier;  
  
public class DetectingFaceInAnImage 
{
	
	
	public void detectFaceInAnImage(String input, String facepath,String croppath, String eyepath)
	{
		 //Loading the OpenCV core library  
	      System.loadLibrary( Core.NATIVE_LIBRARY_NAME ); 

	      //Reading the Image from the file and storing it in to a Matrix object 
	      // "C:/Users/admin/Pictures/Face Images/face1.jpg";
	      String file =input;
	      Mat src = Highgui.imread(file);  
	      
	      //Instantiating the CascadeClassifier 
	      String xmlFile = "lbpcascade_frontalface.xml"; 
	      CascadeClassifier classifier = new CascadeClassifier(xmlFile); 
	      
	      CascadeClassifier cascadeEyeClassifier = new CascadeClassifier(
					"haarcascade_eye_tree_eyeglasses.xml");
	      
	      /*CascadeClassifier cascadeEyeClassifier = new CascadeClassifier(
					"haarcascade_eye.xml");*/
	      
	      System.out.println("Start Process.....");

	      //Detecting the face in the snap 
	      MatOfRect faceDetections = new MatOfRect(); 
	      classifier.detectMultiScale(src, faceDetections);  
	      System.out.println(String.format("Detected %s faces", 
	         faceDetections.toArray().length));  
	      
	      Rect rect_Crop=null;
	      //Drawing boxes  
	      for (Rect rect : faceDetections.toArray()) { 
	    	//name
			Core.putText(src, "FACE", new Point(rect.x,rect.y-5), 1, 2, new Scalar(0,0,255));
			//Detect Face
	    	Core.rectangle(src,       //where to draw the box 
	         new Point(rect.x, rect.y),   //bottom left 
	         new Point(rect.x + rect.width, rect.y + rect.height),  //top right  
	         new Scalar(0, 0, 255), 
	         3);    //RGB color 
	    	  
	    	  rect_Crop = new Rect(rect.x, rect.y, rect.width, rect.height);
	      } 
	      //Writing the image     
	      Highgui.imwrite(facepath, src); 
	      
	      System.out.println("Face Detection Processed");
	      
	      Mat image_roi = new Mat(src,rect_Crop);
	      Highgui.imwrite(croppath,image_roi);
	      System.out.println("Image Crop Successed!!!");
	      
	      //Eye Detection
	      MatOfRect eyes = new MatOfRect();
			cascadeEyeClassifier.detectMultiScale(src, eyes);
			for (Rect rect : eyes.toArray()) {
				//name
				Core.putText(src, "EYE", new Point(rect.x,rect.y-5), 1, 2, new Scalar(0,0,255));				
				//Eye Detection
				Core.rectangle(src, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
						new Scalar(200, 200, 100),2);
			}
			
			Highgui.imwrite(eyepath, src);
			System.out.println("Eye Detection Process Successed!!!");
	}
	public void detectFace(String input, String output)
	{
		 //Loading the OpenCV core library  
	      System.loadLibrary( Core.NATIVE_LIBRARY_NAME ); 

	      //Reading the Image from the file and storing it in to a Matrix object 
	      // "C:/Users/admin/Pictures/Face Images/face1.jpg";
	      String file =input;
	      Mat src = Highgui.imread(file);  
	      
	      //Instantiating the CascadeClassifier 
	      String xmlFile = "lbpcascade_frontalface.xml"; 
	      CascadeClassifier classifier = new CascadeClassifier(xmlFile); 
	      
	      System.out.println("Start Process.....");

	      //Detecting the face in the snap 
	      MatOfRect faceDetections = new MatOfRect(); 
	      classifier.detectMultiScale(src, faceDetections);  
	      System.out.println(String.format("Detected %s faces", 
	         faceDetections.toArray().length));  
	      
	      Rect rect_Crop=null;
	      //Drawing boxes  
	      for (Rect rect : faceDetections.toArray()) { 
	    	  Core.rectangle(src,       //where to draw the box 
	         new Point(rect.x, rect.y),   //bottom left 
	         new Point(rect.x + rect.width, rect.y + rect.height),  //top right  
	         new Scalar(0, 0, 255), 
	         3);    //RGB color 
	    	  
	    	  rect_Crop = new Rect(rect.x, rect.y, rect.width, rect.height);
	      } 
	      //Writing the image     
	      
	      System.out.println("Detection Processed");
	      
	      Mat image_roi = new Mat(src,rect_Crop);
	      Highgui.imwrite(output,image_roi);
	      System.out.println("Image Crop Success");
		
	}
      
}