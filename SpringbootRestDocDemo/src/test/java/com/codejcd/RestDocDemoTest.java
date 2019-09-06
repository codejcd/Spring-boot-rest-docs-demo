package com.codejcd;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestDocDemoTest {
@Rule
public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

@Autowired
private WebApplicationContext context;

private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
           .apply(documentationConfiguration(this.restDocumentation))
           .alwaysDo(document("{method-name}/{class-name}"))
           .build();
	}

	@Test
	 public void getUserInfoById() throws Exception{
	       this.mockMvc.perform(get("/demo/user/user1"))
	              .andDo(print())
	              .andExpect(status().isOk())
	              .andDo(document("index",
	                       responseFields(
	                               fieldWithPath("id").description("id"),
	                               fieldWithPath("name").description("name"),
	                               fieldWithPath("email").description("email")
	                              )
	              ));
	  }

	@Test
	 public void registUserInfo() throws Exception{
	      this.mockMvc.perform(post("/demo/user")
	    		  .param("id", "user2")
	    		  .param("name","paul")
	    		  .param("email","paul@gmail.com")
	    		  )
	              .andDo(print())
	              .andExpect(status().isOk())
	              .andDo(document("index",
	                       responseFields(
	                               fieldWithPath("id").description("id"),
	                               fieldWithPath("name").description("name"),
	                               fieldWithPath("email").description("email")
	                              )
	              ));
	  }
}
