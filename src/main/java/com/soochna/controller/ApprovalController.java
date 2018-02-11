/**
 * 
 */
package com.soochna.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soochna.service.ApprovalService;

/**
 * @author root
 *
 */
@RestController
@RequestMapping("/api")
public class ApprovalController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	ApprovalService approvalService;
	
	

}
