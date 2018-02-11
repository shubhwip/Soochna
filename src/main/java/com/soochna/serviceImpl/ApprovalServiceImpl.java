/**
 * 
 */
package com.soochna.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soochna.entity.Approval;
import com.soochna.entity.Notice;
import com.soochna.repositories.ApprovalRepository;
import com.soochna.service.ApprovalService;
import com.soochna.service.NoticeService;

/**
 * @author root
 *
 */
@Service("approvalService")
@Transactional
public class ApprovalServiceImpl implements ApprovalService{

	@Autowired
	ApprovalRepository approvalRepository;
	
	@Autowired
	NoticeService noticeService;
	
	@Override
	public void saveForApproval(Approval approval) {
		approvalRepository.save(approval);
	}

	@Override
	public List<Approval> findByApprovingMinistry(String approvingMinistry) {
		return approvalRepository.findByApprovingMinistryAndApprovalStatus(approvingMinistry, "pending");
	}

	@Override
	public Approval findByNoticeTitle(String noticeTitle) {
		return approvalRepository.findByNoticeTitle(noticeTitle);
	}

	@Override
	public boolean approveNotice(String noticeTitle) {
		Approval approval = approvalRepository.findByNoticeTitle(noticeTitle);
		approval.setApprovalStatus("approved");
		Notice notice = noticeService.findByNoticeTitle(noticeTitle);
		notice.setApproved(true);
		noticeService.saveNotice(notice);
		approvalRepository.save(approval);
		return true;
	}

}
