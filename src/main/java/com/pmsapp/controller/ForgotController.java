package com.pmsapp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.constants.AppConstants;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.UUIDGenerator;
import com.jpa.entities.PasswordChangeInfo;
import com.jpa.entities.User;
import com.jpa.repositories.PasswordResetRepo;
import com.web.service.ApplicationService;
import com.web.service.EmailService;
import com.web.service.UserService;
import com.web.util.RestResponse;

@RequestMapping(value = "/forgot")
@Controller
public class ForgotController extends BaseController{


	private static final Logger LOGGER = LoggerFactory.getLogger(ForgotController.class);

	/** The user service. */
	@Autowired
	private UserService userService;

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private EmailService emailService;
	
	
	
	@Autowired
	private PasswordResetRepo passwordResetRepo;

	@RequestMapping(value = "/password/page", method = RequestMethod.GET)
	public String forgotPage(final Locale locale, final ModelMap model,
			final HttpServletRequest request, final HttpSession session) {
			return "forgot.page";
	}
	
	
	@RequestMapping(value = "/password/reset", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<RestResponse> sendPasswordResetLink(@RequestParam (value="email") String email, final HttpSession session) {
		LOGGER.info("Inside ForgotController .. sendPasswordResetLink");
		RestResponse response = new RestResponse();
		ResponseEntity<RestResponse> responseEntity = new ResponseEntity<RestResponse>(HttpStatus.NO_CONTENT);
		if(StringUtils.isNotBlank(email)){
			String resetPassword = RandomStringUtils.randomAlphanumeric(8);
			try {
				response = userService.resetForgotPassword(email, resetPassword);
				if(response.getStatusCode()==200){
					response = emailService.sendForgotPasswordEmail(email, response.getMessage());
					if(response.getStatusCode() == 202){
						response.setStatusCode(200);
						response.setMessage("An email has been sent to reset you password.");
						responseEntity = new ResponseEntity<RestResponse>(response,HttpStatus.OK);
					}else{
						response.setStatusCode(204);
						response.setMessage("Password reset email cannot be sent. Please contact administrator.");
						responseEntity = new ResponseEntity<RestResponse>(response,HttpStatus.NOT_FOUND);
					}
				}
			} catch (Exception e) {
				response.setStatusCode(500);
				LOGGER.error("Exception while resetting forgot password", e);
				responseEntity = new ResponseEntity<RestResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		LOGGER.info("Inside ForgotController .. sendPasswordResetLink");
		return responseEntity;
	}
	
}
