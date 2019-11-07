package com.qrata.service;

public interface FeedbackService {

	public boolean sendFeedbackEmail(String subject,String heading,String zipcode,String name,String from,String context);
	public boolean sendFeedbackCashContent(String name,String zipcode,String from,String topic,String site,String url);
}
