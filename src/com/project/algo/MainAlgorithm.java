package com.project.algo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainAlgorithm {
	
	public boolean classify(String file1,String file2) throws InterruptedException, IOException
	{
		
		BufferedReader reader1 = new BufferedReader(new FileReader(file1));
        
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));
         
        String line1 = reader1.readLine();
         
        String line2 = reader2.readLine();
         
        boolean resultStatus = true;
         
        while (line1 != null || line2 != null)
        {
            if(line1.equalsIgnoreCase(line2))
            {
            	resultStatus = false;
                 
                break;
            }
             
            line1 = reader1.readLine();
             
            line2 = reader2.readLine();
             
           
        }
        reader1.close();
         
        reader2.close();
   
		
		return resultStatus;
	}
	
	/*public static void main(String args[]) throws InterruptedException, IOException
	{
		
		MatchFeatures m=new MatchFeatures();
		
		String FeatureFile1= "D:/TrainData/sadfeatures/features0.txt";
		String FeatureFile2="D:/TrainData/sadfeatures/features0.txt";
		
		Boolean result=m.matchFeatures(FeatureFile1, FeatureFile2);
		
		System.out.println("Result= "+result);
		
	}*/
}
