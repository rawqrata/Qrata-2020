package com.qrata.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.qrata.enums.Constants.Roles;

@Entity
@Table(name = "user", schema = "public")
public class User extends CommonEntity implements Serializable {

	private static final long serialVersionUID = -68340675336244656L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
	@SequenceGenerator(name = "users_id_seq", initialValue = 1, sequenceName = "user_id_seq", allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(name = "username", unique = true, nullable = false, length = 50)
	private String userName;

	@Column(name = "password")
	private String password;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private UserInfo userinfo;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, 
	        inverseJoinColumns = {
					@JoinColumn(name = "role_id", nullable = false, updatable = false) })
	private Set<Role> roles;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "expert", cascade = CascadeType.ALL)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<TopicExpertAssignment> topicExpertAssignments = new HashSet<TopicExpertAssignment>(0);

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<SiteReview> siteReviews;

	public User() {
	}

	public User(String userName, String password, UserInfo userInfo) {
		this.userName = userName;
		this.password = password;
		this.userinfo = userInfo;
		// this.setSortName(this.getUserinfo().getLastname());
	}

	@Override
	public void setSortName(String sortName) {
		super.setSortName(sortName);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<TopicExpertAssignment> getTopicExpertAssignments() {
		return topicExpertAssignments;
	}

	public void setTopicExpertAssignments(Set<TopicExpertAssignment> topicExpertAssignments) {
		this.topicExpertAssignments = topicExpertAssignments;
	}

	public Set<SiteReview> getSiteReviews() {
		return siteReviews;
	}

	public void setSiteReviews(Set<SiteReview> siteReviews) {
		this.siteReviews = siteReviews;
	}

	@Transient
	public boolean isExpert() {
		return containsExpert();
	}

	@Transient
	public boolean isEditor() {
		return containsEditor();
	}

	@Transient
	public boolean isAdmin() {
		return containsAdmin();
	}

	private boolean containsExpert() {
		
		for (Role role : getRoles()) {
			if (role.getId() == Roles.EXPERT.getValue()) {
				return true;
			}
		}
		return false;
	}

	private boolean containsEditor() {
		for (Role role : getRoles()) {
			if (role.getId() == Roles.EDITOR.getValue()) {
				return true;
			}
		}
		return false;
	}

	private boolean containsAdmin() {
		for (Role role : getRoles()) {
			if (role.getId() == Roles.ADMIN.getValue()) {
				return true;
			}
		}
		return false;
	}

}
