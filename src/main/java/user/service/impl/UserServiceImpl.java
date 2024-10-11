package user.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.bean.UserDTO;
import user.bean.UserPaging;
import user.dao.UserDAO;
import user.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserPaging userPaging;
    
    @Override
    public String userExistId(String id) {
        String exist = userDAO.userExistId(id);
        if(exist == null) {
            return "non_exist";
        }
        else {
            return "exist";
        }
    }
    
    @Override
    public void write(UserDTO userDTO) {
        userDAO.userWrite(userDTO);
    }
    
    @Override
    public Map<String, Object> list(String pg) {
        int page = Integer.parseInt(pg);
        int endNum = 5;
        int startNum = (page * endNum) - endNum;
        
        Map<String, Integer> map = new HashedMap<String, Integer>();
        map.put("startNum", startNum);
        map.put("endNum", endNum);
        
        List<UserDTO> list = userDAO.userList(map);
        
        //페이징 처리(a태그)
        int totalA = userDAO.getTotalA();
        
        userPaging.setTotalA(totalA);
        userPaging.setCurrentPage(page);
        userPaging.setPageBlock(3);
        userPaging.setPageSize(endNum);
        userPaging.makePagingHTML();
        
        Map<String, Object> pageingMap = new HashedMap<String, Object>();
        pageingMap.put("list", list);
        pageingMap.put("pg", page);
        pageingMap.put("userPaging", userPaging);
        
        return pageingMap;
    }
    
    @Override
    public List<UserDTO> selectUser(String id) {
        return userDAO.selectUser(id);
    }
    
    @Override
    public int update(Map<String, Object> userMap) {
        return userDAO.userUpdate(userMap);
    }
    
    @Override
    public void delete(Map<String, String> deleteMap) {
        userDAO.userDelete(deleteMap);        
    }
}