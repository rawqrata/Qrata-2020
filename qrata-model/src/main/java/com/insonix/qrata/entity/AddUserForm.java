package com.insonix.qrata.entity;

import com.insonix.qrata.models.User;

/**
 * @author kamal
 * this is our model class.
 */
public class AddUserForm {
	private long id;
	private String uuid;
	private String firstName;
	private String lastName;
	private String email;
	private String bio;
	private String imageName;
	private String userName;
	private String password;
	private String confirmPassword;
	private Short roleId;
	private String userSearchVal;
	private int noOfCoreRatings;
	private int noOfTotalRatings;
	private int count;
	private long expertId;

	public int getCount() {
		return count;
	}


	


	public long getExpertId() {
		return expertId;
	}





	public void setExpertId(long expertId) {
		this.expertId = expertId;
	}





	public void setCount(int count) {
		this.count = count;
	}


	public AddUserForm() {
		super();
	}


	public AddUserForm(User user) {
		super();
		this.firstName=user.getUserinfo().getFirstname();
		this.lastName=user.getUserinfo().getLastname();
		this.email=user.getUserinfo().getEmail();
		this.userName=user.getUserName();
		this.password=user.getPassword();
		this.roleId=user.getRole().getId();
		this.email=user.getUserinfo().getEmail();
	}

	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getBio() {
		return bio;
	}


	public void setBio(String bio) {
		this.bio = bio;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}


	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	/**
	 * @return the roleId
	 */
	public Short getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Short roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the userSearchVal
	 */
	public String getUserSearchVal() {
		return userSearchVal;
	}


	/**
	 * @param userSearchVal the userSearchVal to set
	 */
	public void setUserSearchVal(String userSearchVal) {
		this.userSearchVal = userSearchVal;
	}

	public int getNoOfCoreRatings() {
		return noOfCoreRatings;
	}


	public void setNoOfCoreRatings(int noOfCoreRatings) {
		this.noOfCoreRatings = noOfCoreRatings;
	}


	public int getNoOfTotalRatings() {
		return noOfTotalRatings;
	}


	public void setNoOfTotalRatings(int noOfTotalRatings) {
		this.noOfTotalRatings = noOfTotalRatings;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AddUserForm [firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", userName=" + userName + ", password="
				+ password + ", confirmPassword=" + confirmPassword
				+ ", roleId=" + roleId + ", userSearchVal=" + userSearchVal
				+ "]";
	}

}
