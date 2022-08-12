package com.project.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.core.Core;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;


public class CaptureThread implements Runnable
{
    protected volatile boolean runnable = false;
    public static int flagcount=0;

    String storedpath="D:/Inputs/EmotionTrainData/Captured";

    
    int count = 0;
    VideoCapture webSource = null;
    int x,y,width,height;
    Mat frame;
    MatOfByte mem;

    String File_path="";
    CaptureThread()
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        frame = new Mat();
        mem = new MatOfByte();
        webSource =new VideoCapture(0);
       
    }

    @Override
    public  void run()
    {
        synchronized(this)
        {
            int count = 0;
            while(runnable)
            {
                if(webSource.grab())
                {
		    	try
                        {
                            webSource.retrieve(frame);
			    Highgui.imencode(".bmp", frame, mem);
			    Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));

			    BufferedImage buff = (BufferedImage) im;
			    Graphics g=CaptureGUI.face.getGraphics();

			    if (g.drawImage(buff, 0, 0, CaptureGUI.face.getWidth(), CaptureGUI.face.getHeight() -1 , 0, 0, buff.getWidth(), buff.getHeight(), null))
			    if(runnable == false)
                            {
			    	System.out.println("Going to wait()");
			    	this.wait();
			    }
                            CascadeClassifier faceDetector = new CascadeClassifier("haarcascade_frontalface_alt.xml");
                            
                            File outputfile = new File(storedpath+"/capturedimage.jpg");
                            ImageIO.write(buff, "jpg", outputfile);
                            Mat image = Highgui.imread(storedpath+"/capturedimage.jpg");
                            MatOfRect faceDetections = new MatOfRect();
                            faceDetector.detectMultiScale(image, faceDetections);
 
                            System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
                            System.out.println(("Detected faces= "+faceDetections.toArray().length));
                            
 
                            for (Rect rect : faceDetections.toArray()) {
                                Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                                        new Scalar(0, 255, 0));
                            }

                            Graphics g1 =CaptureGUI.fdetect.getGraphics();
                            MatOfByte bytemat = new MatOfByte();
                            Highgui.imencode(".jpg", image, bytemat);
                            byte[] bytes = bytemat.toArray();
                            InputStream in = new ByteArrayInputStream(bytes);
                            BufferedImage img = ImageIO.read(in);
    			    if (g1.drawImage(img, 0, 0, CaptureGUI.fdetect.getWidth(), CaptureGUI.fdetect.getHeight() -1 , 0, 0, buff.getWidth(), buff.getHeight(), null))
                            if(runnable == false)
                            {
			    	System.out.println("Going to wait()");
			    	this.wait();
			    }  
                            String filename = storedpath+"/facedetect.jpg";
                            Highgui.imwrite(filename, image);
                            // System.out.println(String.format("Writing %s", filename));
                            /*if(faceDetections.toArray().length==1)
                            {
                            Highgui.imwrite(filename, image);
                            flagcount=1;
                            System.out.println("Flagcount= "+flagcount);
                            break;
                            }*/
                            MatOfRect eyeDetections = new MatOfRect();
                            Mat face = image.submat(faceDetections.toArray()[0]);
                            Mat crop = face.submat(4, (2*face.width())/3, 0, face.height());
                            Rect rectcrop=null;
                            for (Rect rect : faceDetections.toArray()) {
                                Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                                        new Scalar(0, 255, 0));
                                rectcrop= new Rect(rect.x,rect.y,rect.width,rect.height);
                            }
                            
                            Mat cropface=new Mat(image,rectcrop);
                            String filename1 = storedpath+"/facecrop.jpg";
                            Highgui.imwrite(filename1, cropface);
                           
                            
                            CascadeClassifier eyeDetector = new CascadeClassifier("haarcascade_eye.xml");
                            eyeDetector.detectMultiScale(crop, eyeDetections); 
                            for (Rect rect : faceDetections.toArray()) {
                                Core.rectangle(crop, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                                        new Scalar(0, 255, 0));
                            }
                            
                            
                            String face_cascade_name = "haarcascade_frontalface_alt.xml";
                            String eyes_cascade_name = "haarcascade_eye_tree_eyeglasses.xml";
                            CascadeClassifier face_cascade = new CascadeClassifier();
                            CascadeClassifier eyes_cascade = new CascadeClassifier();
                            
                            if(!face_cascade.load(face_cascade_name))
                            {
                               System.out.println("Error loading face cascade");
                            }
                            else
                            {
                                System.out.println("Success loading face cascade");
                            }

                            //load the eyes xml cascade
                            
                           // String window_name = "D:/upload/Face detection.jpg";
                            //String window_name = "D:/upload/Capture - Face detection.jpg";
                            Mat frame_gray = new Mat();
                            Imgproc.cvtColor(frame, frame_gray, Imgproc.COLOR_BGRA2GRAY);
                            Imgproc.equalizeHist(frame_gray, frame_gray);


                            MatOfRect faces = new MatOfRect();

                            face_cascade.detectMultiScale(frame_gray, faces, 1.1, 2, 0, new Size(30,30), new Size() );


                            Rect[] facesArray = faces.toArray();

                            for(int i=0; i<facesArray.length; i++)
                            {
                                Point center = new Point(facesArray[i].x + facesArray[i].width * 0.5, facesArray[i].y + facesArray[i].height * 0.5);
                                 Core.ellipse(frame, center, new Size(facesArray[i].width * 0.5, facesArray[i].height * 0.5), 0, 0, 360, new Scalar(255, 0, 255), 4, 8, 0);

                                 Mat faceROI = frame_gray.submat(facesArray[i]);
                                 MatOfRect eyes = new MatOfRect();

                                 //-- In each face, detect eyes
                                 eyes_cascade.detectMultiScale(faceROI, eyes, 1.1, 2, 0,new Size(30,30), new Size());            

                                 Rect[] eyesArray = eyes.toArray();

                                 for (int j = 0; j < eyesArray.length; j++)
                                 {
                                    Point center1 = new Point(facesArray[i].x + eyesArray[i].x + eyesArray[i].width * 0.5, facesArray[i].y + eyesArray[i].y + eyesArray[i].height * 0.5);
                                    int radius = (int) Math.round((eyesArray[i].width + eyesArray[i].height) * 0.25);
                                    Core.circle(frame, center1, radius, new Scalar(255, 0, 0), 4, 8, 0);
                                 }
                                 if(eyesArray.length==0)
                                 {
                                     count = count+1;
                                     if(count>4)
                                     {
                                       
                                        CaptureGUI.msg.setForeground(Color.RED);
                                        
                                     }
                                     if(count==15)
                                     {
                                         
                                         JOptionPane.showMessageDialog(null, "Face image is scanned!");
                                     }
                                 }
                                 else
                                 {
                                     count = 0;
                                    
                                     CaptureGUI.msg.setForeground(Color.GREEN);
                                    
                                     CaptureGUI.msg.setText("Alaram OFF");
                                 }
                            }

                            //Highgui.imwrite(window_name, frame);

                            
                            
                            Mat gray = crop;
                            Mat circles = new Mat();
                            Imgproc.cvtColor(gray, gray, Imgproc.COLOR_BGR2GRAY);
                            System.out.println("1 Hough :" +circles.size());
                            float circle[] = new float[3];

                            for (int i = 0; i < circles.cols(); i++)
                            {
                                circles.get(0, i, circle);
                                org.opencv.core.Point center = new org.opencv.core.Point();
                                center.x = circle[0];
                                center.y = circle[1];
                                Core.circle(gray, center, (int) circle[2], new Scalar(255,255,100,1), 4);
                            }


                            Imgproc.Canny( gray, gray, 200, 10, 3,false);  

                            Imgproc.HoughCircles( gray, circles, Imgproc.CV_HOUGH_GRADIENT, 1, 100, 80, 10, 10, 50 );
                            System.out.println("2 Hough:" +circles.size());

                            for (int i = 0; i < circles.cols(); i++)
                            {
                                    circles.get(0, i, circle);
                                org.opencv.core.Point center = new org.opencv.core.Point();
                                center.x = circle[0];
                                center.y = circle[1];
                                Core.circle(gray, center, (int) circle[2], new Scalar(255,255,100,1), 4);
                            }
                            Imgproc.Canny( gray, gray, 200, 10, 3,false);  

                            Imgproc.HoughCircles( gray, circles, Imgproc.CV_HOUGH_GRADIENT, 1, 100, 80, 10, 10, 50 );
                            System.out.println("3 Hough" +circles.size());

                            //float circle[] = new float[3];

                            for (int i = 0; i < circles.cols(); i++)
                            {
                                    circles.get(0, i, circle);
                                org.opencv.core.Point center = new org.opencv.core.Point();
                                center.x = circle[0];
                                center.y = circle[1];
                                Core.circle(gray, center, (int) circle[2], new Scalar(255,255,100,1), 4);
                            }
                            
			 }
			 catch(Exception ex)
                         {
			    System.out.println("Error");
                         }
                }
            }
        }
     }
}
