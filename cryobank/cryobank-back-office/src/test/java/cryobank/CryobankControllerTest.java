package cryobank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import cryobank.controller.CryobankController;
import cryobank.service.CryobankService;

@WebMvcTest(CryobankController.class)
class CryobankControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	CryobankService cryobankService;
	
	@Test
	void test() {
		//fail("Not yet implemented");
	}

}
