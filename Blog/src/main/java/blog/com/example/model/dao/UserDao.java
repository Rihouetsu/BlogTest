package blog.com.example.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.com.example.model.entity.UserEntity;


public interface UserDao extends JpaRepository<UserEntity, Long> {
	//データを保存するinsert文に該当するメソッドを書く
		//UserEntityを引数として受け取ってUserEntityの内容を保存して保存した結果を返す
		 UserEntity save(UserEntity userEntity);
		
		//userNameの内容を受け取ってUserEntityを返すメソッド
		//Where user_name = ?
		 UserEntity findByUserName(String userName);
		 
		 //Select*form user Where user_name=?And user_password=?
				

		UserEntity findByUserEmailAndPassword(String email, String password);

		
		 
	

}
