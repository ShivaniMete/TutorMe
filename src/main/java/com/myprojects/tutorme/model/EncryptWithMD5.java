package com.myprojects.tutorme.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.*;

import com.myprojects.tutorme.model.EncryptWithMD5;

public class EncryptWithMD5 {
	private static MessageDigest md;

	   public static String cryptWithMD5(String pass){
	    try {
	        md = MessageDigest.getInstance("MD5");
	        byte[] passBytes = pass.getBytes();
	        md.reset();
	        byte[] digested = md.digest(passBytes);
	        StringBuffer sb = new StringBuffer();
	        for(int i=0;i<digested.length;i++){
	            sb.append(Integer.toHexString(0xff & digested[i]));
	        }
	        return sb.toString();
	        //System.out.println(sb.toString());
	        
	    } catch (NoSuchAlgorithmException ex) {
	        Logger.getLogger(EncryptWithMD5.class.getName()).log(Level.SEVERE, null, ex);
	    }
	        return null;
	   }

}
