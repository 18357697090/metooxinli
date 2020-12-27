package com.metoo.user.tj.service.impl;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.user.tj.dao.entity.TjUserAccount;
import com.metoo.user.tj.dao.mapper.TjUserAccountMapper;
import com.metoo.user.tj.dao.repository.TjUserAccountRepository;
import com.metoo.user.tj.service.TjUserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class TjUserAccountServiceImpl extends ServiceImpl<TjUserAccountMapper, TjUserAccount> implements TjUserAccountService {

    @Resource
    private TjUserAccountRepository tjUserAccountRepository;

    @Override
    public RE getUserAccountByUserId(Integer userId) {
        List<TjUserAccount> byUserId2 = tjUserAccountRepository.findByUserId2(userId);
        if(OU.isBlack(byUserId2)){
            return RE.serviceFail("空");
        }
        return RE.ok(byUserId2);
    }
}
