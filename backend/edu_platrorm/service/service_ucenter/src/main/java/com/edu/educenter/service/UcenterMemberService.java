package com.edu.educenter.service;

import com.edu.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2024-09-01
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getOpenIdMember(String openid);

    Integer countRegisterDay(String day);
}
