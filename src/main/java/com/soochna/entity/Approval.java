/**
 * 
 */
package com.soochna.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author root
 *
 */
@Entity
@Table(name = "approval")
public class Approval {

	@Id
	@NotNull
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	@Column(name = "approvingMinistry", nullable = false)
	private String approvingMinistry;

	@Column(name = "requestingMinistry", nullable = false)
	private String requestingMinistry;
	
	@Column(name = "noticeTitle")
	private String noticeTitle;
	
	@Column(name = "noticeContent")
	private String noticeContent;

	@Column(name = "approvalStatus", nullable = false)
	private String approvalStatus;
	
	public Approval() {
		super();
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
	 * @return the approvingMinistry
	 */
	public String getApprovingMinistry() {
		return approvingMinistry;
	}

	/**
	 * @param approvingMinistry the approvingMinistry to set
	 */
	public void setApprovingMinistry(String approvingMinistry) {
		this.approvingMinistry = approvingMinistry;
	}

	/**
	 * @return the requestingMinistry
	 */
	public String getRequestingMinistry() {
		return requestingMinistry;
	}

	/**
	 * @param requestingMinistry the requestingMinistry to set
	 */
	public void setRequestingMinistry(String requestingMinistry) {
		this.requestingMinistry = requestingMinistry;
	}

	/**
	 * @return the noticeTitle
	 */
	public String getNoticeTitle() {
		return noticeTitle;
	}

	/**
	 * @param noticeTitle the noticeTitle to set
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
	 * @param noticeContent the noticeContent to set
	 */
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	/**
	 * @return the approvalStatus
	 */
	public String getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * @param approvalStatus the approvalStatus to set
	 */
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Approval(long id, String approvingMinistry, String requestingMinistry, String noticeTitle,
			String noticeContent, String approvalStatus) {
		super();
		this.id = id;
		this.approvingMinistry = approvingMinistry;
		this.requestingMinistry = requestingMinistry;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.approvalStatus = approvalStatus;
	}
	
}
