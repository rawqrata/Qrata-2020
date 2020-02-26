package com.insonix.qrata.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.insonix.qrata.entity.AddSiteForm;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.pagination.PaginationUtility;
import com.insonix.qrata.service.SiteService;
import com.insonix.qrata.service.TopicService;
import com.insonix.qrata.utility.QrataImagesUtil;
import com.insonix.qrata.utility.Utility;

/**
 * @author Gurminder Singh
 *
 * @date 19-Jun-2013
 */

@Controller("siteController")
@RequestMapping(value="sites")
public class SiteController {
	@Autowired SiteService siteService;
	@Autowired TopicService topicService;
	@Autowired ServletContext servletContext;

	@RequestMapping(value="findContentsByTopic.htm", method={RequestMethod.GET})
	public String findContentsByTopic(Model model, @RequestParam("id") int topicId, HttpServletRequest request,@ModelAttribute("siteForm") AddSiteForm form) {
		Topic topic = topicService.getTopics_Id(topicId);
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "siteReview");
		List<AddSiteForm> sites = siteService.getActiveSites_Topic(Utility.replaceSpecialCharacters(form.getSiteSearchVal()),topic,start,PaginationUtility.pageSize);
		model.addAttribute("sites", sites);
		return "sites/listContentsByTopic";
	}
	
		/*
		 * Display Content's Image
		 */
	@RequestMapping(value="getContentImage.htm", method=RequestMethod.GET)
		public void getContentImage(@RequestParam("id") long siteId, HttpServletResponse response, HttpServletRequest request) {
		OutputStream out  = null;
		try {		
			Site site = siteService.getSite_Id(siteId);
			if(StringUtils.isNotEmpty(site.getImagePath())) {
				File qrataData = QrataImagesUtil.getQrataDataFolder();
				File contentPicFile = new File(qrataData, site.getImagePath());
				BufferedImage img = ImageIO.read(contentPicFile);
				out = response.getOutputStream();
				
				ImageIO.write(img, "png", out);
				out.flush();
				img.flush();
			}
		} catch (Exception e) {
			try {
				String filePath = servletContext.getRealPath("/resources/images/column-image.gif");
				File contentPicFile = new File(filePath);
				BufferedImage img = ImageIO.read(contentPicFile);
				out = response.getOutputStream();
				
				ImageIO.write(img, "png", out);
				out.flush();
				img.flush();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(out != null)
					out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
