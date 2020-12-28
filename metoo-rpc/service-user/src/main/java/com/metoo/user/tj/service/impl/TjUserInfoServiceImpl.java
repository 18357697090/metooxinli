package com.metoo.user.tj.service.impl;

import com.metoo.user.tj.dao.entity.TjUserInfo;
import com.metoo.user.tj.dao.mapper.TjUserInfoMapper;
import com.metoo.user.tj.service.TjUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户个人信息表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class TjUserInfoServiceImpl extends ServiceImpl<TjUserInfoMapper, TjUserInfo> implements TjUserInfoService {

}
