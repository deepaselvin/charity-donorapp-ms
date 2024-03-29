package com.revature.charityappdonorms.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.charityappdonorms.dto.DonorDto;
import com.revature.charityappdonorms.dto.MailContributeDto;
import com.revature.charityappdonorms.dto.RequestorDto;
import com.revature.charityappdonorms.dto.UserDto;
import com.revature.charityappdonorms.exception.ServiceException;
import com.revature.charityappdonorms.exception.ValidatorException;
import com.revature.charityappdonorms.model.Donor;
import com.revature.charityappdonorms.repository.DonorTransactionRepository;
import com.revature.charityappdonorms.util.MessageConstant;
import com.revature.charityappdonorms.validator.DonorContributeValidator;

@Service
public class DonorTransactionService {
	@Autowired
	UserService userService;

	@Autowired
	MailService mailservice;
	
	@Autowired
	DonorTransactionRepository donorTransactionRepository;

	@Autowired
	DonorContributeValidator donorValidator;

	private static final Logger LOGGER = LoggerFactory.getLogger(DonorTransactionService.class);

	
	/**
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

			    donorTransactionRepository.save(d);		
				
				MailContributeDto mail=new MailContributeDto();
				UserDto user =userService.getUserId(donor.getUserId());
				RequestorDto requestor=userService.getFund(donor.getRequestId());
				if(user !=null)
				{
				mail.setEmail(user.getEmail());	
			
				}
				if(requestor != null)
				{
				mail.setAmount(donor.getAmount());
				mail.setCategoryName(requestor.getCategoryName());
				}
				mailservice.sendContributeMail(mail);
			
			} 
			catch (ValidatorException e)
			{
				LOGGER.error("Validator Exception:", e);	
				throw new ServiceException(MessageConstant.INVALID_USERID_REQUESTID_AMOUNT);
			}
			 catch (Exception e) {
			
					throw new ServiceException(MessageConstant.UNABLE_TO_TRANSACTION);
			 }
			
			
	}

	/**
	 * DONOR COUNT [how are all donated to a request]
	 * input was given as REQUESTID
	 * it returns the count of donor's
	 

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
	*/

	/**
	 * DONOR TOTAL AMOUNT [how much amount all donated by all donor's]
	 * it returns the total amount contributed by donor's
	 */

	@Transactional
	public Long donorTotalAmount() throws ServiceException {

		Long findAmount=null;
			try {
				findAmount = donorTransactionRepository.findTotalAmount();
			} 
			 catch (Exception e) {
				
				throw new ServiceException(MessageConstant.UNABLE_TO_TOTAL_AMOUNT);
			 }			
			return findAmount;
	}
	
	/**
	 * DONOR AMOUNT BY REQUESTID [how much amount was contributed to a particular request id]
	 * input was given as REQUESTID
	 * it returns the amount donated to a particular of request Id
	 */

	@Transactional
	public Long donorAmountById(int requestId) throws ServiceException {

		Long findAmountByRequestid=null;
			try {
			
			 findAmountByRequestid = donorTransactionRepository.findAmountByRequestId(requestId);	
			} 
			 catch (Exception e) {
					
				 LOGGER.error("Exception:", e);
				 throw new ServiceException(MessageConstant.UNABLE_TO_TOTAL_AMOUNT_BY_ID);
			 }			
			return findAmountByRequestid;
	}
	/**
	 * allDonorTransList[it returns the list of donation done by donor's]
	 * if there is no transaction done a empty array list will be returned
	 * */


	@Transactional
	public List<Donor> allDonorTransList() {
		List<Donor> list = null;
		try {
			list = donorTransactionRepository.findAll();
			System.out.println(list);
		} catch (Exception e) {
			LOGGER.error("Exception:", e);
		}
		return list;
	}

	/**
	 * LIST DONOR CONTRIBUTION BY REQUEST ID user can able to view their donation list
	 * returns:UserId,UserName,RequestId,RequestName,AmountDonated,Date
	 * userName was taken from userMicroservice
	 * categoryNamwe was taken from requestorMicroservice
	 */

	@Transactional
	public List<RequestorDto> donorTranByRequestId(int requestId)  throws ServiceException{
		List<Donor> list = null;
		list = donorTransactionRepository.findDonorByRequestId(requestId);
		System.out.println(list);
        
        List<RequestorDto> listDto=new ArrayList<>();
        for (Donor donor : list) {
        	RequestorDto dto = new RequestorDto();
        	
            dto.setId(donor.getUserId());
            dto.setCategoryId(donor.getRequestId());
            dto.setAmount(donor.getAmount());
            dto.setCreatedDate(donor.getCreateDate());
                     
            UserDto user = userService.getUserId(donor.getUserId());
            if(user != null) {
            dto.setName(user.getName());}

            RequestorDto donorObj = userService.getFund(dto.getCategoryId());
            if(donorObj!= null) {
            dto.setCategoryName(donorObj.getCategoryName());
            dto.setFundNeeded(donorObj.getFundNeeded());}
            listDto.add(dto);
        }       
		return listDto;
	}
	

	/**
	 * LIST MY DONATION user can able to view their donation list
	 * contains:UserId,UserName,RequestId,RequestName,AmountDonated,Date
	 * userName was taken from userMicroservice
	 * categoryNamwe was taken from requestorMicroservice
	 */

	@Transactional
	public List<RequestorDto> myDonorTransList(int userId)   throws ServiceException{
		List<Donor> list = null;
		list = donorTransactionRepository.findByDonorId(userId);
		System.out.println(list);

        
        List<RequestorDto> listDto=new ArrayList<>();
        for (Donor donor : list) {
        	RequestorDto dto = new RequestorDto();
        	
            dto.setId(donor.getUserId());
            dto.setCategoryId(donor.getRequestId());
            dto.setAmount(donor.getAmount());
            dto.setCreatedDate(donor.getCreateDate());        
            
            UserDto user = userService.getUserId(donor.getUserId());
            if(user != null) 
            {
            dto.setName(user.getName());
            }

            RequestorDto donorObj = userService.getFund(dto.getCategoryId());
            if(donorObj!= null) 
            {
            dto.setCategoryName(donorObj.getCategoryName());
            }
           
            listDto.add(dto);
        }       
		

		return listDto;
	}

	
	
	/**
	 * LIST ALL  DONATION user can able to view who are all donated 
	 * contains:UserId,UserName,RequestId,RequestName,AmountDonated,Date
	 */

	public List<RequestorDto> findAll()  throws ServiceException {
       
		List<Donor> list = donorTransactionRepository.findAll();
		System.out.println(list);
        
		 
        List<RequestorDto> listDto=new ArrayList<>();
        for (Donor donor : list) {
        	RequestorDto dto = new RequestorDto();
            dto.setId(donor.getUserId());
            dto.setCategoryId(donor.getRequestId());
            dto.setAmount(donor.getAmount());
           dto.setCreatedDate(donor.getCreateDate());                     
            
            UserDto user = userService.getUserId(donor.getUserId());
            if(user != null) {
            dto.setName(user.getName());}

            RequestorDto donorObj = userService.getFund(dto.getCategoryId());
            if(donorObj!= null) {
            dto.setCategoryName(donorObj.getCategoryName());
            dto.setFundNeeded(donorObj.getFundNeeded());
            }
            
            listDto.add(dto);
        }       
		
		
		return listDto;
	}	
	
}
