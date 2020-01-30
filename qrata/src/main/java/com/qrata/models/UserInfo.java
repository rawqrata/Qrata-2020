package com.qrata.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "userinfo", schema = "public")
public class UserInfo extends CommonEntity implements Serializable{
	
private static final long serialVersionUID = 7052060978798642330L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userinfo_id_seq")
	@SequenceGenerator(name = "userinfo_id_seq", initialValue = 1, sequenceName = "userinfo_id_seq", allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",nullable=false)
	private User user;
	
	@Column(name="bio",columnDefinition="text")
	private String bio;
	
	@Column(name="image_path", nullable=true)
	private String imagePath;
	
	@Column(name="image_name", nullable=true)
	private String imageName;
	
	@Column(name = "firstname", length = 50)
	private String firstname;
	
	@Column(name = "lastname", length = 50)
	private String lastname;
	
	@Column(name = "email", length = 100,unique=true)
	private String email;
	
	public UserInfo() {
	}

	public UserInfo(String firstName , String lastName , String email) {
		this.firstname = firstName;
		this.lastname = lastName;
		this.email = email;
	}

	public UserInfo(long id, User user, String firstname, String lastname,
			String email, Short status, Date dateCreated, Date lastUpdated,
			Serializable uuid) {
		this.id = id;
		this.user = user;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}
	
	@Override
	public void setSortName(String sortName){
		super.setSortName(sortName);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	


}
