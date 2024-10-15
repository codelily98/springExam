package user.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import user.bean.UserDTO;
import user.bean.UserUploadDTO;

@Component
public interface UserService {
    
    public String userExistId(String id);
    public void write(UserDTO userDTO);
    public Map<String, Object> list(String pg);
    public int update(Map<String, Object> userMap);
    public List<UserDTO> selectUser(String id);
    public void delete(Map<String, String> deleteMap);
}
