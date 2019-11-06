package com.revature.charityappdonorms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.revature.charityappdonorms.dto.MailContributeDto;
import com.revature.charityappdonorms.dto.MailDto;

@Service
public class MailService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);
	  
	@Autowired
	RestTemplate restTemplate;
	
	void sendMail(final MailDto mailDTO)
	{
		String apiUrl = "https://charity-notification.herokuapp.com";
		ResponseEntity<Void> postForEntity = restTemplate.postForEntity(apiUrl+"/mail/registeruser", mailDTO, Void.class);
		LOGGER.info("sendmail:", postForEntity);
	}
	
	public void sendContributeMail(final MailContributeDto mailDTO)
	{
		String apiUrl = "https://charity-notification.herokuapp.com";
		ResponseEntity<Void> postForEntity = restTemplate.postForEntity(apiUrl+"/mail/donationmail", mailDTO, Void.class);
		LOGGER.info("sendContributeMail:", postForEntity);
	}

}
