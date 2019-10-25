package com.revature.charityappdonorms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.charityappdonorms.model.Donor;


/*
 * to update any inputs in the table
 */
@Repository
public interface DonorTransactionRepository extends JpaRepository<Donor, Integer> {

	

}
