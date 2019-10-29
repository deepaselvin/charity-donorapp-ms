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
	 * DONOR COUNT [how are all donated to a request]
	 * input was given as REQUESTID
	 * it returns the count of donor's
	 */

	@Transactional
	public Long donorCountService(int requestId) throws ServiceException {

		Long findByRequestid=null;
			try {
				donorValidator.countValidator(requestId);
				
				// insert method
			 findByRequestid = donorTransactionRepository.findByRequestid(requestId);
		
			} catch (ValidatorException e) {
				
				LOGGER.error("Validator Exception:", e);
			}
			 catch (Exception e) {
				 
			LOGGER.error("Exception:", e);
			 throw new ServiceException(MessageConstant.UNABLE_TO_TRANSACTION);
			 }			
	
			
			return findByRequestid;
	}
	

	/*
	 * DONOR TOTAL AMOUNT [how much amount all donated by all donor's]
	 * it returns the total amount contributed by donor's
	 */

	@Transactional
	public Long donorTotalAmount() throws ServiceException {

		Long findAmount=null;
			try {
				// insert method
				findAmount = donorTransactionRepository.findTotalAmount();
			} 
			 catch (Exception e) {
				 LOGGER.error("Exception:", e);
				throw new ServiceException(MessageConstant.UNABLE_TO_TOTAL_AMOUNT);
			 }			
			return findAmount;
	}
	
	/*
	 * DONOR AOUNT BY REQUESTID [how much amount was contributed to a particular request id]
	 * input was given as REQUESTID
	 * it returns the amount donated to a particular of request Id
	 */

	@Transactional
	public Long donorAmountById(int requestId) throws ServiceException {

		Long findAmountByRequestid=null;
			try {
				
				// insert method
			 findAmountByRequestid = donorTransactionRepository.findAmountByRequestId(requestId);	
			} 
			 catch (Exception e) {
					
				 LOGGER.error("Exception:", e);
				 throw new ServiceException(MessageConstant.UNABLE_TO_TOTAL_AMOUNT_BY_ID);
			 }			
			return findAmountByRequestid;
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
		list = donorTransactionRepository.findByDonorId(userId);

		if (list.isEmpty()) {
			throw new ServiceException(MessageConstant.MY_UNABLE_TO_LIST);
		}
		return list;
	}
	
}
