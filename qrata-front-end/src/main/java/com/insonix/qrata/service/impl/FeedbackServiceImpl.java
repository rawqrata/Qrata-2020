package com.insonix.qrata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insonix.qrata.service.FeedbackService;
import com.insonix.qrata.utility.MailUtility;


@Service("forgetPasswordService")
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	MailUtility mailUtility;
	
	@Override
	public boolean sendFeedbackEmail(String subject,String heading,String zipcode,String name,String from,String content) throws RuntimeException{
	 content="Hi Admin,\n\n\nName\t\t:\t"+name+
						"\n\nEmail\t\t:\t"+from+"\n\nZip-code\t:\t"+zipcode+"\n\n"+heading+"\t:\t"+content+"\n\nThanks. \n";
	 subject="User Feedback- "+subject;
		mailUtility.sendFeedbackMail(subject,name,from, content);
		return false;
	}
    
	@Override
	public boolean sendFeedbackCashContent(String name,String zipcode,String from,String topic,String site,String url) throws RuntimeException{
	 String content="Hi Admin,\n\n\nTopic/Subject Area\t:\t"+topic+"\n\nSite, App or Video Name\t:\t"+site+"\n\nSite, App or Video URL\t:\t"+url+
						"\n\nName\t\t\t\t:\t"+name+"\n\nEmail\t\t\t\t:\t"+from+"\n\nzip-code\t\t\t:\t"+zipcode+"\n\n\nThanks.";
	 String subject="User Feedback- Cash for better content";
		mailUtility.sendFeedbackMail(subject,name,from, content);
		return false;
	}

}
