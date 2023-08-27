//package blog.com.example.controller;
//
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import blog.com.example.model.entity.UserEntity;
//import blog.com.example.services.BlogService;
//import blog.com.example.services.UserService;
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class BlogRegisterController {
//	// @Autowiredを使用してUserServiceクラスを読み込んで下さい
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private HttpSession session;
//
//	@Autowired
//	private BlogService blogService;
//
//	/*
//	 * URLが /register getRegisterPage() メソッドは、"admin_register"
//	 * というビュー（画面）を表示することで、登録画面を表示できるようにソースを書いて下さい
//	 */
//	@GetMapping("/blog/register")
//	public String getBlogRegisterPage() {
//		return "blog-register.html";
//	}
//
//	@PostMapping("/blog/register/insert")
//	public String insertBlog(@RequestParam String title, @RequestParam String date, @RequestParam String category,
//			@RequestParam String detail, @RequestParam MultipartFile image) {
//		UserEntity user = (UserEntity) session.getAttribute("user");
//		Long userId = user.getUserId();
//		if (user == null) {
//			return "redirect:/login";
//		} else {
//			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
//					+ image.getOriginalFilename();
//			try {
//				/**
//				 * ファイルを実際にサーバー上に保存するための処理を行っています。Files.copy()メソッドを使用して、
//				 * blogImageオブジェクトから入力ストリームを取得し、指定されたパスにファイルをコピー。
//				 * Path.of()メソッドを使用して、保存先のパスを指定している。
//				 * 保存先のパスは、"src/main/resources/static/blog-img/"というディレクトリの中に
//			 * 、fileNameで指定されたファイル名で保存される。。
//				 **/
//				Files.copy(image.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			if (blogService.createBlog(title, category, fileName, detail, userId)) {
//				return "redirect:/blog/list";
//			} else {
//				return "redirect:/blog/register";
//			}
//		}
//	}
//
//}