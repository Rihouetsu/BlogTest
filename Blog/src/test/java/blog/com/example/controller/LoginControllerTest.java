package blog.com.example.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import blog.com.example.model.entity.UserEntity;
import blog.com.example.services.UserService;
import jakarta.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	
	@MockBean
	private UserService userService;
	
	@BeforeEach
	//事前のデータ準備
	public void prepareData() {
		UserEntity userEntity = new UserEntity("lpy","lpy@gmail.com","123456");
		when(userService.checkLogin(eq("lpy@gmail.com"),eq("123456"))).thenReturn(userEntity);
		when(userService.checkLogin(eq("test@gmail.com"),eq("123456"))).thenReturn(null);
		when(userService.checkLogin(eq(""),eq("123456"))).thenReturn(null);
      	when(userService.checkLogin(eq("lpy@gmail.com"),eq("1234abcd"))).thenReturn(null);
		when(userService.checkLogin(eq(""),eq(""))).thenReturn(null);
		when(userService.checkLogin(eq("test@gmail.com"),eq("1234abcd"))).thenReturn(null);
	}
	//ログインページが正しく取得できるかのテスト
	@Test
	public void testGetUserLoginPage_Succeed()throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/login");
		mockMvc.perform(request)
		.andExpect(view().name("login.html"));		
	}	
	//ログインが成功した場合のテスト
		@Test
		public void testLogin_Successful()throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.post("/login/process")
			        .param("userEmail","lpy@gmail.com")
			        .param("password","123456");	 	
			MvcResult result = mockMvc.perform(request)
			//リクエストの実行結果を返すためのメソッドがandReturn()
			.andExpect(redirectedUrl("/blog/list")).andReturn();
			
			//セッションの取得
			HttpSession session = result.getRequest().getSession();
			
			//セッションがきちんと設定出来ているかの確認テスト
			UserEntity userList = (UserEntity)session.getAttribute("user");
			assertNotNull(userList);
			assertEquals("lpy",userList.getUserName());
			assertEquals("lpy@gmail.com",userList.getUserEmail());
			assertEquals("123456",userList.getPassword());
		}
		//ログインが失敗した場合のテスト Wrong userEmail
		@Test
		public void testLogin_Unsucceed_WrongEmail()throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.post("/login/process")
					.param("userEmail", "test@gmail.com")
					.param("password", "123456");
			MvcResult result =mockMvc.perform(request).andExpect(redirectedUrl("/login")).andReturn();
			
			HttpSession session = result.getRequest().getSession();
			
			UserEntity userList = (UserEntity) session.getAttribute("user");
			
			assertNull(userList);
		}
		//空白メール　正しいパスワード
		@Test
		public void testLogin_Unsucceed_BlankEmail()throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.post("/login/process")
					.param("userEmail", "")
					.param("password", "123456");
			MvcResult result =mockMvc.perform(request).andExpect(redirectedUrl("/login")).andReturn();
			
			HttpSession session = result.getRequest().getSession();
			
			UserEntity userList = (UserEntity) session.getAttribute("user");
			
			assertNull(userList);
		}
		//RightEmail   WrongPassword   		
		@Test
		public void testLogin_Unsucceed_WrongPassword()throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.post("/login/process")
					.param("userEmail", "lpy@gmail.com")
					.param("password", "1234abcd");
			MvcResult result =mockMvc.perform(request).andExpect(redirectedUrl("/login")).andReturn();
			
			HttpSession session = result.getRequest().getSession();
			
			UserEntity userList = (UserEntity) session.getAttribute("user");
			
			assertNull(userList);
		}
		//RightEmail BlankPassword
		@Test
		public void testLogin_Unsucceed_BlankPassword()throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.post("/login/process")
					.param("userEmail", "lpy@gmail.com")
					.param("password", "");
			MvcResult result =mockMvc.perform(request).andExpect(redirectedUrl("/login")).andReturn();
			
			HttpSession session = result.getRequest().getSession();
			
			UserEntity userList = (UserEntity) session.getAttribute("user");
			
			assertNull(userList);
		}
		
		//BlankEmail   BlankPassword
		@Test
		public void testLogin_Unsucceed_BlankEmailAndBlankPassword()throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.post("/login/process")
					.param("userEmail", "")
					.param("password", "");
			MvcResult result =mockMvc.perform(request).andExpect(redirectedUrl("/login")).andReturn();
			
			HttpSession session = result.getRequest().getSession();
			
			UserEntity userList = (UserEntity) session.getAttribute("user");
			
			assertNull(userList);
		}
		//WrongEmail  WrongPassword
		@Test
		public void testLogin_Unsucceed_WrongEmailAndWrongPassword()throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.post("/login/process")
					.param("userEmail", "test@gmail.com")
					.param("password", "1234abcd");
			MvcResult result =mockMvc.perform(request).andExpect(redirectedUrl("/login")).andReturn();
			
			HttpSession session = result.getRequest().getSession();
			
			UserEntity userList = (UserEntity) session.getAttribute("user");
			
			assertNull(userList);
		}
		
		
		}
		

