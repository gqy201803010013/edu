package com.edu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.commonutils.R;
import com.edu.eduservice.entity.EduTeacher;
import com.edu.eduservice.entity.vo.TeacherQuery;
import com.edu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2024-05-10
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
//@CrossOrigin(origins = {"http://localhost:9528", "http://localhost:3000"}, allowCredentials = "true")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    //逻辑删除
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(
            @ApiParam(name="id", value="讲师ID", required=true)
            @PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        if(b){
            return R.ok();
        }else{
            return R.error();
        }
    }


//分页查询
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageList/{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        eduTeacherService.page(pageParam, null);
        List<EduTeacher> records = pageParam.getRecords(); //数据list集合
        long total = pageParam.getTotal(); //总记录数
        return R.ok().data("total", total).data("rows", records);
    }

    //条件查询
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
//        int i = 10/0;
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level) ) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        eduTeacherService.page(pageTeacher,wrapper);

        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合
        long total = pageTeacher.getTotal(); //总记录数
        return R.ok().data("total", total).data("rows", records);
    }

    //添加讲师
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //根据id查询
    @GetMapping("selectTeacherById/{id}")
    public R selectTeacherById(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("teacher", teacher);

    }

//    修改
    @PostMapping("UpdateTeacher")
    public R UpdateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }
//
}

