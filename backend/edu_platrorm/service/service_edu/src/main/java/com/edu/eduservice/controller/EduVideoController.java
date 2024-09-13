package com.edu.eduservice.controller;


import com.alibaba.excel.util.StringUtils;
import com.edu.commonutils.R;
import com.edu.eduservice.client.VodClient;
import com.edu.eduservice.entity.EduVideo;
import com.edu.eduservice.service.EduVideoService;
import com.edu.servicebase.exceptionhandler.GuliException;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2024-07-10
 */
@RestController
@RequestMapping("/eduservice/video")
//@CrossOrigin(origins = {"http://localhost:9528", "http://localhost:3000"}, allowCredentials = "true")
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;
    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }

    //删除小节
    // TODO 后面这个方法需要完善：删除小节时候，同时把里面视频删除
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
//        EduVideo eduvideo = videoService.getById(id);
//        String videoSourceId = eduvideo.getVideoSourceId();
//        if(!StringUtils.isEmpty(videoSourceId)){
//            vodClient.removeAlyVideo(videoSourceId);
//        }
        //根据小节id获取视频id，调用方法实现视频删除
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节里面是否有视频id
        if(!StringUtils.isEmpty(videoSourceId)) {
            //根据视频id，远程调用实现视频删除
            R result = vodClient.removeAlyVideo(videoSourceId);
            if(result.getCode() == 20001) {
                throw new GuliException(20001,"删除视频失败，熔断器...");
            }
        }
        videoService.removeById(id);
        return R.ok();
    }

    //修改小节 TODO
}

