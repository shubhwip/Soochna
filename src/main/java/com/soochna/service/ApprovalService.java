package com.soochna.service;

import java.util.List;

import com.soochna.entity.Approval;

public interface ApprovalService {
	
	public void saveForApproval(Approval approval);
	
	public List<Approval> findByApprovingMinistry(String ministryName);
	public Approval findByNoticeTitle(String noticeTitle);
	public boolean approveNotice(String noticeTitle);
}
