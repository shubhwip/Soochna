/**
 * 
 */
package com.soochna.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soochna.entity.Notice;
import com.soochna.repositories.NoticeRepository;
import com.soochna.service.NoticeService;

/**
 * @author root
 *
 */
@Service("noticeService")
@Transactional
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;

	@Override
	public Notice saveNotice(Notice notice) {
		return noticeRepository.save(notice);
	}

	@Override
	public boolean isNoticeExist(Notice notice) {
		return findByNoticeTitle(notice.getNoticeTitle()) != null;
	}

	@Override
	public Notice findById(long id) {
		return noticeRepository.findById(id);
	}

	@Override
	public Notice updateNotice(Notice notice) {
		return noticeRepository.save(notice);
	}

	@Override
	public void deleteNotice(Notice notice) {
		noticeRepository.delete(notice);
	}

	@Override
	public Notice findByNoticeTitle(String noticeTitle) {
		return noticeRepository.findByNoticeTitle(noticeTitle);
	}

	@Override
	public List<Notice> findByMinistryName(String ministryName) {
		return noticeRepository.findByMinistryName(ministryName);
	}

	@Override
	public List<Notice> findAll() {
		return noticeRepository.findAllByOrderByCreatedTimeStampDesc();
	}

}