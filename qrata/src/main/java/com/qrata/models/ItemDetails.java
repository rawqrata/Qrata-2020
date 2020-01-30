package com.qrata.models;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name = "item_details")
public class ItemDetails extends CommonEntity implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemdetails_id_seq")
	@Column(name = "item_id")
	private int itemid;
	
	@Column(name = "CategoryID")
	private int CategoryID;
	
	@Column(name = "Ratings")
	private int ratings;
	
	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	@Column(name = "item_name")
    private String itemName;
	
	@Column(name = "item_description")
    private String itemDescription;

	@Column(name = "Item_Image")
	private String itemImage;
	
	
	public ItemDetails() {
			
		
		}
	
    public ItemDetails(int itemid, int categoryID, String itemName, String itemDescription, String itemImage,int Ratings) {
		super();
		this.itemid = itemid;
		CategoryID = categoryID;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemImage = itemImage;
	
	}




	public int getItemid() {
		return itemid;
	}


	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	

	public int getCategoryID() {
		return CategoryID;
	}


	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}
   


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getItemDescription() {
		return itemDescription;
	}


	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	 public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}


}
