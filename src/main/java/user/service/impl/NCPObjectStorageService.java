package user.service.impl;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import spring.conf.NCPConfiguration;
import user.service.ObjectStorageService;

@Service
public class NCPObjectStorageService implements ObjectStorageService {
    final AmazonS3 amazonS3;
    
    public NCPObjectStorageService(NCPConfiguration ncpConfiguration) {
        amazonS3 = AmazonS3ClientBuilder
              .standard()
              .withEndpointConfiguration(
                      new AwsClientBuilder.EndpointConfiguration(
                              ncpConfiguration.getEndPoint(), ncpConfiguration.getRegionName()))
              .withCredentials(
                      new AWSStaticCredentialsProvider(
                              new BasicAWSCredentials(
                                      ncpConfiguration.getAccessKey(), ncpConfiguration.getSecretKey())))
              .build();
     }
    
    @Override
    public String uploadFile(String bucketName, String directoryPath, MultipartFile multipartFile) {
        try(InputStream inputStream = multipartFile.getInputStream()) {
            String imageFileName = UUID.randomUUID().toString();
            
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucketName, 
                                         directoryPath + imageFileName, 
                                         inputStream, 
                                         objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            
            amazonS3.putObject(putObjectRequest);
            
            return imageFileName;
            
        } catch(Exception e) {
            throw new RuntimeException("파일 업로드 실패");
        }
    }
    
    @Override
    public void deleteFile(String bucketName, String directoryFilePath, String imageFileName) {
        amazonS3.deleteObject(bucketName, directoryFilePath + imageFileName);
    }
    
    @Override
    public void selectDeleteFile(String bucketName, String directoryFilePaths) {
        amazonS3.deleteObject(bucketName, directoryFilePaths);        
    }
}
