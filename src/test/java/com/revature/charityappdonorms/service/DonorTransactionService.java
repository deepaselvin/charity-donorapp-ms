package com.revature.charityappdonorms.service;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.charityappdonorms.exception.ServiceException;
import com.revature.charityappdonorms.model.Donor;
import com.revature.charityappdonorms.repository.DonorTransactionRepository;

//@RunWith(SpringRunner.class)
@SpringBootTest// package name is same it takes above configuration

class DonorTransactionService {


	@InjectMocks
	DonorTransactionService donorTransactionService;

	@Mock
	private DonorTransactionRepository donorRepoMock;

   @BeforeEach
  void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
			
	@Test
	
	public void testValidRegister() throws ServiceException {
		Donor donor = new Donor();
		donor.setRequestId(1);
		donor.setUserId(1);
		donor.setAmount(7000);
		Mockito.when(donorRepoMock.save((Donor) any(Donor.class))).thenReturn(donor);
		//Mockito.when(donorRepoMock.save(anyString(), anyString())).thenReturn(null);
	//TODO::HOW TO CALL THE SERVICE CLASS HERE
		//donorTransactionService.
}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
