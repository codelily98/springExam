package user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import user.bean.UserDTO;
import user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @RequestMapping(value="writeForm", method = RequestMethod.GET)
    public String writeForm() {
        return "user/writeForm";
    }
    
    @RequestMapping(value="userExistId", method = RequestMethod.POST)
    @ResponseBody
    public String userExistId(String id) {
        return userService.userExistId(id);
    }
    
    @RequestMapping(value = "write", method = RequestMethod.POST)
    @ResponseBody //return값 없이 js로 갈 때
    public void write(@ModelAttribute UserDTO userDTO) {
        userService.write(userDTO);
    }
    
    @RequestMapping(value="list", method = RequestMethod.GET)
    public String list(@RequestParam(required = false, defaultValue = "1") String pg, Model model) {
        Map<String, Object> pagingMap = userService.list(pg);
        model.addAttribute("list", pagingMap.get("list"));
        model.addAttribute("pg", pagingMap.get("pg"));
        model.addAttribute("userPaging", pagingMap.get("userPaging"));
        return "user/list";
    }
    
    @RequestMapping(value="updateForm", method = RequestMethod.GET)
    public String updateForm(@RequestParam("id") String id, @RequestParam("pg") String pg, Model model) {
        List<UserDTO> list = userService.selectUser(id);
        model.addAttribute("list", list);
        model.addAttribute("pg", Integer.parseInt(pg));
        return "user/updateForm";
    }
    
    @RequestMapping(value="update", method = RequestMethod.POST)
    @ResponseBody
    public void update(@RequestParam("id") String id, @RequestParam("pwd") String pwd, @RequestParam("name") String name, Model model) {
        Map<String, Object> userMap = new HashedMap<String, Object>();
        userMap.put("id", id);
        userMap.put("name", name);
        userMap.put("pwd", pwd);
        userService.update(userMap); 
    }
    
    @RequestMapping(value="deleteForm", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteForm(@RequestParam("id") String id, @RequestParam("pg") String pg) {
        Map<String, Object> responseMap = new HashMap<>();
        
        List<UserDTO> userList = userService.selectUser(id);
        
        if (userList != null) {
            responseMap.put("status", "success");
            responseMap.put("list", userList);
            responseMap.put("id", id);
            responseMap.put("pg", pg);
        } else {
            responseMap.put("status", "fail");
            responseMap.put("message", "사용자를 찾을 수 없습니다.");
        }
        
        return responseMap;
    }
    
    @RequestMapping(value="deleteFormView", method = RequestMethod.GET)
    public String deleteFormView(@RequestParam("id") String id, @RequestParam("pg") String pg, Model model) {
        List<UserDTO> list = userService.selectUser(id);
        model.addAttribute("list", list);
        model.addAttribute("pg", Integer.parseInt(pg));
        return "user/deleteForm";
    }
    
    @RequestMapping(value="delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(@RequestParam("id") String id, @RequestParam("pwd") String pwd, @RequestParam("checkPwd") String checkPwd) {
        Map<String, String> responseMap = new HashMap<>(); // 응답을 담을 Map
        if (pwd.equals(checkPwd)) {
            Map<String, String> deleteMap = new HashMap<>();
            deleteMap.put("id", id);
            deleteMap.put("pwd", pwd);
            
            userService.delete(deleteMap);
            //자동으로 json으로 변환해서 return한다. 따라서 ajax에서 dataType을 json으로 설정하고, Dependency를 설정한다.
            responseMap.put("status", "success");
        } else {
            responseMap.put("status", "fail");
            responseMap.put("message", "비밀번호가 일치하지 않습니다.");
        }
        return responseMap;
    }
}
