package com.generic.selenium.util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import com.generic.selenium.report.ReportUtil;
import com.generic.selenium.setup.SelTestCase;


public class SendMail extends SelTestCase{

	public static String readIndexFile(String filePath) throws IOException {
		String content = "";
		BufferedReader in = new BufferedReader(new FileReader(filePath));
	    try {
	        String str;
	        while ((str = in.readLine()) != null) {
	            content +=str;
	        }
	    } catch (IOException e) {
	    } finally{
	    	in.close();
	    }
		return content;
	}
	
	public static void sendSummeryMail(String log_path){
		getCurrentFunctionName(true);
		final String mail_to  = getCONFIG().getProperty("toEmail");
		final String from = getCONFIG().getProperty("fromEmail");
		final String password = getCONFIG().getProperty("fromEmailPassword");
		
		logs.debug("sending mail to: "+ mail_to);
		
		String host = "smtp.gmail.com";
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");
			
	
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			});

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail_to));
			message.setSubject("AutomationReport_"+ReportUtil.now("dd_MMMMM_hhmmss"));
			message.setContent(readIndexFile(log_path),"text/html");

			Transport.send(message);

			logs.debug("message sent successfully...");
		}
		catch (Throwable e) {
			logs.debug("Fail to send message");
			System.out.println(e.getMessage());
		}
		getCurrentFunctionName(false);
	}
}