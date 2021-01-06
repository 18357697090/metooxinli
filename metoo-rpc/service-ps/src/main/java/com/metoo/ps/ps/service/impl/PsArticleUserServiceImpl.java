package com.metoo.ps.ps.service.impl;

import com.metoo.ps.ps.dao.entity.PsArticleUser;
import com.metoo.ps.ps.dao.mapper.PsArticleUserMapper;
import com.metoo.ps.ps.dao.repository.PsArticleUserRepository;
import com.metoo.ps.ps.service.PsArticleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户查看心理文章记录表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsArticleUserServiceImpl extends ServiceImpl<PsArticleUserMapper, PsArticleUser> implements PsArticleUserService {

    @Autowired
    private PsArticleUserRepository psArticleUserRepository;

    @Override
    public PsArticleUser findByUid(Integer uid) {
        return psArticleUserRepository.findByUid(uid);
    }
}
