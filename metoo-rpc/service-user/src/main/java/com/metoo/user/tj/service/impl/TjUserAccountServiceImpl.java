package com.metoo.user.tj.service.impl;

import com.metoo.user.tj.dao.entity.TjUserAccount;
import com.metoo.user.tj.dao.mapper.TjUserAccountMapper;
import com.metoo.user.tj.service.TjUserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class TjUserAccountServiceImpl extends ServiceImpl<TjUserAccountMapper, TjUserAccount> implements TjUserAccountService {

}
