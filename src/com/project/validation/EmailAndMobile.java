/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.validation;

/**
 *
 * @author Iee
 */
public class EmailAndMobile {
    public static boolean isValidEmailAddress(String email) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
    
    public static boolean isValidPassword(String pass) {
    	java.util.regex.Pattern p = java.util.regex.Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
    	java.util.regex.Matcher m = p.matcher(pass);
		return m.matches();
    	
    }
    
     public static boolean isValidMobilNumber(String mobNo) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("^[7-9][0-9]{9}$");
        java.util.regex.Matcher m = p.matcher(mobNo);
        return m.matches();
    }
     public static boolean isValidName(String name) {
         java.util.regex.Pattern p = java.util.regex.Pattern.compile("^[a-zA-Z\\s]*$");
         java.util.regex.Matcher m = p.matcher(name);
         return m.matches();
     }
      
}
