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
import com.revature.charityappdonorms.dto.RequestorDto;
import com.revature.charityappdonorms.exception.ServiceException;
import com.revature.charityappdonorms.service.DonorTransactionService;
import com.revature.charityappdonorms.util.MessageConstant;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("Transaction")
public class DonorTransactionController {

	private static final String EXCEPTION = "Exception:";

	@Autowired
	DonorTransactionService donorTransactionService;

	private static final Logger LOGGER = LoggerFactory.getLogger(DonorTransactionController.class);

	/**
	 * It includes transaction details so post mapping was used.
	 * It returns the total amount contributed by all donor's
	 * 
	 */

	@PostMapping("/TotalAmount")
	@ApiOperation(value = "DonorTotalAmount API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = DonorDto.class),
	@ApiResponse(code = 400, message = "Unable to show Total Amount ", response = Message.class) })

	public ResponseEntity<?> totalAmount() {
		try 
		{
			Long count = null;
			count = donorTransactionService.donorTotalAmount();
			return new ResponseEntity<>(count, HttpStatus.OK);
		} 
		catch (ServiceException e) 
		{
			LOGGER.error(EXCEPTION, e);
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * @RequestParam RequestId was given.
	 * It returns the total amount contributed by all donor's for a particular request.
	 * if the donor's did'nt done any donation to that Id it returns a   "Unable to show Total Amount".
	 */

	@GetMapping("/TotalAmountById")
	@ApiOperation(value = "DonorTotalAmountById API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = DonorDto.class),
	@ApiResponse(code = 400, message = "No one donated for this Request type ", response = Message.class) })

	public ResponseEntity<?> totalAmountById(@RequestParam("requestId") int requestId) {

		try 
		{
			Long count = null;
			count = donorTransactionService.donorAmountById(requestId);
			return new ResponseEntity<>(count, HttpStatus.OK);
		} 
		catch (ServiceException e) 
		{
			LOGGER.error(EXCEPTION, e);
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * It includes transaction details so post mapping was used.
	 * @RequestBody input was given as object it returns a response
	 */

	@PostMapping()
	@ApiOperation(value = "Donorcontribute API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = DonorDto.class),
	@ApiResponse(code = 400, message = "Cannot able to Contribute ", response = Message.class) })

	public ResponseEntity<?> contribute(@RequestBody DonorDto donor) {
		try 
		{
			donorTransactionService.donorContribute(donor);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (ServiceException e)
		{
			LOGGER.error(EXCEPTION, e);
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *  It returns the list of amount contributed by all donor's. 
	 */

	@GetMapping("/AllDonation")
	@ResponseStatus(code = HttpStatus.OK)
	public List<RequestorDto> viewAllDonation() throws ServiceException {
		List<RequestorDto> donorObj = null;
		try {
			donorObj = donorTransactionService.findAll();	
			return donorObj;
		}
		catch (ServiceException e)
		{
			LOGGER.error(EXCEPTION, e);
			throw new ServiceException(MessageConstant.ALL_UNABLE_TO_LIST);

		}

	}

	/**
	 * @RequestParam userId was given[localStorage]
	 *  It returns the total amount contributed by the logged in user
	 */

	@GetMapping("/MyDonation")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> viewMyDonation(@RequestParam("userId") int userId) {

		List<RequestorDto> donorObj;
		try 
		{
			donorObj = donorTransactionService.myDonorTransList(userId);
			return new ResponseEntity<>(donorObj, HttpStatus.OK);
		} 
		catch (ServiceException e)
		{
			LOGGER.error(EXCEPTION, e);
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * @RequestParam requestId was given[localStorage]
	 * It shows Donor's contribution  to a particular request type 
	 * it returns an array of object
	 */

	@GetMapping("/DonationByRequestId")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> viewDonorByRequest(@RequestParam("requestId") int requestId) {

		List<RequestorDto> donorObj;
		try
		{
			donorObj = donorTransactionService.donorTranByRequestId(requestId);	
			return new ResponseEntity<>(donorObj, HttpStatus.OK);
		} 
		catch (ServiceException e)
		{
			LOGGER.error(EXCEPTION, e);
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * It shows the DONORCOUNT so get mapping was used.
	 * @Requestparam input was given as object it returns a response
	 

	@GetMapping("/DonationCount")
	@ApiOperation(value = "Donorcontribute API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = DonorDto.class),
	@ApiResponse(code = 400, message = "Cannot able to Contribute ", response = Message.class) })

	public ResponseEntity<?> donorCount(@RequestParam("requestId") int requestId) {
		try 
		{
			Long count = null;
			count = donorTransactionService.donorCountService(requestId);
			return new ResponseEntity<>(count, HttpStatus.OK);
		}
		catch (ServiceException e)
		{
			LOGGER.error("Exception:", e);
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}*/

}
