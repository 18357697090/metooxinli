package com.metoo.business.bu.service.impl;

import com.metoo.business.bu.dao.entity.BuUserList;
import com.metoo.business.bu.dao.mapper.BuUserListMapper;
import com.metoo.business.bu.dao.repository.BuUserListRepository;
import com.metoo.business.bu.service.BuUserListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoo.pojo.bu.model.BuUserListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author loongya
 * @since 2021-01-06
 */
@Service
public class BuUserListServiceImpl extends ServiceImpl<BuUserListMapper, BuUserList> implements BuUserListService {

    @Autowired
    private BuUserListRepository buUserListRepository;

    @Override
    public List<BuUserList> findUserList(Integer page) {
        return buUserListRepository.findUserList(page);
    }

    @Override
    public long userCount() {
        return buUserListRepository.count();
    }
}
