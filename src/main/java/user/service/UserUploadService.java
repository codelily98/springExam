package user.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import user.bean.UserUploadDTO;

@Component
public interface UserUploadService {

    public void upload(List<UserUploadDTO> imgList);

    public List<UserUploadDTO> uploadList();

    public UserUploadDTO uploadView(int seq);

    public UserUploadDTO uploadUpdateForm(int seq);

    public void imgUpdate(UserUploadDTO userUploadDTO, MultipartFile multipartFile);

    public List<String> deleteSelect(int parseInt);

    public void deleteImageDB(int seq);

}
