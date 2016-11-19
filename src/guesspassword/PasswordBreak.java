/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guesspassword;

import java.security.*;
import java.io.*;
import java.util.*;
import java.lang.StringBuilder;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author lukasz
 */
public class PasswordBreak  {
     private String password;
     
    
//    
    
    public static String bytesToHex(byte[] b) {
	      char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
	                         '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	      StringBuffer buf = new StringBuffer();
	      for (int j=0; j<b.length; j++) {
	         buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
	         buf.append(hexDigit[b[j] & 0x0f]);
	      }
	      return buf.toString();
	   }
    
    public static String stringToMD5(String input) throws Exception {
		//Setup a MessageDigest for MD5 
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.reset();
		
		//Setup the MessageDigest with our input string
        md.update(input.getBytes("UTF-8"));
        
        //Convert the string's digest to HEX
        String md5 = bytesToHex(md.digest());
		return md5;
	}

   

    public PasswordBreak() {
        
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String run() throws Exception{
        
        System.out.println("Zaczynamy !!!");
        
        File passwords_file = new File("password.txt");
    	FileInputStream password_stream = new FileInputStream(passwords_file);
    	BufferedReader password_buffer = new BufferedReader(new InputStreamReader(password_stream));
        
        Map<String, String> passwords = new HashMap<String, String>();
        
        String password_file_line = null;
    	while ((password_file_line = password_buffer.readLine()) != null)
            
            password_buffer.close();
        
        
        File fin = new File("medium.txt");
    	FileInputStream fis = new FileInputStream(fin);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        
        
        
        String line = null;
    	while ((line = br.readLine()) != null) {
    		//We first iterate through the non salted passwords
    		Iterator non_salted_passwords_it = passwords.entrySet().iterator();
    		while (non_salted_passwords_it.hasNext()) {
    			//We extract the key,value pair from the HashTable entry
    			Map.Entry pair = (Map.Entry)non_salted_passwords_it.next();
    	     //   String account_name = pair.getKey().toString(); 
    	        String account_password_hash = pair.getValue().toString();

    	        //We test if the password matches an unmodified dictionary entry
    	        if(account_password_hash.equals(stringToMD5(line))){
    	        	System.out.println( "Hasło to" + line + "'");
        		}
    	        
    	        //We test if the password matches a reversed dictionary entry
    	        String reversed_line = new StringBuilder(line).reverse().toString();
    	        if(account_password_hash.equals(stringToMD5(reversed_line))){
    	        	System.out.println( "Hasło to" + reversed_line + "'");
        		}
    	        
    	        //We test if the password matches a dictionary entry without its vowels
    	        String line_without_vowels = line.replaceAll("[AEIOUaeiou]", "");
    	        if(account_password_hash.equals(stringToMD5(line_without_vowels))){
    	        	System.out.println( "hasło to" + line_without_vowels + "'");
        		}
    		}
        
        }
        br.close();
        
        System.out.println("Koniec");
        return "1234";
    }

}
