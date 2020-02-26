package com.insonix.qrata.service;

import java.util.List;

import com.insonix.qrata.models.UserInfo;


/**
 * @author kamal
 *
 */
public interface UserInfoService {
	/**
	 * @param id
	 * @return
	 */
	public UserInfo getUserInfo_Id(int id);
	/**
	 * @param userInfo
	 */
	public void delete(UserInfo userInfo);
	/**
	 * @param userInfo
	 * @return
	 */
	public String save(UserInfo userInfo);
	/**
	 * @param userInfo
	 */
	public void update(UserInfo userInfo);
	/**
	 * @param userInfoList
	 */
	public void updateBulk(List<UserInfo> userInfoList);
}
