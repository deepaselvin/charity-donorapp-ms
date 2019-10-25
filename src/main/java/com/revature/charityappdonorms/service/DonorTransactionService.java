package com.revature.charityappdonorms.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.charityappdonorms.dto.DonorDto;
import com.revature.charityappdonorms.exception.ServiceException;
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
		} catch (Exception e) {
			throw new ServiceException(MessageConstant.UNABLE_TO_TRANSACTION);
		}
	}

	/*
	 * LIST MY DONATION user can able to view their donation list
	 * contains:UserId,UserName,RequestId,RequestName,AmountDonated,Date
	 */

	@Transactional
	public List<Donor> donorTransList() throws ServiceException {
		List<Donor> list = null;
		list = donorTransactionRepository.findAll();

		if (list.isEmpty()) {
			throw new ServiceException(MessageConstant.UNABLE_TO_LIST);
		}
		return list;
	}

}
