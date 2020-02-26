package com.insonix.qrata.utility;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailUtility
{
	private MailSender mailSender;
	private SimpleMailMessage simpleMailMessage;
	
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(String dear, String content) throws RuntimeException{

		SimpleMailMessage message = new SimpleMailMessage(simpleMailMessage);
		message.setTo(dear);
		message.setText(content);
		mailSender.send(message);
		
	}
	
	public void sendFeedbackMail(String subject,String name , String from, String content) throws RuntimeException{

		SimpleMailMessage message = new SimpleMailMessage(simpleMailMessage);
		message.setFrom(from);
		message.setSubject(subject);
		message.setText(name);
		message.setText(content);
		mailSender.send(message);
		
	}
	
	
}
