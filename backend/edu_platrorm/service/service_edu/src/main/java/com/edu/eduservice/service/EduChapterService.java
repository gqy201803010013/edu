package com.edu.eduservice.service;

import com.edu.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2024-07-10
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoById(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
