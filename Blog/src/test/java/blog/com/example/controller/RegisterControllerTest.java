package blog.com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
public class RegisterControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	
	@MockBean
	private UserService userService;
	
	@BeforeEach
	//事前のデータ準備
	public void prepareData() {
		UserEntity userEntity = new UserEntity("lpy","lpy@gmail.com","123456");
		when(userService.createUser(eq("lpy"), eq("lpy@gmail.com"),eq("123456"))).thenReturn(true);
		when(userService.createUser(eq(""), eq(""),eq("123456"))).thenReturn(false);
		when(userService.createUser(eq("lpy"), eq(""),eq("123456"))).thenReturn(false);
		when(userService.createUser(eq("lpy"), eq("test@gmail.com"),eq("123456"))).thenReturn(false);
		when(userService.createUser(eq("lpy"), eq("lpy@gmail.com"),eq("1234abcd"))).thenReturn(false);
		when(userService.createUser(eq("lpy"), eq(""),eq(""))).thenReturn(false);
		when(userService.createUser(eq("Alice"), eq("lpy@gmail.com"),eq(""))).thenReturn(false);
		when(userService.createUser(eq(""), eq("lpy@gmail.com"),eq(""))).thenReturn(false);
		when(userService.createUser(eq("Alice"), eq("test@gmail.com"),eq("1234abcd"))).thenReturn(false);
	}
	
	//登録ページが正しく取得できるかのテスト
		@Test
		public void testGetUserRegisterPage_Succeed()throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.get("/register");
			mockMvc.perform(request)
			.andExpect(view().name("register.html"));		
		}	
		//1:登録が成功した場合のテスト
		@Test
		public void testRegister_Succeed() throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.post("/register/process")
					.param("userName","lpy")
					.param("userEmail","lpy@gmail.com")
					.param("password","123456");
			
			 mockMvc.perform(request)
				.andExpect(view().name("login.html"));
			
}
	//登録が失敗した場合のテスト
		
		//2:BlankUsernameAndUserEmail 
		
		@Test
		public void testRegister_Unsucceed_BlankUsernameAndUserEmail() throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.post("/register/process")				
					.param("userName","")
					.param("userEmail","")
			        .param("password","123456");
			   		
			 mockMvc.perform(request)
				.andExpect(view().name("register.html"));
			
}
		//3: BlankEmail
		@Test
		public void testRegister_Unsucceed_BlankEmail() throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.post("/register/process")				
					.param("userName","lpy")
					.param("userEmail","")
			        .param("password","123456");
			   		
			 mockMvc.perform(request)
				.andExpect(view().name("register.html"));
			
}
		//4:WrongEmail
		@Test
		public void testRegister_Unsucceed_WrongEmail() throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.post("/register/process")				
					.param("userName","lpy")
					.param("userEmail","test@gmail.com")
			        .param("password","123456");
			   		
			 mockMvc.perform(request)
				.andExpect(view().name("register.html"));
			
}
		//5:WrongPassword
		@Test
		public void testRegister_Unsucceed_WrongPassword() throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.post("/register/process")				
					.param("userName","lpy")
					.param("userEmail","lpy@gmail.com")
			        .param("password","1234abcd");
			   		
			 mockMvc.perform(request)
				.andExpect(view().name("register.html"));
			
}
		//6:BlankEmailAndBlankPassword
		@Test
		public void testRegister_Unsucceed_BlankEmailAndBlankPassword() throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.post("/register/process")				
					.param("userName","lpy")
					.param("userEmail","")
			        .param("password","");
			   		
			 mockMvc.perform(request)
				.andExpect(view().name("register.html"));
			
}
		//7:WrongNameAndBlankPassword
		@Test
		public void testRegister_Unsucceed_WrongNameAndBlankPassword() throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.post("/register/process")				
					.param("userName","Alice")
					.param("userEmail","lpy@gmail.com")
			        .param("password","");
			   		
			 mockMvc.perform(request)
				.andExpect(view().name("register.html"));
			
}
		//8:BlankNameAndBlankPassword
		@Test
		public void testRegister_Unsucceed_BlankNameAndBlankPassword() throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.post("/register/process")				
					.param("userName","")
					.param("userEmail","lpy@gmail.com")
			        .param("password","");
			   		
			 mockMvc.perform(request)
				.andExpect(view().name("register.html"));
			
}
	//9:WrongNameAndWrongEmailAndWrongPassword
		@Test
		public void testRegister_Unsucceed_WrongNameAndWrongEmailAndWrongPassword() throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.post("/register/process")				
					.param("userName","Alice")
					.param("userEmail","test@gmail.com")
			        .param("password","1234abcd");
			   		
			 mockMvc.perform(request)
				.andExpect(view().name("register.html"));
			
}	
		
}

