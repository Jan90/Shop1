package com.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;

import com.web.ShoppingCartController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:mvc-dispatcher-servlet.xml" })
@WebAppConfiguration
public class TestShoppingCartController {

	@InjectMocks
	private ShoppingCartController cartController = new ShoppingCartController();

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
	}

	@Test
	public void testgetShoppingCart() throws Exception {
		mockMvc.perform(get("/getShoppingCart"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("shoppingCart"))
				.andExpect(
						MockMvcResultMatchers.model().attributeExists(
								"cartProducts"));
	}
}
