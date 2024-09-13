package com.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.eduservice.entity.EduChapter;
import com.edu.eduservice.entity.EduVideo;
import com.edu.eduservice.entity.chapter.ChapterVo;
import com.edu.eduservice.entity.chapter.VideoVo;
import com.edu.eduservice.mapper.EduChapterMapper;
import com.edu.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.eduservice.service.EduVideoService;
import com.edu.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2024-07-10
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoById(String courseId) {

        //根据课程id查询所有章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);

        //根据课程id查询所有小结
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> eduVideoList = eduVideoService.list(wrapperVideo);

        //创建list集合，用于封装最终数据
        List<ChapterVo> finallList = new ArrayList<>();

        //遍历查询章节list集合
        for (int i = 0; i < eduChapterList.size(); i++) {

            EduChapter eduChapter = eduChapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finallList.add(chapterVo);

            //创建集合，用于封装章节的小结
            List<VideoVo> videoVos = new ArrayList<>();
            //遍历查询小结list,进行封装
            for (int j = 0; j < eduVideoList.size(); j++) {
                //得到每个小结
                EduVideo eduVideo = eduVideoList.get(j);
                if(eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoVos.add(videoVo);
                }


            }
            chapterVo.setChildren(videoVos);
        }

        return finallList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",chapterId);
        int count = eduVideoService.count(eduVideoQueryWrapper);
        if(count>0){
            throw new GuliException(20001,"删除失败");
        }else{
            int i = baseMapper.deleteById(chapterId);
            return i>0;
        }
    }
    //2 根据课程id删除章节
    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
