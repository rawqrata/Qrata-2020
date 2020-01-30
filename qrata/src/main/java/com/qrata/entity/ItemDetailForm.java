

package com.qrata.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qrata.models.SiteReviewRatingCriteria;


public class ItemDetailForm {

	private int item_id;
	private int CategoryID;
	private int ratings;
	
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	private String item_name;
	private String item_description;
	private String item_image;
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getCategoryID() {
		return CategoryID;
	}
	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}

	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_description() {
		return item_description;
	}
	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}
	public String getItem_image() {
		return item_image;
	}
	public void setItem_image(String item_image) {
		this.item_image = item_image;
	}

	

	
}

