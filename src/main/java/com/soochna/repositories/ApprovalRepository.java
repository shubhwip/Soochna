/**
 * 
 */
package com.soochna.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.soochna.entity.Approval;

/**
 * @author root
 *
 */
public interface ApprovalRepository  extends CrudRepository<Approval, Serializable> {
	
	List<Approval> findByApprovingMinistryAndApprovalStatus(String approvingMinistry, String pending);
	Approval findByNoticeTitle(String noticeTitle);
}
