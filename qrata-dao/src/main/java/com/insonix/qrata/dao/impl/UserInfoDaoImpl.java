package com.insonix.qrata.dao.impl;

import org.springframework.stereotype.Repository;

import com.insonix.qrata.dao.UserInfoDao;
import com.insonix.qrata.models.UserInfo;

@Repository("UserInfoDao")
public class UserInfoDaoImpl extends BaseDao<UserInfo> implements UserInfoDao {

}
