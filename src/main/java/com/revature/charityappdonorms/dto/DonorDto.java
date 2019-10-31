package com.revature.charityappdonorms.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DonorDto {
	private int id;

	private String name;
	
	private int userId;

	private int requestId;
	
	private String categoryName;

	
	private double amount;

	private LocalDateTime createDate;

	private LocalDateTime updateDate;

}
