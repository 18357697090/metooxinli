package com.metoo.web.controller.xy;


import com.loongya.core.util.RE;
import com.metoo.api.xy.XyCreateCityApi;
import com.metoo.pojo.xy.vo.XyCreateCityVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 申请加入城消息记录表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/xy/xyCreateCity")
public class XyCreateCityController {

    @DubboReference
    private XyCreateCityApi xyCreateCityApi;

    /**
     * 建城申请表
     * 1: 判断是否积分足够
     * 2: 判断国家是否是自己的,如果是,直接建城,如果不是,加入申请表.
     * 3: 冻结积分, 新增积分明细
     * @param uid
     * @param vo
     * @return
     */
    @PostMapping("/joinCity")
    public RE joinCity(@RequestHeader("UID")Integer uid, @RequestBody XyCreateCityVo vo){
        return xyCreateCityApi.joinCity(uid, vo);

    }

    /**
     * 国主审核建城申请列表
     * 如果同意,直接建城,如果拒绝,返还积分
     */
}
