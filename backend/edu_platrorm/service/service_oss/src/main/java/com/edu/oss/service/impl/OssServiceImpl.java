package com.edu.oss.service.impl;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.edu.oss.service.OssService;
import com.edu.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {

            String endpoint = ConstantPropertiesUtil.END_POINT;
            String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
            String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
            String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

            try {
                // 创建OSSClient实例。
                OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
                InputStream inputStream = file.getInputStream();
                String fileName = file.getOriginalFilename();

                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                fileName = uuid + fileName;

                String datePath = new DateTime().toString("yyyy/MM/dd");
                fileName = datePath+"/"+fileName;
                // 创建PutObjectRequest对象。
                ossClient.putObject(bucketName,fileName,inputStream);
//                ossClient.putObject(bucketName,fileName , inputStream);
                ossClient.shutdown();
                String url = "http://" + bucketName + "." + endpoint + "/" + fileName;
                return url;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }


}
