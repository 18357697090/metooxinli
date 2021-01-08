package com.metoo.web.controller.nr;


import com.loongya.core.util.AssertUtils;
import com.loongya.core.util.RE;
import com.metoo.api.order.NrBackpackApi;
import com.metoo.pojo.nr.vo.NrGoodsVo;
import com.metoo.pojo.order.vo.NrBackpackVo;
import com.metoo.web.config.auth.ThreadLocal;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户背包商品表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/nr/nrBackpackIndex")
public class NrBackpackIndexController {

    @DubboReference
    private NrBackpackApi nrBackpackApi;

    /**
     * 我的道具列表
     * @param vo
     * @return
     */
    @GetMapping("/myBackpackList")
    public RE myBackpackList(NrBackpackVo vo){
         vo.setUserId(ThreadLocal.getUserId());
        return nrBackpackApi.myBackpackList(vo);
    }

}
