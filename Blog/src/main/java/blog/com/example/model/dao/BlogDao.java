package blog.com.example.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.com.example.model.entity.BlogEntity;



public interface BlogDao extends JpaRepository<BlogEntity, Long> {
	//データを保存するinsert文に該当するメソッドを書く
			//UserEntityを引数として受け取ってUserEntityの内容を保存して保存した結果を返す
			 BlogEntity save(BlogEntity blogEntity);
			
			//一覧の取得　SELECT * FROM blogs;
				List<BlogEntity>findAll();
			 
			//一行のデータを取得する　SELECT * FROM WHERE user_id = ?
				BlogEntity findByBlogId(Long userId);
		  
		   //ブログのtitleを探す
				BlogEntity findByBlogTitle(String blogTitle);
}
