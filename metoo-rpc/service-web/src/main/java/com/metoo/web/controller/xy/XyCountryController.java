package com.metoo.web.controller.xy;


import com.loongya.core.util.AssertUtils;
import com.loongya.core.util.RE;
import com.metoo.api.xy.XyCountryApi;
import com.metoo.pojo.old.vo.BuildCountryDTO;
import com.metoo.pojo.xy.vo.XyCountryVo;
import com.metoo.web.config.auth.ThreadLocal;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 国度表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/xy/xyCountry")
public class XyCountryController {

    @DubboReference
    private XyCountryApi xyCountryApi;

    /**
     * 创建国家接口
     * 1:检测有没有权限创建国家
     * 2:建成积分够不够
     * 3: 新增国家
     * 4: 新增主城
     * 5: 新增我的聊天室数据
     * 6: 扣除积分,添加积分明细
     * @param vo
     * @return
     */
    @ApiOperation("创建国家")
    @PostMapping("/buildCountry")
    public RE buildCountry(XyCountryVo vo){
        AssertUtils.checkParam(vo.getName());
        vo.setUid(ThreadLocal.getUserId());
        return xyCountryApi.buildCountry(vo);
    }

    /**
     * 点击国家按钮,查看是否有权限进入
     */

    /**
     * 国家和部落列表
     *
     */
    @ApiOperation("获取国家列表")
    @PostMapping("/getCountry")
    public RE getCountry(@RequestHeader("UID")Integer uid, Integer raceId){
        return xyCountryApi.getCountry(uid, raceId);
    }

}
