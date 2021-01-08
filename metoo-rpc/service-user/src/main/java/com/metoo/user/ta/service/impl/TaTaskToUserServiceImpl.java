package com.metoo.user.ta.service.impl;

import com.metoo.user.ta.dao.entity.TaTaskToUser;
import com.metoo.user.ta.dao.mapper.TaTaskToUserMapper;
import com.metoo.user.ta.service.TaTaskToUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务指定用户 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2021-01-08
 */
@Service
public class TaTaskToUserServiceImpl extends ServiceImpl<TaTaskToUserMapper, TaTaskToUser> implements TaTaskToUserService {

}
