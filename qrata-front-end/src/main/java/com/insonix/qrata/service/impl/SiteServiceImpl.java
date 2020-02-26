package com.insonix.qrata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.SiteDao;
import com.insonix.qrata.entity.AddSiteForm;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.service.SiteService;

/**
 * @author Gurminder Singh
 *
 * @date 19-Jun-2013
 */

@Service("siteService")
public class SiteServiceImpl implements SiteService {
	@Autowired SiteDao sitesDao;
	
	
	@Override
	public List<AddSiteForm> getActiveSites_Topic(String name, Topic topic,int start, int pagesize) {
		if(name == null){
			name = "%";
		}else{
			name = name.trim() + "%";
		}
		return sitesDao.getActiveSites_Topic(Status.ACTIVE,name,topic,start,pagesize);
	}
	
	@Override
	public Site getSite_Id(long siteId) {
		return sitesDao.get(siteId);
	}
	
}
