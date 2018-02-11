package com.soochna.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soochna.dao.NoticeDetails;
import com.soochna.dao.PendingApprovalDao;
import com.soochna.dao.ToApproveDao;
import com.soochna.entity.Approval;
import com.soochna.entity.Notice;
import com.soochna.service.ApprovalService;
import com.soochna.service.NoticeService;

@RestController
@RequestMapping("/api")
public class NoticeController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	NoticeService noticeService;

	@Autowired
	ApprovalService approvalService;

	@RequestMapping(value = "/notice/save", method = RequestMethod.POST)
	public ResponseEntity<String> saveNotice(@RequestBody Notice notice, UriComponentsBuilder uriComponentBuilder,
			HttpSession httpSession) {
		Object session = httpSession.getAttribute("uid");
		if (session == null)
			return new ResponseEntity<String>("Please login", HttpStatus.UNAUTHORIZED);
		notice.setMinistryName(httpSession.getAttribute("ministryName").toString());
		if (noticeService.isNoticeExist(notice)) {
			logger.error("Notice with given ID already exists");
			return new ResponseEntity<String>("Notice already exists", HttpStatus.CONFLICT);
		}
		notice.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
		notice.setDeleted(false);
		noticeService.saveNotice(notice);
		Approval approval = new Approval();
		approval.setApprovalStatus("pending");
		approval.setApprovingMinistry(notice.getApprovingMinistry());
		approval.setRequestingMinistry(notice.getMinistryName());
		approval.setNoticeContent(notice.getNoticeContent());
		approval.setNoticeTitle(notice.getNoticeTitle());
		this.addForApproval(approval);
		return new ResponseEntity<String>("Notice Saved Successfully", HttpStatus.OK);
	}

	private ResponseEntity<String> addForApproval(Approval approval) {
		approvalService.saveForApproval(approval);
		return new ResponseEntity<String>("Approved", HttpStatus.OK);
	}

	@RequestMapping(value = "/notices/pending", method = RequestMethod.GET)
	public ResponseEntity<String> getPendingNotices(HttpSession httpSession,
			UriComponentsBuilder uriComponentsBuilder) {
		Object session = httpSession.getAttribute("uid");
		if (session == null)
			return new ResponseEntity<String>("Please login", HttpStatus.UNAUTHORIZED);
		List<Approval> pendingList = approvalService
				.findByApprovingMinistry(httpSession.getAttribute("ministryName").toString());
		List<PendingApprovalDao> pendingApprovalList = new ArrayList<>();
		for (int i = 0; i < pendingList.size(); i++) {
			if(!pendingList.get(i).getApprovalStatus().equals("pending")) {
				continue;
			}
			PendingApprovalDao pendingApprovalDao = new PendingApprovalDao();
			pendingApprovalDao.setNoticeContent(pendingList.get(i).getNoticeContent());
			pendingApprovalDao.setNoticeTitle(pendingList.get(i).getNoticeTitle());
			pendingApprovalDao.setRepuestingMinistry(pendingList.get(i).getRequestingMinistry());
			pendingApprovalList.add(pendingApprovalDao);
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			String result = mapper.writeValueAsString(pendingApprovalList);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/notice/approve", method = RequestMethod.POST)
	public ResponseEntity<String> approveNotice(@RequestBody ToApproveDao toApproveDao, HttpSession httpSession,
			UriComponentsBuilder uriComponentsBuilder) {
		Object session = httpSession.getAttribute("uid");
		if (session == null)
			return new ResponseEntity<String>("Please login", HttpStatus.UNAUTHORIZED);
		String noticeTitle = toApproveDao.getNoticeTitle();
		approvalService.approveNotice(noticeTitle);
		return null;
	}

	@RequestMapping(value = "/notice/byMinistry", method = RequestMethod.GET)
	public ResponseEntity<String> getNoticesCurrentMinistry(UriComponentsBuilder uriComponentBuilder,
			HttpSession httpSession) {

		Object session = httpSession.getAttribute("ministryName");
		if (session == null)
			return new ResponseEntity<String>("Please login", HttpStatus.UNAUTHORIZED);
		String ministryName = session.toString();
		List<Notice> allNotices = new ArrayList<Notice>();
		List<NoticeDetails> noticeList = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		allNotices = noticeService.findByMinistryName(ministryName);

		for (int i = 0; i < allNotices.size(); i++) {
			NoticeDetails noticeDetails = new NoticeDetails();
			noticeDetails.setMinistryName(allNotices.get(i).getMinistryName());
			noticeDetails.setNoticeContent(allNotices.get(i).getNoticeContent());
			noticeDetails.setNoticeTitle(allNotices.get(i).getNoticeTitle());
			noticeDetails.setCreatedTimeStamp(allNotices.get(i).getCreatedTimeStamp());
			if(allNotices.get(i).isApproved()) {
				noticeDetails.setApprovalStatus("Approved");
			} else {
				noticeDetails.setApprovalStatus("Pending");
			}
			noticeList.add(noticeDetails);
		}
		try {
			String result = "{ \"notices\":" + mapper.writeValueAsString(noticeList) + "}";
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			return new ResponseEntity<String>("An error occured", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/notice/byMinistry/{ministry-name}", method = RequestMethod.GET)
	public ResponseEntity<String> getNoticesByMinistryName(@RequestParam(value = "ministry-name") String ministryName,
			UriComponentsBuilder uriComponentBuilder) {

		List<Notice> allNotices = new ArrayList<Notice>();
		List<NoticeDetails> noticeList = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		allNotices = noticeService.findByMinistryName(ministryName);

		for (int i = 0; i < allNotices.size(); i++) {
			if(!allNotices.get(i).isApproved()) {
				continue;
			}
			NoticeDetails noticeDetails = new NoticeDetails();
			noticeDetails.setMinistryName(allNotices.get(i).getMinistryName());
			noticeDetails.setNoticeContent(allNotices.get(i).getNoticeContent());
			noticeDetails.setNoticeTitle(allNotices.get(i).getNoticeTitle());
			noticeDetails.setCreatedTimeStamp(allNotices.get(i).getCreatedTimeStamp());
			noticeDetails.setApprovalStatus("Approved");
			noticeList.add(noticeDetails);
		}
		try {
			String result = "{ \"notices\":" + mapper.writeValueAsString(noticeList) + "}";
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			return new ResponseEntity<String>("An error occured", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/notice/ministry", method = RequestMethod.GET)
	public ResponseEntity<String> getAllNotices(UriComponentsBuilder uriComponentBuilder, HttpSession httpSession) {
		List<Notice> allNotices = new ArrayList<Notice>();
		List<NoticeDetails> noticeList = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		allNotices = noticeService.findAll();
		System.out.println(allNotices.size() + "" + allNotices.get(0).getNoticeTitle());
		for (int i = 0; i < allNotices.size(); i++) {
			if(!allNotices.get(i).isApproved()) {
				continue;
			}
			NoticeDetails noticeDetails = new NoticeDetails();
			noticeDetails.setMinistryName(allNotices.get(i).getMinistryName());
			noticeDetails.setNoticeContent(allNotices.get(i).getNoticeContent());
			noticeDetails.setNoticeTitle(allNotices.get(i).getNoticeTitle());
			noticeDetails.setCreatedTimeStamp(allNotices.get(i).getCreatedTimeStamp());
			noticeDetails.setApprovalStatus("Approved");
			noticeList.add(noticeDetails);
		}
		try {
			String result = "{ \"notices\":" + mapper.writeValueAsString(noticeList) + "}";
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			return new ResponseEntity<String>("An error occured", HttpStatus.BAD_REQUEST);
		}

	}

}