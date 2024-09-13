<template>
    <div class="app-container">
        
        <h2 style="text-align: center;">发布新课程</h2>
        <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px;">
            <el-step title="填写课程基本信息"/>
            <el-step title="创建课程大纲"/>
            <el-step title="最终发布"/>
        </el-steps>
        <el-form label-width="120px">
            <el-form-item label="课程标题">
                <el-input v-model="courseInfo.title" placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"/>
            </el-form-item>
             <!-- 所属分类 TODO -->
             <el-form-item label="课程分类">
                <el-select
                 v-model="courseInfo.subjectParentId"
                 placeholder="一级分类" @change="subjectLevelOneChanged">
                 <el-option
                 v-for="subject in subjectOneList"
                 :key="subject.id"
                 :label="subject.title"
                 :value="subject.id"/>
              </el-select>

              <!-- 二级分类 -->
            <el-select v-model="courseInfo.subjectId" placeholder="二级分类" >
                <el-option
                v-for="subject in subjectTwoList"
                :key="subject.id"
                :label="subject.title"
                :value="subject.id"/>
            </el-select>

            </el-form-item>

             <!-- 课程讲师 TODO -->
             <el-form-item label="课程讲师">
                <el-select
                 v-model="courseInfo.teacherId"
                 placeholder="请选择">
                 <el-option
                 v-for="teacher in teacherList"
                 :key="teacher.id"
                 :label="teacher.name"
                 :value="teacher.id"/>

                </el-select>

            </el-form-item>

            <el-form-item label="总课时">
                <el-input-number :min="0" v-model="courseInfo.lessonNum" controls-position="right" placeholder="请填写课程的总课时数"/>
            </el-form-item>

            <!-- 课程简介 TODO -->
            <!-- <el-form-item label="课程简介">
                <el-input v-model="courseInfo.description" placeholder="课程简介"/>
            </el-form-item> -->
            <!-- 课程简介-->
            <el-form-item label="课程简介">
                <tinymce :height="300" v-model="courseInfo.description"/>
            </el-form-item>

            <!-- 课程封面 TODO -->
            <el-form-item label="课程封面">
                <el-upload
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload" 
                    :action="BASE_API+'/eduoss/fileoss'"
                    class="avatar-uploader">    
                    <!-- <img :src="courseInfo.cover"> -->
                    <img :src="courseInfo.cover" class="default-image" alt="default image">
                </el-upload>
            </el-form-item>

            <el-form-item label="课程价格">
                <el-input-number :min="0" v-model="courseInfo.price" controls-position="right" placeholder="免费课程请设置为0元"/> 
            </el-form-item>

            <el-form-item>
                <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存并下一步</el-button>
            </el-form-item>

        </el-form>
        
    </div>
</template>

    <script>
    import course from '@/api/edu/course'
    import subject from '@/api/edu/subject'
    import Tinymce from '@/components/Tinymce'
        export default {
        components: { Tinymce },
        data() {
        return {
             saveBtnDisabled: false, // 保存按钮是否禁用
             courseInfo:{
                title: '',
                subjectId: '',//二级分类id
                subjectParentId:'',//一级分类id
                teacherId: '',
                lessonNum: 0,
                description: '',
                cover: '/static/02.jpg',
                price: 0
            },
            BASE_API: process.env.BASE_API ,// 接口API地址,
            teacherList: [],
            subjectOneList: [], //一级课程分类
            subjectTwoList: [], //二级课程分类
            courseId:''
        }
     },
    created() {
        //判断
        if(this.$route.params && this.$route.params.id) {
                this.courseId = this.$route.params.id
                //根据课程id查询
                this.init()
        }else{
                //初始化所有讲师 
                this.getListTeacher()
                this.getOneSubject()
        }
        
     },
     watch: {
        $route(to, from) {
        console.log('watch $route')
        this.init()
      }
    },
    methods: {
        init(){
            //判断是否有id值
            if (this.$route.params && this.$route.params.id) {
            const id = this.$route.params.id
            //有id则查询信息
                this.getInfo()  
            }else{
                //返回默认值
                this.courseInfo = {
                    title: '',
                    subjectId: '',//二级分类id
                    subjectParentId:'',//一级分类id
                    teacherId: '',
                    lessonNum: 0,
                    description: '',
                    cover: '/static/02.jpg',
                    price: 0
            }
            }
      },
        getInfo(){
            course.getCourseInfoById(this.courseId)
                .then(response =>{
                    this.courseInfo = response.data.courseInfoVo
                    // 初始化分类列表
                    subject.getSubjectList()
                        .then(responseSubject => {
                            this.subjectOneList = responseSubject.data.list
                            for (let i = 0; i < this.subjectOneList.length; i++) {
                                if (this.subjectOneList[i].id == this.courseInfo.subjectParentId) {
                                    this.subjectTwoList = this.subjectOneList[i].child
                                }
                            }
                        })
                    //初始化所有讲师
                    this.getListTeacher()
                })
        },
        //上传成功后
        handleAvatarSuccess(res,file){
            this.courseInfo.cover = res.data.url
        },
        //上传之前
        beforeAvatarUpload(file){
            const isJPG = file.type === 'image/jpeg' 
            const isLt2M = file.size / 1024 / 1024 < 2
            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!')
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!')
            }
            return isJPG && isLt2M
        },
        //点击一级分类，获取所有 id = value 的二级分类
        subjectLevelOneChanged(value){
            for(var i = 0; i<this.subjectOneList.length; i++){
                var sub = this.subjectOneList[i]
                if(sub.id === value){
                    this.subjectTwoList = sub.child;
                    this.courseInfo.subjectId = ''
                }
            }
        },
        //获取所有一级分类
        getOneSubject(){
            subject.getSubjectList()
                .then(response=>{
                    this.subjectOneList = response.data.list
                })
        },
        getListTeacher(){
            course.getListTeacher()
                .then(response=>{
                    this.teacherList = response.data.items
                })
        },
        addCourse(){
            course.addCourseInfo(this.courseInfo)
                .then(response =>{
                    this.$message({
                    type: 'success',
                    message: '添加课程信息成功!' 
                });
                    this.$router.push({ path: '/course/chapter/'+response.data.courseId })
                })
        },
        updateCourse(){
            course.updateCourseInfo(this.courseInfo)
                .then(response =>{
                    this.$message({
                    type: 'success',
                    message: '修改课程信息成功!' 
                });
                    this.$router.push({ path: '/course/chapter/'+this.courseId })
                })
        },
        saveOrUpdate() {
            if(!this.courseInfo.id){
                this.addCourse()
            }else{
                this.updateCourse()
            }
            
        }
        }
    }
    </script>
    <style scoped>
    .default-image {
      width: 300px; /* 设置宽度，例如200px */
      height: 200px; /* 设置高度，例如200px */
    }
    .tinymce-container {
    line-height: 29px;
    }
    </style>
