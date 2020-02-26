package com.insonix.qrata.utility;

import java.io.File;

/**
 * @author Gurminder Singh
 *
 * @date 01-Aug-2013
 */
public class QrataImagesUtil {

	/**
	 * This method will be used to get the root folder i.e. Qrata-Data 
	 * 
	 * @return file
	 */
	public static File getQrataDataFolder() {
		File qrataData = null;
		try {
			File webApps = new File(System.getProperty("catalina.base")+"/webapps").getAbsoluteFile();			
			if(webApps != null && webApps.exists()) {
				qrataData = new File(webApps, "/Qrata-Data");
				if(qrataData != null && !qrataData.exists()) {
					qrataData.mkdir();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qrataData;
	}
	
	/**
	 * This method will be used to create the image file for saving the uploaded pic
	 * 
	 * @param imgName
	 * @param userId
	 * @return file
	 */
	public static File createExpertPicFile(String imgName, long userId) {
		File qrataData = getQrataDataFolder();
		File expertPic = null;
		try {
			if(qrataData != null && qrataData.exists()) {
				expertPic = new File(qrataData, "/Expert-Pics/"+userId);
				if(expertPic != null && !expertPic.exists()) {
					expertPic.mkdirs();
				}
				expertPic = new File(expertPic, "/"+userId+"_"+imgName);
				if(expertPic != null && !expertPic.exists()) {
					expertPic.createNewFile();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expertPic;
	}
	
	/**
	 * This method will be used to delete the old image file of the Expert
	 * 
	 * @param imgName
	 * @param userId
	 * @return file
	 */
	public static void deleteOldExpertPicFile(String imgName, long userId) {
		File qrataData = getQrataDataFolder();
		File expertPic = null;
		try {
			if(qrataData != null && qrataData.exists()) {
				expertPic = new File(qrataData, "/Expert-Pics/"+userId+"/"+userId+"_"+imgName);
				if(expertPic != null && expertPic.exists()) {
					expertPic.delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will be used to delete the Expert Pics directory
	 * 
	 * @param imgName
	 * @param siteId
	 * @return 
	 */
	public static void deleteExpertPicFolder(String imgName, long expertId) {
		File qrataData = getQrataDataFolder();
		File expertPic = null;
		try {
			if(qrataData != null && qrataData.exists()) {
				expertPic = new File(qrataData, "/Expert-Pics/"+expertId+"/"+expertId+"_"+imgName);
				if(expertPic != null && expertPic.exists()) {
					expertPic.delete();
				}
				
				expertPic = new File(qrataData, "/Expert-Pics/"+expertId);
				if(expertPic != null && expertPic.exists()) {
					expertPic.delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will be used to create the image file for saving the uploaded pic for Content Logo
	 * 
	 * @param imgName
	 * @param siteId
	 * @return file
	 */
	public static File createContentPicFile(String imgName, long siteId) {
		File qrataData = getQrataDataFolder();
		File contentPic = null;
		try {
			if(qrataData != null && qrataData.exists()) {
				contentPic = new File(qrataData, "/Content-Pics/"+siteId);
				if(contentPic != null && !contentPic.exists()) {
					contentPic.mkdirs();
				}
				contentPic = new File(contentPic, "/"+siteId+"_"+imgName);
				if(contentPic != null && !contentPic.exists()) {
					contentPic.createNewFile();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentPic;
	}
	
	/**
	 * This method will be used to delete the old image file of the Content Logo
	 * 
	 * @param imgName
	 * @param siteId
	 * @return 
	 */
	public static void deleteOldContentPicFile(String imgName, long siteId) {
		File qrataData = getQrataDataFolder();
		File contentPic = null;
		try {
			if(qrataData != null && qrataData.exists()) {
				contentPic = new File(qrataData, "/Content-Pics/"+siteId+"/"+siteId+"_"+imgName);
				if(contentPic != null && contentPic.exists()) {
					contentPic.delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will be used to delete the Content Logo directory
	 * 
	 * @param imgName
	 * @param siteId
	 * @return 
	 */
	public static void deleteContentPicFolder(String imgName, long siteId) {
		File qrataData = getQrataDataFolder();
		File contentPic = null;
		try {
			if(qrataData != null && qrataData.exists()) {
				contentPic = new File(qrataData, "/Content-Pics/"+siteId+"/"+siteId+"_"+imgName);
				if(contentPic != null && contentPic.exists()) {
					contentPic.delete();
				}
				
				contentPic = new File(qrataData, "/Content-Pics/"+siteId);
				if(contentPic != null && contentPic.exists()) {
					contentPic.delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
