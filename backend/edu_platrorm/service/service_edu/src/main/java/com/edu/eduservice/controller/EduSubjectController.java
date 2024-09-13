package com.edu.eduservice.controller;


import com.edu.commonutils.R;
import com.edu.eduservice.entity.subject.OneSubject;
import com.edu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2024-07-06
 */
@RestController
@RequestMapping("/eduservice/subject")
//@CrossOrigin(allowCredentials = "true")
//@CrossOrigin(origins = {"http://localhost:9528", "http://localhost:3000"}, allowCredentials = "true")
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }
}

