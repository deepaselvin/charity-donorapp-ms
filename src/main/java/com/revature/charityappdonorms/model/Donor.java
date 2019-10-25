package com.revature.charityappdonorms.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_transaction")
public class Donor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "request_id")
	private int requestId;

	@Column(name = "amount_donated")
	private double amount;

	@Column(name = "transaction_date")
	private LocalDateTime createDate ;

	@Column(name = "modified_date")
	private LocalDateTime updateDate ;

}
