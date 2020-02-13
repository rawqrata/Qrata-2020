package com.insonix.qrata.entity;

public class CategoryForm {

	private String uuid;
	
	private int id;
	
	
	private String name;
	private String categorySearchVal;
	private String domainSearchVal;
	

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * @return the categorySearchVal
	 */
	public String getCategorySearchVal() {
		return categorySearchVal;
	}

	/**
	 * @param categorySearchVal the categorySearchVal to set
	 */
	public void setCategorySearchVal(String categorySearchVal) {
		this.categorySearchVal = categorySearchVal;
	}
	

	/**
	 * @return the domainSearchVal
	 */
	public String getDomainSearchVal() {
		return domainSearchVal;
	}

	/**
	 * @param domainSearchVal the domainSearchVal to set
	 */
	public void setDomainSearchVal(String domainSearchVal) {
		this.domainSearchVal = domainSearchVal;
	}

	@Override
	public String toString() {
		return "CategoryForm [uuid=" + uuid + ", name=" + name + "]";
	}

}
