package com.insonix.qrata.service;

import java.util.List;

import com.insonix.qrata.entity.AddSiteForm;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.Topic;

/**
 * @author Gurminder Singh
 *
 * @date 19-Jun-2013
 */
public interface SiteService {
	
	public List<AddSiteForm> getActiveSites_Topic(String name, Topic topic,int start, int pagesize) ;
	
	public Site getSite_Id(long siteId);
}
