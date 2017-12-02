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
	

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMinistryName() {
		return ministryName;
	}

	public void setMinistryName(String ministryName) {
		this.ministryName = ministryName;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Timestamp getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public boolean isApproved() {
		return approved;
	}

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