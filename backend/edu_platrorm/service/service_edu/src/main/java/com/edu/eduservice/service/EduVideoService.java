package com.edu.eduservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.eduservice.entity.EduChapter;
import com.edu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2024-07-10
 */
public interface EduVideoService extends IService<EduVideo> {


    void removeVideoByCourseId(String courseId);
}
