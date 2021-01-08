package com.metoo.user.in.service.impl;

import com.metoo.user.in.dao.entity.InDict;
import com.metoo.user.in.dao.mapper.InDictMapper;
import com.metoo.user.in.dao.repository.InDictRepository;
import com.metoo.user.in.service.InDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2021-01-08
 */
@Service
public class InDictServiceImpl extends ServiceImpl<InDictMapper, InDict> implements InDictService {

    @Autowired
    private InDictRepository inDictRepository;

    @Override
    public List<InDict> findAllByPkey(String levelDict) {
        return inDictRepository.findAllByPkey(levelDict);
    }
}
