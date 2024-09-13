package com.edu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.eduservice.entity.EduSubject;
import com.edu.eduservice.entity.excel.SubjectData;
import com.edu.eduservice.entity.subject.OneSubject;
import com.edu.eduservice.entity.subject.TwoSubject;
import com.edu.eduservice.listener.SubjectExcelListener;
import com.edu.eduservice.mapper.EduSubjectMapper;
import com.edu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2024-07-06
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //查询所有一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id",0);
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        //查询所有二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id",0);
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //创建集合，存放最终数据、t
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //封装一级分类
        for (int i = 0; i < oneSubjectList.size(); i++) {
            EduSubject eduSubject = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();
            //复制eduSubject中的值到oneSubject
            BeanUtils.copyProperties(eduSubject,oneSubject);
            finalSubjectList.add(oneSubject);

            //封装二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            for (int j = 0; j < twoSubjectList.size(); j++) {
                EduSubject tSubject = twoSubjectList.get(j);
                if(oneSubject.getId().equals(tSubject.getParentId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }

            }
            oneSubject.setChild(twoFinalSubjectList);
        }

        //封装二级分类

        return finalSubjectList;
    }

}
