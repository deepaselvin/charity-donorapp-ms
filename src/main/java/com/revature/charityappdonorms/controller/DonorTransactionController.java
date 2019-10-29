package com.revature.charityappdonorms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.charityappdonorms.dto.DonorDto;
import com.revature.charityappdonorms.dto.Message;
import com.revature.charityappdonorms.exception.ServiceException;
import com.revature.charityappdonorms.model.Donor;
import com.revature.charityappdonorms.service.DonorTransactionService;
import com.revature.charityappdonorms.util.MessageConstant;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("Transaction")
public class DonorTransactionController {

	@Autowired
	DonorTransactionService donorTransactionService;

	private static final Logger LOGGER = LoggerFactory.getLogger(DonorTransactionController.class);


	/*
	 * It shows the DONORCOUNT so get mapping was used.
	 * 
	 * @Requestparam input was given as object it returns a response
	 */

	@GetMapping("/DonationCount")
	
	@ApiOperation(value = "Donorcontribute API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = DonorDto.class),
			@ApiResponse(code = 400, message = "Cannot able to Contribute ", response = Message.class) })

	public ResponseEntity<?> donorCount(@RequestParam("requestId") int requestId) {
		try {	
			Long count=null;
			count=donorTransactionService.donorCountService(requestId);
			return new ResponseEntity<>(count, HttpStatus.OK);
		} catch (ServiceException e) {
			LOGGER.error("Exception:", e);
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	
	/*
	 * It includes transaction details so post mapping was used.
	 * 
	 * @RequestBody input was given as object it returns a response
	 */

	@PostMapping()

	@ApiOperation(value = "Donorcontribute API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = DonorDto.class),
			@ApiResponse(code = 400, message = "Cannot able to Contribute ", response = Message.class) })

	public ResponseEntity<?> contribute(@RequestBody DonorDto donor) {
		try {
			System.out.println(donor);
			donorTransactionService.donorContribute(donor);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch (ServiceException e) {
			LOGGER.error("Exception:", e);
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	/*
	 * It shows transaction details list so post mapping was used. it returns a
	 * array of object
	 */

	@GetMapping("/AllDonation")
	
	@ResponseStatus(code = HttpStatus.OK)
	public List<Donor> viewDonation() throws ServiceException  {
		List<Donor> donorObj = null;
		try {
			donorObj = donorTransactionService.allDonorTransList();
			System.out.println("Alllist"+donorObj);
			return donorObj;
		} catch (ServiceException e) {
			LOGGER.error("Exception:", e);
			throw new ServiceException(MessageConstant.ALL_UNABLE_TO_LIST);

		}
	
	}

	/*
	 * It shows transaction details list so post mapping was used. it returns a
	 * array of object
	 */

    @GetMapping("/MyDonation")
	@ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> subject(@RequestParam("userId") int userId){

		List<Donor> donorObj;
		try {
			donorObj = donorTransactionService.myDonorTransList(userId);
			System.out.println("list"+donorObj);
			return new ResponseEntity<>(donorObj, HttpStatus.OK);
		} catch (ServiceException e) {
			LOGGER.error("Exception:", e);
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	
		 
	}

	
}
