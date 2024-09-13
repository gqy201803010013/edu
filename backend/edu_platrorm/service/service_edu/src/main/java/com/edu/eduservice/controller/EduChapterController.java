package com.edu.eduservice.controller;


import com.edu.commonutils.R;
import com.edu.eduservice.entity.EduChapter;
import com.edu.eduservice.entity.chapter.ChapterVo;
import com.edu.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2024-07-10
 */
@RestController
@RequestMapping("/eduservice/chapter")
//@CrossOrigin(origins = {"http://localhost:9528", "http://localhost:3000"}, allowCredentials = "true")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list = eduChapterService.getChapterVideoById(courseId);
        return R.ok().data("allChapterVideo",list);
    }

    //添加章节
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return  R.ok();
    }

    //根据id查询章节
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return R.ok().data("eduChapter",eduChapter);
    }

    //修改章节
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return  R.ok();
    }

//    删除章节
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        boolean flag = eduChapterService.deleteChapter(chapterId);
        if(flag){
            return R.ok();
        }else{
            return  R.error();
        }
    }
}

