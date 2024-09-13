package com.edu.eduservice.mapper;

import com.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.eduservice.entity.frontvo.CourseWebVo;
import com.edu.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2024-07-10
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
        public CoursePublishVo getPublishCourseInfo(String courseId);

        //根据课程id，编写sql语句查询课程信息
        CourseWebVo getBaseCourseInfo(String courseId);
}
