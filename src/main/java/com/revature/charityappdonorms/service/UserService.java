package com.revature.charityappdonorms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.revature.charityappdonorms.dto.RequestorDto;
import com.revature.charityappdonorms.dto.UserDto;

@Service
public class UserService {
	
	@Autowired
	RestTemplate restTemplate;
	
	public UserDto getUserId(final Integer userId) {
		String apiUrl="https://userapp-v1.herokuapp.com";
		ResponseEntity <UserDto> postForEntity = restTemplate.getForEntity(apiUrl + "/user/" + userId,UserDto.class);
		UserDto result = postForEntity.getBody();
		return result;
		
	}
	
	public RequestorDto getFund(final Integer Id) {
		System.out.println("RequestID:" + Id);
        String apiUrl = "https://charity-requestor.herokuapp.com";
        RequestorDto result=null;
        try {
			ResponseEntity<RequestorDto> postForEntity = restTemplate.getForEntity(apiUrl + "/fundrequest/"+ Id ,RequestorDto.class);
      result = postForEntity.getBody();
		} catch (RestClientException e) {
			
			e.printStackTrace();
		}
        System.out.println(result);
        return result;
    }

}

