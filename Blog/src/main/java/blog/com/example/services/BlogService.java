package blog.com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.example.model.dao.BlogDao;
import blog.com.example.model.entity.BlogEntity;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;

	public List<BlogEntity> selectAll(Long blogId) {
		if (blogId == null) {
			return null;
		} else {
			return blogDao.findAll();
		}
	}

	// 詳細な情報を記録する
	public boolean createBlog(String blogTitle, String categoryName, String blogImage, String article, Long userId) {
		BlogEntity blogList = blogDao.findByBlogTitle(blogTitle);
		if (blogList == null) {
			blogDao.save(new BlogEntity(blogTitle, categoryName, blogImage, article, userId));
			return true;
		} else {
			return false;
		}

	}

	// blog_idを使ってテーブルから１行だけデータを取得する
	public BlogEntity getBlogPost(Long blogId) {
		// もしコントローラクラスから渡ってきたblogIDがnullだったら
		// null（検索できませんでした）
		if (blogId == null) {
			return null;
		} else {
			// 検索の結果をController側に知らせる BlogEnitity型で
			// データをコントローラクラスに送る
			return blogDao.findByBlogId(blogId);
		}
	}
   //更新処理
	public boolean editBlog(Long blogId, String blogTitle, String categoryName, String blogImage, String article) {
		//更新前のデータベースの内容を取得する
		BlogEntity blogList = blogDao.findByBlogId(blogId);
		if (blogList == null) {
			return false;
		} else {
			blogList.setBlogTitle(blogTitle);
			blogList.setCategoryName(categoryName);
			blogList.setBlogImage(blogImage);
			blogList.setArticle(article);
			blogDao.save(blogList);
			return true;
		}
	}
    //削除処理
	public boolean deleteBlog(Long blogId) {
		if (blogId == null) {
			return false;
		} else {
			blogDao.deleteById(blogId);
			return true;
		}
	}
}
