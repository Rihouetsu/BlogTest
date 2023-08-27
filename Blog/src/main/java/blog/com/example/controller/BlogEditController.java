//package blog.com.example.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import blog.com.example.model.entity.BlogEntity;
//import blog.com.example.model.entity.UserEntity;
//import blog.com.example.services.BlogService;
//import blog.com.example.services.UserService;
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class BlogEditController {
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
//	private BlogEntity blog;
//
//	/*
//	 * URLが /register getRegisterPage() メソッドは、"user_register"
//	 * というビュー（画面）を表示することで、登録画面を表示できるようにソースを書いて下さい
//	 */
//	@GetMapping("/blog/editPage")
//	public String getEditBlog(Model model) {
//		String blogId = (String) session.getAttribute("blogId");
//		blog = blogService.selectAll(Long.parseLong(blogId.replaceAll(",", ""))).get(0);
//		model.addAttribute("blog", blog);
//		return "blog-edit.html";
//	}
//
//	@PostMapping("/blog/editPage/update")
//	public String updateBlog(@RequestParam String blogTitle, @RequestParam String date,
//			@RequestParam String categoryName, @RequestParam String article) {
//		UserEntity user = (UserEntity) session.getAttribute("user");
//		if (user == null) {
//			return "redirect:/login";
//		} else {
//			try {
//				blogService.editBlog(blog.getBlogId(), blogTitle, categoryName, blog.getBlogImage(), article);
//				return "redirect:/blog/list";
//			} catch (Exception e) {				
//				return "/";
//			}
//		}
//	}
//	
//    //削除する処理
////	@GetMapping("/blog/editPage/delete")
////	public String deleteBlog() {
////		UserEntity user = (UserEntity) session.getAttribute("user");
////		if (user == null) {
////			return "redirect:/login";
////		} else {
////			blogService.deleteBlog(blog.getBlogId());
////			return "redirect:/blog/list";
////		}
////	}
//
//}