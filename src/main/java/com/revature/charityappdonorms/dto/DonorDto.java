package com.revature.charityappdonorms.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DonorDto {
	private int id;

	private int userId;

	private int requestId;

	private double amount;

	private LocalDateTime createDate;

	private LocalDateTime updateDate;

}
