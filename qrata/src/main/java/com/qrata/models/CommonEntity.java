package com.qrata.models;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@MappedSuperclass
public class CommonEntity {
    
	@Column(name = "status")
	private Short status;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created", length = 35)
	@Basic
	private Date dateCreated;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated", length = 35)
	@Basic
	private Date lastUpdated;
	
	private String uuid;
	

	@Transient
	private String sortName;
	

	public Short getStatus() {
		return status;
	}


	public void setStatus(Short status) {
		this.status = status;
	}


	public Long getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}


	public Date getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	public Date getLastUpdated() {
		return lastUpdated;
	}


	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getSortName() {
		return sortName;
	}


	public void setSortName(String sortName) {
		this.sortName = sortName;
	}



}
