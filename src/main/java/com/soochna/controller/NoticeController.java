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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soochna.dao.NoticeDetails;
import com.soochna.entity.Notice;
import com.soochna.service.NoticeService;

@RestController
@RequestMapping("/api")
public class NoticeController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	NoticeService noticeService;

	@RequestMapping(value = "/notice/save", method = RequestMethod.POST)
	public ResponseEntity<String> saveNotice(@RequestBody Notice notice, UriComponentsBuilder uriComponentBuilder, HttpSession httpSession) {
		Object session = httpSession.getAttribute("uid");
		if(session==null)
			return new ResponseEntity<String>("Please login", HttpStatus.UNAUTHORIZED);
		notice.setMinistryName(httpSession.getAttribute("ministryName").toString());
		if (noticeService.isNoticeExist(notice)) {
			logger.error("Notice with given ID already exists");
			return new ResponseEntity<String>("Notice already exists", HttpStatus.CONFLICT);
		}
		notice.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
		notice.setDeleted(false);
		noticeService.saveNotice(notice);
		return new ResponseEntity<String>("Notice Saved Successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/notice/byMinistry", method = RequestMethod.GET)
	public ResponseEntity<String> getNoticesByMinistryName(UriComponentsBuilder uriComponentBuilder, HttpSession httpSession) {

		Object session= httpSession.getAttribute("ministryName");
		if(session==null)
			return new ResponseEntity<String>("Please login", HttpStatus.UNAUTHORIZED);
		String ministryName= session.toString();
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
			NoticeDetails noticeDetails = new NoticeDetails();
			noticeDetails.setMinistryName(allNotices.get(i).getMinistryName());
			noticeDetails.setNoticeContent(allNotices.get(i).getNoticeContent());
			noticeDetails.setNoticeTitle(allNotices.get(i).getNoticeTitle());
			noticeDetails.setCreatedTimeStamp(allNotices.get(i).getCreatedTimeStamp());
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