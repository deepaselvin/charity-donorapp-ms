package com.revature.charityappdonorms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.charityappdonorms.dto.MailContributeDto;
import com.revature.charityappdonorms.service.MailService;

@SpringBootTest
class CharityappDonorMsApplicationTests {
	@Autowired
	MailService mailService;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
    void testContributeMail() {
		MailContributeDto mail = new 	MailContributeDto();
		mail.setCategoryName("Education");
		mail.setAmount(40);
		mail.setEmail("ennillaoo7@gmail.com");
		assertEquals(40,mail.getAmount());
		assertEquals("Education",mail.getCategoryName());
		assertEquals("ennillaoo7@gmail.com",mail.getEmail());
		
		mailService.sendContributeMail(mail);
        assertNotNull(mail);
    }

}
