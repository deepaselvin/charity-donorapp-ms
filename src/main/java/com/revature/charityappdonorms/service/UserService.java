package com.revature.charityappdonorms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.revature.charityappdonorms.dto.RequestorDto;
import com.revature.charityappdonorms.dto.UserDto;

@Service
public class UserService {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	   
	@Autowired
	RestTemplate restTemplate;
	
	public UserDto getUserId(final Integer userId) {
		String apiUrl="https://userapp-v1.herokuapp.com";
		ResponseEntity<UserDto> postForEntity;
		UserDto result=null;
		try {
			postForEntity = restTemplate.getForEntity(apiUrl + "/user/" + userId,UserDto.class);
			LOGGER.info("getUserId:", postForEntity);
			result = postForEntity.getBody();
		    } 
		catch (RestClientException e) 
		{
			LOGGER.error("Exception:", e);	
		}

		return result;
		
	}
	
	public RequestorDto getFund(final Integer Id) {
		LOGGER.info("RequestID:" ,Id);
        String apiUrl = "https://charity-requestor.herokuapp.com";
        RequestorDto result=null;
        try 
        {
			ResponseEntity<RequestorDto> postForEntity = restTemplate.getForEntity(apiUrl + "/fundrequest/"+ Id ,RequestorDto.class);
			LOGGER.info("getFund:", postForEntity);
            result = postForEntity.getBody();
		} 
        catch (RestClientException e) 
        {
        	LOGGER.error("Exception:", e);
		}
        
        return result;
    }

}

