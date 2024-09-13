package com.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.eduservice.entity.frontvo.CourseFrontVo;
import com.edu.eduservice.entity.frontvo.CourseWebVo;
import com.edu.eduservice.entity.vo.CourseInfoVo;
import com.edu.eduservice.entity.vo.CoursePublishVo;
import java.util.List;
import java.util.Map;
/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2024-07-10
 */
public interface EduCourseService extends IService<EduCourse> {


    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    //1 条件查询带分页查询课程前台
    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

//    //根据课程id，编写sql语句查询课程信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
