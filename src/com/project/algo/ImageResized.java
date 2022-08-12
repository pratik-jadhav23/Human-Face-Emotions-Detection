package com.project.algo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageResized {
	
	BufferedImage originalImage=null;
	BufferedImage resizedImage=null;
	
	private static final int IMG_WIDTH = 430;
    private static final int IMG_HEIGHT =350;
	
    public void resizeImage(String Finput ,String Foutput,int type) throws IOException
	
    {
    	File input=new File(Finput);
    	
    	
    	originalImage=ImageIO.read(input);
    	//resizedImage=ImageIO.read(output);
    	
        resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
    	
        File output=new File(Foutput);
        ImageIO.write(resizedImage, "jpg", output);
    }
    
public BufferedImage resize(String Finput, int type) throws IOException
	
    {
    	File input=new File(Finput);
    	
    	
    	originalImage=ImageIO.read(input);
    	//resizedImage=ImageIO.read(output);
    	
        resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
    	
        return resizedImage;
    }

}
