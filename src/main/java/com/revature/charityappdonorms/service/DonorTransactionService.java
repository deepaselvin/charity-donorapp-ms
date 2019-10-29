package com.revature.charityappdonorms.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.charityappdonorms.controller.DonorTransactionController;
import com.revature.charityappdonorms.dto.DonorDto;
import com.revature.charityappdonorms.exception.ServiceException;
import com.revature.charityappdonorms.exception.ValidatorException;
import com.revature.charityappdonorms.model.Donor;
import com.revature.charityappdonorms.repository.DonorTransactionRepository;
import com.revature.charityappdonorms.util.MessageConstant;
import com.revature.charityappdonorms.validator.DonorContributeValidator;

@Service
public class DonorTransactionService {

	@Autowired
	DonorTransactionRepository donorTransactionRepository;

	@Autowired
	DonorContributeValidator donorValidator;

	private static final Logger LOGGER = LoggerFactory.getLogger(DonorTransactionController.class);

	
	/*
	 * insert TRANSACATIONAL DETAILS USERID from userService REQUESTID from
	 * RequestorService
	 */

	@Transactional
	public void donorContribute(DonorDto donor) throws ServiceException {


			try {
				donorValidator.contributeValidator(donor);

				Donor d = new Donor();
				d.setRequestId(donor.getRequestId());
				d.setUserId(donor.getUserId());
				d.setAmount(donor.getAmount());
				d.setCreateDate(LocalDateTime.now());
				d.setUpdateDate(LocalDateTime.now());
				// insert method
				donorTransactionRepository.save(d);
			} catch (ValidatorException e) {
				
				LOGGER.error("Exception:", e);
			}
			 catch (Exception e) {
					
					throw new ServiceException(MessageConstant.UNABLE_TO_TRANSACTION);
			 }
			
	}

	/*
	 * LIST ALL  DONATION user can able to view who are all donated 
	 * contains:UserId,UserName,RequestId,RequestName,AmountDonated,Date
	 */

	@Transactional
	public List<Donor> allDonorTransList() throws ServiceException {
		List<Donor> list = null;
		list = donorTransactionRepository.findAll();

		if (list.isEmpty()) {
			throw new ServiceException(MessageConstant.ALL_UNABLE_TO_LIST);
		}
		return list;
	}


	/*
	 * LIST MY DONATION user can able to view their donation list
	 * contains:UserId,UserName,RequestId,RequestName,AmountDonated,Date
	 */

	@Transactional
	public List<Donor> myDonorTransList(int userId) throws ServiceException {
		List<Donor> list = null;
		list = donorTransactionRepository.findByDonor(userId);

		if (list.isEmpty()) {
			throw new ServiceException(MessageConstant.MY_UNABLE_TO_LIST);
		}
		return list;
	}
	
}
