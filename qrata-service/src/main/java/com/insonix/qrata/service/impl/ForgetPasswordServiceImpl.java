package com.insonix.qrata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insonix.qrata.dao.UserDao;
import com.insonix.qrata.models.User;
import com.insonix.qrata.service.ForgetPasswordService;
import com.insonix.qrata.utility.MailUtility;

@Service("forgetPasswordService")
public class ForgetPasswordServiceImpl implements ForgetPasswordService{

	@Autowired
	UserDao userDao;
	
	@Autowired
	MailUtility mailUtility;
	
	@Override
	public User checkEmailIdExist(String emailId) {
		User user = userDao.getUser_Email(emailId);
		return user;
	}

	@Override
	public boolean sendEmail(User user,String context) throws RuntimeException{
		String message = "Hi QRata User, \n\nSeems you have forgotten your password. You have just requested your password. Below are your login credentials." +
				"\n\nUserID : "+user.getUserName()+"\nPassword :"+user.getPassword()+"\n\nLink for log in: "+context+"/loginForm.htm"+"\n\n\n" +
						"This is a system generated email. Do not reply to this email. \nFor any other information please contact the Administrator at info@qrata.com" +
						"\n\n\n\nThanks, \nQRata team." ;
		mailUtility.sendMail(user.getUserinfo().getEmail(), message);
		return false;
	}

}
