package com.edu.oss.controller;

import com.edu.commonutils.R;
import com.edu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
//@CrossOrigin(origins = "http://localhost:9528", allowCredentials = "true")
public class OssController {
    @Autowired
    private OssService ossService;
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
