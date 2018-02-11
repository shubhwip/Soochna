package com.soochna.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "notice")
public class Notice {

	public Notice() {
		super();
	}

	@Id
	@NotNull
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	@Column(name = "ministryName", nullable = false)
	private String ministryName;

	@Column(name = "noticeTitle")
	private String noticeTitle;

	@Column(name = "noticeContent", nullable = false)
	private String noticeContent;

	@Column(name = "createdTimeStamp", nullable = false)
	Timestamp createdTimeStamp;

	@Column(name = "isDeleted", nullable = false)
	boolean deleted;
	
	

	@Column(name = "isApproved", nullable = false)
	boolean approved;

	@Column(name = "approvingMinistry", nullable = false)
	private String approvingMinistry;

	/**
	 * @return the approvingMinistry
	 */
	public String getApprovingMinistry() {
		return approvingMinistry;
	}

	/**
	 * @param approvingMinistry
	 *            the approvingMinistry to set
	 */
	public void setApprovingMinistry(String approvingMinistry) {
		this.approvingMinistry = approvingMinistry;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the ministryName
	 */
	public String getMinistryName() {
		return ministryName;
	}

	/**
	 * @param ministryName
	 *            the ministryName to set
	 */
	public void setMinistryName(String ministryName) {
		this.ministryName = ministryName;
	}

	/**
	 * @return the noticeTitle
	 */
	public String getNoticeTitle() {
		return noticeTitle;
	}

	/**
	 * @param noticeTitle
	 *            the noticeTitle to set
	 */
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	/**
	 * @return the noticeContent
	 */
	public String getNoticeContent() {
		return noticeContent;
	}

	/**
	 * @param noticeContent
	 *            the noticeContent to set
	 */
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	/**
	 * @return the createdTimeStamp
	 */
	public Timestamp getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	/**
	 * @param createdTimeStamp
	 *            the createdTimeStamp to set
	 */
	public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	/**
	 * @return the deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * @param deleted
	 *            the deleted to set
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the approved
	 */
	public boolean isApproved() {
		return approved;
	}

	/**
	 * @param approved
	 *            the approved to set
	 */
	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Notice(long id, String ministryName, String noticeTitle, String noticeContent, Timestamp createdTimeStamp,
			boolean deleted, boolean approved) {
		super();
		this.id = id;
		this.ministryName = ministryName;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.createdTimeStamp = createdTimeStamp;
		this.deleted = deleted;
		this.approved = approved;
	}

}