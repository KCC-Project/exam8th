package com.project.exam.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

	public static void sendEmailPasswordForgot(String email, String authienciationCode,int userId, String typeOfUser) 
			throws AddressException, MessagingException {
		
		Properties props = new Properties();
		//------ sets SMTP server properties ---------
//        Properties properties = new Properties();
        
        //------- this is not working for now --------
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", 465);
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
        
        // creates a new session with an authenticator
        // this is a our system.. gmail username and password, 
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("EIS.org.Pvt", "eis123456");
            }
        };

        Session session = Session.getInstance(props,auth);
        
        
        String message_to_user = "The verification link is given below: <br> 1. For Desktop = "
        		+ "http://localhost:8080/exam-project-8/ResetPassword?email="+email+""
        				+ "&code="+authienciationCode+"&tablename="+typeOfUser+"&id="+userId +"<br> If you are using Andoird use the following code <br><br><br>2. For Andoird = <strong>"+authienciationCode+"</strong>";
       
        String message_subject_to_user = "Forgot Password verify link from EIS";
        
        MimeMessage msg = new MimeMessage(session);
        msg.setContent(message_to_user, "text/html");
        msg.setSubject(message_subject_to_user);
        msg.setFrom(new InternetAddress("EIS.org.Pvt"));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        
		Transport transport = session.getTransport("smtps");
		transport.connect("smtp.gmail.com", 465, "EIS.org.Pvt", "eis123456");
		transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
	}
}
