/**
 * 
 */
package com.soochna.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.soochna.entity.Notice;

/**
 * @author ankit
 *
 */
public interface NoticeRepository extends CrudRepository<Notice, Serializable> {

	Notice findById(long id);

	Notice findByNoticeTitle(String noticeTitle);

	List<Notice> findByMinistryName(String ministryName);

	List<Notice> findAllByOrderByCreatedTimeStampDesc();
	
	Notice findByNoticeTitleAndMinistryName(String ministryName, String noticeTitle);
}	