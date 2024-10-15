package user.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sound.midi.Sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import user.bean.UserUploadDTO;
import user.service.ObjectStorageService;
import user.service.UserService;
import user.service.UserUploadService;

@Controller
@RequestMapping("user")
public class UserUploadController {
    @Autowired
    private ObjectStorageService objectStorageService;
    @Autowired
    private UserUploadService userUploadService;

    private String bucketName = "bitcamp-9th-bucket-97";
    
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
        String imageFileName;
        File file;
        String result = "";
        
        //파일들을 모아서(list) DB로 보내기
        List<UserUploadDTO> imgList = new ArrayList<UserUploadDTO>();
        
        for(MultipartFile multipartFile : list) {
            //imageFileName = UUID.randomUUID().toString();
            
            //Naver Cloud Plateform (Object Storage) 연결
            imageFileName = objectStorageService.uploadFile(bucketName, "storage/", multipartFile);
            
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
            imageDTO.setImageFileName(imageFileName);
            imageDTO.setImageOriginalFileName(imageOriginalFileName);
            
            imgList.add(imageDTO);
        }
        //DB로 연동
        userUploadService.upload(imgList);
        
        return result;
    }
    
    @RequestMapping(value = "uploadList", method = RequestMethod.GET)
    public ModelAndView uploadList() {
        ModelAndView modelAndView = new ModelAndView();
        List<UserUploadDTO> list = new ArrayList<UserUploadDTO>();
        list = userUploadService.uploadList();        
        modelAndView.addObject("list", list);
        modelAndView.setViewName("upload/uploadList");
        return modelAndView;
    }
    
    @RequestMapping(value = "uploadView", method = RequestMethod.GET) 
    public String uploadView(@RequestParam("seq") String seq, Model model) {
        UserUploadDTO userUploadDTO = userUploadService.uploadView(Integer.parseInt(seq));
        model.addAttribute("userUploadDTO", userUploadDTO);
        return "upload/uploadView";
    }
    
    @RequestMapping(value = "uploadUpdateForm", method = RequestMethod.GET)
    public String uploadUpdateForm(@RequestParam("seq") String seq, Model model) {
        UserUploadDTO userUploadDTO = userUploadService.uploadUpdateForm(Integer.parseInt(seq));
        model.addAttribute("userUploadDTO", userUploadDTO);
        return "upload/uploadUpdateForm";
    }
    
    @RequestMapping(value="uploadUpdate", method=RequestMethod.POST, produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String ncpUpdate(@ModelAttribute UserUploadDTO userUploadDTO,
                    @RequestParam("multipartFile") MultipartFile multipartFile) {
       userUploadService.imgUpdate(userUploadDTO, multipartFile);
       
       return "이미지 수정 완료";
    }
    
    @RequestMapping(value = "deleteImage", method = RequestMethod.POST)
    @ResponseBody
    public int deleteImage(@RequestParam("seq[]") List<Integer> checked) {
        int count = 0;
        
        List<String> imageFileNames = new ArrayList<>();
        
        try {
            for(int seq : checked) {
                List<String> imageFileName = userUploadService.deleteSelect(seq);
                
                imageFileNames.addAll(imageFileName);
            }

            if(!imageFileNames.isEmpty()) {
                for (String fileName : imageFileNames) {
                    objectStorageService.selectDeleteFile(bucketName, "storage/" + fileName);
                }
            } else {
                System.out.println("imageFileNames 비워져있음(오류) : " + imageFileNames);
            }
            
            for(int seq : checked) {
                userUploadService.deleteImageDB(seq);
                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return count;
        }        
        return count;
    }
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

