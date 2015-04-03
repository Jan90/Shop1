package com.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.model.CD;
import com.model.Store;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:mvc-dispatcher-servlet.xml" })
@WebAppConfiguration
public class TestCdController {

	@InjectMocks
	private CdController cdController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(cdController).build();
	}

	@Test
	public void testgetAllProductList() throws Exception {
		mockMvc.perform(get("/getProductList/page/{page}", 1))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("errorPage"));
		CD cd = new CD();
		cd.setName("Alladin");
		cd.setGenre("Film");
		cd.setType("Audio");
		cd.setQuantity();
		Store.productList.add(cd);
		mockMvc.perform(get("/getProductList/page/{page}", 1))
				.andExpect(status().isOk())
				.andExpect(
						MockMvcResultMatchers.model().attributeExists("page"))
				.andExpect(
						MockMvcResultMatchers.model().attributeExists(
								"pageCount"))
				.andExpect(
						MockMvcResultMatchers.model().attributeExists(
								"productList"));
	}
}
