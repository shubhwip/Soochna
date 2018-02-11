package com.soochna.service;

import java.util.List;

import com.soochna.entity.Notice;

public interface NoticeService {

	public Notice findById(long id);
	public Notice findByNoticeTitle(String noticeTitle);
	public Notice saveNotice(Notice notice);
	public void updateNotice(String noticeTitle, String ministryName);
	public void deleteNotice(Notice notice);
	public boolean isNoticeExist(Notice notice);
	public List<Notice> findByMinistryName(String ministryName);
	public List<Notice> findAll();
	public void changeApprovalStatus(String noticeTitle);
}