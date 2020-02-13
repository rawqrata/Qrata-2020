package com.insonix.qrata.service;

import com.insonix.qrata.models.User;

public interface ForgetPasswordService {
	
	public User checkEmailIdExist(String elmailId);
	
	public boolean sendEmail(User userInfo,String context);
}
