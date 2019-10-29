package com.revature.charityappdonorms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.charityappdonorms.model.Donor;


/*
 * to update any inputs in the table
 */
@Repository
public interface DonorTransactionRepository extends JpaRepository<Donor, Integer> {

	@Query(" from Donor where  userId = :userId ")
	List<Donor> findByDonor(int userId);

	
	@Query(" select count(*) from Donor d where d.requestId = ?1")
	Long findByRequestid(int requestId);



}
