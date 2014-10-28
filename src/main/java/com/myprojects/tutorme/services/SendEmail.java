package com.myprojects.tutorme.services;

//import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	public static void sendTo(String uName)//, String passCode)
	{
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		 
		String clientUName = uName; //"kevin-lewis@uiowa.edu"; this is for examples
		//String clientPswrd = passCode; //"mypassword"; this is example pword
		final String username = "group5fundamentals";
		final String password = "group5password";
		final String fromEmailAddress = "group5fundamentals@gmail.com";
		String toEmailAddress = clientUName;
		String subject = "Account Confirmation";
		String thanks = "Thank you for registering.\n";
		String loginNameText = "Your Username is: " + clientUName;
		//String passwordText = "\nYour Password is: " + clientPswrd;
		String textMessage = thanks + loginNameText;// + passwordText;
		 
		Session session = Session.getDefaultInstance(properties, new Authenticator(){
		protected PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(username, password);
		}
		});
		 
		try {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(fromEmailAddress));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress));
		message.setSubject(subject);
		message.setText(textMessage);
		Transport.send(message);
		 
		System.out.println("Email sent successfully!\n");
		}
		catch(MessagingException e)
		{
		throw new RuntimeException(e);
		}
	}

}
