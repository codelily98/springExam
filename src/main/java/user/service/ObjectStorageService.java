package user.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface ObjectStorageService {

    public String uploadFile(String bucketName, String string, MultipartFile multipartFile);

    public void deleteFile(String bucketName, String string, String imageFileName);

    public void selectDeleteFile(String bucketName, String string);
    
}
