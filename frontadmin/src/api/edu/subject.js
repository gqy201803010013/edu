import request from '@/utils/request'

export default {
  //1 课程分类列表
  //current当前页 limit每页记录数 teacherQuery条件对象
  getSubjectList() {
      return request({
          url: `/eduservice/subject/getAllSubject`,
          method: 'get',
        })
  }
}
