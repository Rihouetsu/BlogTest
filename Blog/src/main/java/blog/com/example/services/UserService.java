package blog.com.example.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.example.model.dao.UserDao;
import blog.com.example.model.entity.UserEntity;

@Service
public class UserService {
	@Autowired
	private UserDao userdao;
	//戻り値がboolean メソッド名createAdmin
	public boolean createUser(String name,String email,String password) {
		
		//もしメールアドレスで検索をかけてnullだったら保存をしてtrueを返す
		//そうでない場合はfalseを返すメソッドを書いて下さい
		if(userdao.findByUserName(name)==null) {
			userdao.save(new UserEntity(name,email,password));
			return true;
		}else {
			return false;
		}
	}
	
	
	public UserEntity checkLogin(String email,String password) {
		UserEntity userEntity = userdao.findByUserEmailAndPassword(email,password);
		if(userEntity == null) {
			return null;
		}else {
			return userEntity;
		}
	}


	public Object createUser(String eq, String eq2) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
