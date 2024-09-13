package com.edu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.eduservice.entity.EduSubject;
import com.edu.eduservice.entity.excel.SubjectData;
import com.edu.eduservice.service.EduSubjectService;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    public EduSubjectService subjectService;
    public SubjectExcelListener() { }
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null){
            System.out.println("error");
        }

        EduSubject existOneeduSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if(existOneeduSubject == null){
            //没有相同的一级分类，进行添加
            existOneeduSubject = new EduSubject();
            existOneeduSubject.setParentId("0");
            existOneeduSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneeduSubject);
        }
        String pid = existOneeduSubject.getId();
        EduSubject existTwoeduSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(),pid );
        if(existTwoeduSubject == null){
            //没有相同的一级分类，进行添加
            existTwoeduSubject = new EduSubject();
            existTwoeduSubject.setParentId(pid);
            existTwoeduSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoeduSubject);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject; // 如果返回一个对象，说明已经存在一个同名的一级分类
    }

    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject; // 如果返回一个对象，说明已经存在一个同名的二级分类
    }
}
