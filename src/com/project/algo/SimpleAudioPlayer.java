package com.project.algo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
 
public class SimpleAudioPlayer 
{
 
    // to store current position
    Long currentFrame;
    Clip clip;
     
    // current status of clip
    String status;
     
    AudioInputStream audioInputStream;
    static String filePath;
 
    // constructor to initialize streams and clip
    /*public SimpleAudioPlayer()
        throws UnsupportedAudioFileException,
        IOException, LineUnavailableException 
    {
        // create AudioInputStream object
        audioInputStream = 
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
         
        // create clip reference
        clip = AudioSystem.getClip();
         
        // open audioInputStream to the clip
        clip.open(audioInputStream);
         
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }*/
 
    /*public static void main(String[] args) 
    {
        try
        {
            filePath = "C:/Users/admin/Pictures/AudioFile/Tera Zikr.wav";
            SimpleAudioPlayer audioPlayer = 
                            new SimpleAudioPlayer();
             
            audioPlayer.play();
            Scanner sc = new Scanner(System.in);
             
            while (true)
            {
                System.out.println("1. pause");
                System.out.println("2. stop");
                int c = sc.nextInt();
                audioPlayer.gotoChoice(c);
                if (c == 4)
                break;
            }
            sc.close();
        } 
         
        catch (Exception ex) 
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
         
          }
    }*/
     
    // Work as the user enters his choice
     
    public void gotoChoice(int c)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException 
    {
        switch (c) 
        {
            case 1:
                pause();
                break; 
            case 2:
                resumeAudio();
                break;    
            case 3:
            	stop();
                break;
        }
     
    }
     
    // Method to play the audio
    public void play(String filepath) throws UnsupportedAudioFileException, IOException, LineUnavailableException 
    {
        //start the clip
    	
    	filePath=filepath;
    	System.out.println("path= "+filePath);
    	
    	audioInputStream = 
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
         
        // create clip reference
        clip = AudioSystem.getClip();
         
        // open audioInputStream to the clip
        clip.open(audioInputStream);
         
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    	
        clip.start();
         
        status = "play";
       
    }
     
    // Method to pause the audio
    public void pause() 
    {
        if (status.equals("paused")) 
        {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame = 
        this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }
     
    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException,
                                IOException, LineUnavailableException 
    {
        if (status.equals("play")) 
        {
            System.out.println("Audio is already "+
            "being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play(filePath);
    }
     
    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException,
                                            UnsupportedAudioFileException 
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play(filePath);
    }
     
    // Method to stop the audio
    public void stop() throws UnsupportedAudioFileException,
    IOException, LineUnavailableException 
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }
     
    // Method to jump over a specific part
    public void jump(long c) throws UnsupportedAudioFileException, IOException,
                                                        LineUnavailableException 
    {
        if (c > 0 && c < clip.getMicrosecondLength()) 
        {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play(filePath);
        }
    }
     
    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
                                            LineUnavailableException 
    {
        audioInputStream = AudioSystem.getAudioInputStream(
        new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
 
}