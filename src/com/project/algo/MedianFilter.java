package com.project.algo;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.io.*;
import java.util.Arrays;
//import javax.imageio.*;




import javax.imageio.ImageIO;



public class MedianFilter{
	
	BufferedImage originalImage=null;
	BufferedImage filterImage=null;
	
	
	public void noiseRemoveImage(String Finput ,String Foutput) throws IOException{
 
		File input=new File(Finput);
    	
    	originalImage=ImageIO.read(input);
    	
        Color[] pixel=new Color[9];
        int[] R=new int[9];
        int[] B=new int[9];
        int[] G=new int[9];
     
        for(int i=1;i<originalImage.getWidth()-1;i++)
            for(int j=1;j<originalImage.getHeight()-1;j++)
            {
               pixel[0]=new Color(originalImage.getRGB(i-1,j-1));
               pixel[1]=new Color(originalImage.getRGB(i-1,j));
               pixel[2]=new Color(originalImage.getRGB(i-1,j+1));
               pixel[3]=new Color(originalImage.getRGB(i,j+1));
               pixel[4]=new Color(originalImage.getRGB(i+1,j+1));
               pixel[5]=new Color(originalImage.getRGB(i+1,j));
               pixel[6]=new Color(originalImage.getRGB(i+1,j-1));
               pixel[7]=new Color(originalImage.getRGB(i,j-1));
               pixel[8]=new Color(originalImage.getRGB(i,j));
               for(int k=0;k<9;k++){
                   R[k]=pixel[k].getRed();
                   B[k]=pixel[k].getBlue();
                   G[k]=pixel[k].getGreen();
               }
               Arrays.sort(R);
               Arrays.sort(G);
               Arrays.sort(B);
               originalImage.setRGB(i,j,new Color(R[4],B[4],G[4]).getRGB());
            }
          
         File output=new File(Foutput);
         ImageIO.write(originalImage,"jpg",output);
		
		
    }
	
	public  BufferedImage noiseRemove(BufferedImage input) throws IOException
	{
		
    	originalImage=input;
    	
        Color[] pixel=new Color[9];
        int[] R=new int[9];
        int[] B=new int[9];
        int[] G=new int[9];
     
        for(int i=1;i<originalImage.getWidth()-1;i++)
            for(int j=1;j<originalImage.getHeight()-1;j++)
            {
               pixel[0]=new Color(originalImage.getRGB(i-1,j-1));
               pixel[1]=new Color(originalImage.getRGB(i-1,j));
               pixel[2]=new Color(originalImage.getRGB(i-1,j+1));
               pixel[3]=new Color(originalImage.getRGB(i,j+1));
               pixel[4]=new Color(originalImage.getRGB(i+1,j+1));
               pixel[5]=new Color(originalImage.getRGB(i+1,j));
               pixel[6]=new Color(originalImage.getRGB(i+1,j-1));
               pixel[7]=new Color(originalImage.getRGB(i,j-1));
               pixel[8]=new Color(originalImage.getRGB(i,j));
               for(int k=0;k<9;k++){
                   R[k]=pixel[k].getRed();
                   B[k]=pixel[k].getBlue();
                   G[k]=pixel[k].getGreen();
               }
               Arrays.sort(R);
               Arrays.sort(G);
               Arrays.sort(B);
               originalImage.setRGB(i,j,new Color(R[4],B[4],G[4]).getRGB());
            }
        
		return originalImage;
    }
    
 }
