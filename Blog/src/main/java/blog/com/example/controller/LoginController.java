package blog.com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.example.model.entity.UserEntity;
import blog.com.example.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;

	@GetMapping("/login")
	public String getLoginPage() {
		return "login.html";
	}
	
	@PostMapping("/login/process")
	public String login(@RequestParam String userEmail,@RequestParam String password){
		UserEntity user = userService.checkLogin(userEmail, password);
		//もしパスワードやメールアドレスが異なければnullがuserに入る
		//正しければユーザー情報が取得できる
		if(user == null) {
			return "redirect:/login";
		}else {
			//セッションを使用してサーバーにデータを格納
			session.setAttribute("user",user);
			return "redirect:/blog/list";
			
		}
	}
	
}
