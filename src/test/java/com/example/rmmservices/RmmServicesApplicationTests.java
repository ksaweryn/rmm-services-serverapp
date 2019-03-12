package com.example.rmmservices;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.rmmservices.controller.DeviceController;
import com.example.rmmservices.dao.DeviceDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { DeviceController.class })
public class RmmServicesApplicationTests {

	private MockMvc mockMvc;
	
	@Mock
	private DeviceDao deviceDao;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void givenWac_whenServletContext_thenItProvidesDeviceController() {
	    ServletContext servletContext = wac.getServletContext();
	    Mockito.mock(DeviceBean.class);
	    Assert.assertNotNull(servletContext);
	    Assert.assertTrue(servletContext instanceof MockServletContext);
	    Assert.assertNotNull(wac.getBean("deviceController"));
	}

}
