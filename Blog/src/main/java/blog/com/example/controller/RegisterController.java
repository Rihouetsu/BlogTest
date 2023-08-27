package blog.com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.example.services.UserService;

@Controller
public class RegisterController {
	// @Autowiredを使用してUserServiceクラスを読み込んで下さい
	@Autowired
	private UserService userService;

	/*
	 * URLが /register getRegisterPage() メソッドは、"admin_register"
	 * というビュー（画面）を表示することで、登録画面を表示できるようにソースを書いて下さい
	 */
	@GetMapping("/register")
	public String getRegisterPage() {
		return "register.html";
	}

	/*
	 * 登録画面から入力内容を受け取って もし、userテーブル内に登録したメールアドレスが存在しなかった場合、テーブルに保存処理をして
	 * ログイン画面を表示し、保存出来ない場合は、登録画面を表示するようにして下さい
	 */
	@PostMapping("/register/process")
	public String register(@RequestParam String userName, @RequestParam String userEmail,
			@RequestParam String password) {
		if (userService.createUser(userName, userEmail, password)) {
			return "login.html";
		} else {
			return "register.html";
		}
	}

}