package com.qrata.entity;

import org.springframework.web.multipart.MultipartFile;

public class ExpertBioForm {
	private long id;
	private MultipartFile file = null;
	private String bio;
	private String uuid;
	private String imageName;
	private String topicSearchVal;
	private String firstName;
	private String lastName;
	
	/**
	 * @return the topicSearchVal
	 */
	public String getTopicSearchVal() {
		return topicSearchVal;
	}

	/**
	 * @param topicSearchVal the topicSearchVal to set
	 */
	public void setTopicSearchVal(String topicSearchVal) {
		this.topicSearchVal = topicSearchVal;
	}

	/**
	 * 
	 */
	public ExpertBioForm() {
		super();
	}

	/**
	 * @param file
	 * @param bio
	 * @param id
	 */
	public ExpertBioForm(MultipartFile file, String bio, String uuid) {
		super();
		this.file = file;
		this.bio = bio;
		this.uuid = uuid;
	}

	
	/**
	 * @param bio
	 * @param uuid
	 * @param imgPath
	 */
	public ExpertBioForm(String bio, String uuid, String imageName) {
		super();
		this.bio = bio;
		this.uuid = uuid;
		this.imageName = imageName;
	}
	
	/**
	 * @param bio
	 * @param id
	 * @param imgPath
	 */
	public ExpertBioForm(String bio, long id, String imageName) {
		super();
		this.bio = bio;
		this.id = id;
		this.imageName = imageName;
	}

	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	/**
	 * @return the bio
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * @param bio
	 *            the bio to set
	 */
	public void setBio(String bio) {
		this.bio = bio;
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
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
