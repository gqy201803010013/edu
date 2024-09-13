import request from '@/utils/request'

export default {
  //1 添加课程信息
  
  addCourseInfo(courseInfo) {
      return request({
          url: `/eduservice/edu-course/addCourseInfo`,
          method: 'post',
          data:courseInfo
        })
  },
  getListTeacher(){
      return request({
        url: `/eduservice/teacher/findAll`,
        method: 'get'
      })
  },

  //根据id查询课程基本信息
  getCourseInfoById(id){
    return request({
      url: `/eduservice/edu-course/getCourseInfo/${id}`,
      method: 'get'
    })
  },
  updateCourseInfo(courseInfo){
    return request({
      url: `/eduservice/edu-course/updateCourseInfo`,
      method: 'post',
      data:courseInfo
    })
  },
  //课程确认信息显示
  getPublihCourseInfo(id) {
    return request({
        url: '/eduservice/edu-course/getPublishCourseInfo/'+id,
        method: 'get'
      })
  },
  // //课程最终发布
  publihCourse(id) {
    return request({
        url: '/eduservice/edu-course/publishCourse/'+id,
        method: 'post'
      })
  },
  //TODO 课程列表
  //课程最终发布
  getListCourse() {
      return request({
          url: '/eduservice/edu-course',
          method: 'get'
        })
  },
  deleteListCourse(courseId) {
    return request({
        url: '/eduservice/edu-course/'+courseId,
        method: 'delete'
      })
  }
}
