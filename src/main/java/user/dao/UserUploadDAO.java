package user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import user.bean.UserUploadDTO;

@Mapper
public interface UserUploadDAO {

    public void imageUpload(List<UserUploadDTO> imgList);

    public List<UserUploadDTO> uploadList();

    public UserUploadDTO uploadView(int seq);

    public UserUploadDTO uploadUpdateForm(int seq);

    public String getImageFileName(int seq);

    public void uploadUpdate(UserUploadDTO userUploadDTO);

    public List<String> deleteSelect(int seq);

    public void deleteImageDB(int seq);
    
}
