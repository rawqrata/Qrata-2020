package com.insonix.qrata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insonix.qrata.dao.UserInfoDao;
import com.insonix.qrata.models.UserInfo;
import com.insonix.qrata.service.UserInfoService;

/**
 * @author kamal
 *
 */
@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	UserInfoDao userInfoDao;

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserInfoService#getUserInfo_Id(int)
	 */
	@Override
	public UserInfo getUserInfo_Id(int id) {
		
		return userInfoDao.get(id);
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserInfoService#delete(com.insonix.qrata.models.UserInfo)
	 */
	@Override
	public void delete(UserInfo userInfo) {
		userInfoDao.delete(userInfo);
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserInfoService#save(com.insonix.qrata.models.UserInfo)
	 */
	@Override
	public String save(UserInfo userInfo) {
		String id = userInfoDao.save(userInfo);
		return id;
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserInfoService#update(com.insonix.qrata.models.UserInfo)
	 */
	@Override
	public void update(UserInfo userInfo) {
		userInfoDao.update(userInfo);
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserInfoService#updateBulk(java.util.List)
	 */
	@Override
	public void updateBulk(List<UserInfo> userInfoList) {
		userInfoDao.updateBulk(userInfoList);
		
	}
}
