package user.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserUploadDTO {
    private int seq;
    private String imageName;
    private String imageContent;
    private String imageFileName;           //UUID이름
    private String imageOriginalFileName;   //이미지 원래 이름
    
    @Override
    public String toString() {
        return imageName + "\t" + imageContent + "\t" + imageFileName + "\t" + imageOriginalFileName;
    }
}
