package com.metoo.ps.ps.service.impl;

import com.metoo.ps.ps.dao.entity.PsCapsuleImg;
import com.metoo.ps.ps.dao.mapper.PsCapsuleImgMapper;
import com.metoo.ps.ps.dao.repository.PsCapsuleImgRepository;
import com.metoo.ps.ps.service.PsCapsuleImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author loongya
 * @since 2021-01-07
 */
@Service
public class PsCapsuleImgServiceImpl extends ServiceImpl<PsCapsuleImgMapper, PsCapsuleImg> implements PsCapsuleImgService {

    @Autowired
    private PsCapsuleImgRepository psCapsuleImgRepository;


    @Override
    public List<PsCapsuleImg> findImgListByCapId(Integer id) {
        return psCapsuleImgRepository.getAllByCapId(id);
    }
}
