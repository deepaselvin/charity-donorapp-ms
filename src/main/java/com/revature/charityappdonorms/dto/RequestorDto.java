package com.revature.charityappdonorms.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RequestorDto {

	 private Integer id;
	    private int categoryId;
	    private double fundNeeded;
	    private String status;
	    private int requestedBy;
	    private String requestedByName;
	    private String categoryName;
	    private LocalDateTime createdDate;
	    private LocalDateTime modifiedDate;
	    private boolean active;
		private String name;
	
}
