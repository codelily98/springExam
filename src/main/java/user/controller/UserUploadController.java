package user.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import user.bean.UserUploadDTO;
import user.service.UserService;

@Controller
@RequestMapping("user")
public class UserUploadController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="uploadForm", method = RequestMethod.GET)
    public String uploadForm() {
        return "upload/uploadForm";
    }
    
    @RequestMapping(value = "uploadFormAjax", method = RequestMethod.GET)
    public String uploadFormAjax() {
        return "upload/uploadFormAjax";
    }
    
    //멀티 이미지 업로드[한번에 하나 또는 여러개] 
    @RequestMapping(value="uploads", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String uploads(@ModelAttribute UserUploadDTO userUploadDTO, 
                         @RequestParam("multipartFile[]") List<MultipartFile> list, 
                         HttpSession session) {
        //실제 폴더 위치
        String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
        System.out.println("실제 폴더 = " + filePath);
        
        String imageOriginalFileName;
        File file;
        String result = "";
        
        //파일들을 모아서(list) DB로 보내기
        List<UserUploadDTO> imgList = new ArrayList<UserUploadDTO>();
        
        for(MultipartFile multipartFile : list) {
            imageOriginalFileName = multipartFile.getOriginalFilename();
            file = new File(filePath, imageOriginalFileName);
            
            try {
                multipartFile.transferTo(file);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            
            result += "<span>"
                    + "<img src='/spring/storage/"
                    + imageOriginalFileName //URLEncoder.encode(imageOriginalFileName, "UTF-8") produces 사용시 안써도 됨.
                    + "' alt='"
                    + imageOriginalFileName
                    + "' width='300' height='300'/>"
                    + "</span>";
            
            UserUploadDTO imageDTO = new UserUploadDTO();
            imageDTO.setImageName(userUploadDTO.getImageName());
            imageDTO.setImageContent(userUploadDTO.getImageContent());
            //imageDTO.setImageFileName(userUploadDTO.getImageFileName());
            imageDTO.setImageFileName(""); //아직 UUID 안했기 떄문에 ""로 넣는다.
            imageDTO.setImageOriginalFileName(userUploadDTO.getImageOriginalFileName());
            
            imgList.add(imageDTO);
        }
        //DB로 연동
        
        
        return result;
    }
    
//    //싱글 이미지 업로드
//    @RequestMapping(value="upload", method = RequestMethod.POST)
//    @ResponseBody
//    public String upload(@ModelAttribute UserUploadDTO userUploadDTO, 
//                         @RequestParam MultipartFile multipartFile, 
//                         HttpSession session) {
//        //실제 폴더 위치
//        String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
//        System.out.println("실제 폴더 = " + filePath);
//        
//        String imageOriginalFileName = multipartFile.getOriginalFilename();
//        
//        File file = new File(filePath, imageOriginalFileName);
//        
//        try {
//            multipartFile.transferTo(file);
//        } catch (IllegalStateException | IOException e) {
//            e.printStackTrace();
//        }
//        
//        String result = "<span>"
//                      + "<img src='/spring/storage/" + imageOriginalFileName + "' alt='" + imageOriginalFileName + "' width='300' height='300'/>"
//                      + "</span>";
//        
//        System.out.println(userUploadDTO.getImageName() 
//                         + userUploadDTO.getImageContent()
//                         + userUploadDTO.getImageFileName()
//                         + userUploadDTO.getImageOriginalFileName());
//        
//        userUploadDTO.setImageOriginalFileName(imageOriginalFileName);
//        
//        return result;
//    }
//    
//    //멀티 이미지 업로드[한번에 하나씩]
//    @RequestMapping(value="uploadoneselects", method = RequestMethod.POST)
//    @ResponseBody
//    public String uploads(@ModelAttribute UserUploadDTO userUploadDTO, 
//                         @RequestParam MultipartFile[] multipartFile, 
//                         HttpSession session) {
//        //실제 폴더 위치
//        String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
//        System.out.println("실제 폴더 = " + filePath);
//        
//        String imageOriginalFileName;
//        File file;
//        String result;
//        
//        //----------------------------------------------------------------
//        imageOriginalFileName = multipartFile[0].getOriginalFilename();
//        file = new File(filePath, imageOriginalFileName);
//        
//        try {
//            multipartFile[0].transferTo(file);
//        } catch (IllegalStateException | IOException e) {
//            e.printStackTrace();
//        }
//        
//        result = "<span>"
//               + "<img src='/spring/storage/" + imageOriginalFileName + "' alt='" + imageOriginalFileName + "' width='300' height='300'/>"
//               + "</span>";
//        //----------------------------------------------------------------
//        //----------------------------------------------------------------
//        imageOriginalFileName = multipartFile[1].getOriginalFilename();
//        file = new File(filePath, imageOriginalFileName);
//        
//        try {
//            multipartFile[1].transferTo(file);
//        } catch (IllegalStateException | IOException e) {
//            e.printStackTrace();
//        }
//        
//        result += "<span>"
//                + "<img src='/spring/storage/" + imageOriginalFileName + "' alt='" + imageOriginalFileName + "' width='300' height='300'/>"
//                + "</span>";
//        //----------------------------------------------------------------
//        
//        System.out.println(userUploadDTO.getImageName() 
//                         + userUploadDTO.getImageContent()
//                         + userUploadDTO.getImageFileName()
//                         + userUploadDTO.getImageOriginalFileName());
//        
//        userUploadDTO.setImageOriginalFileName(imageOriginalFileName);
//        
//        return result;
//    }
}
