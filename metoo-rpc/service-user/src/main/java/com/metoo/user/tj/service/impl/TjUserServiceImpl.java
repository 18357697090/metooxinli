package com.metoo.user.tj.service.impl;

import com.metoo.user.tj.dao.entity.TjUser;
import com.metoo.user.tj.dao.mapper.TjUserMapper;
import com.metoo.user.tj.service.TjUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class TjUserServiceImpl extends ServiceImpl<TjUserMapper, TjUser> implements TjUserService {

}
