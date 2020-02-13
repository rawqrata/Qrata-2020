package com.insonix.qrata.models;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@MappedSuperclass
public class CommonEntity {
	private Short status;
	private Long createdBy;
	private Date dateCreated;
	private Date lastUpdated;
	private String uuid;
	private String sortName;
	
	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "created_by")
	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created", length = 35)
	@Basic
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated", length = 35)
	@Basic
	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
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

	@Transient
	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/*@Override
	public String toString() {
		return "CommonEntity [status=" + status + ", createdBy=" + createdBy
				+ ", dateCreated=" + dateCreated + ", lastUpdated="
				+ lastUpdated + ", uuid=" + uuid + ", sortName=" + sortName
				+ "]";
	}*/
	
}
