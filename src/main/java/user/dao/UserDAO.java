package user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import user.bean.UserDTO;

@Mapper
public interface UserDAO {

	public String userExistId(String id);
	
	public void userWrite(UserDTO userDTO);

	public List<UserDTO> userList(Map<String, Integer> map);
	
	public int getTotalA();
	
	public int userUpdate(Map<String, Object> userMap);

	public void userDelete(Map<String, String> deleteMap);

    public List<UserDTO> selectUser(String id);
    
    

}