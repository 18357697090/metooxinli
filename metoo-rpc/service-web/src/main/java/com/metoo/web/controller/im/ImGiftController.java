package com.metoo.web.controller.im;


import com.loongya.core.util.RE;
import com.metoo.api.im.ImGiftApi;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 礼物表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/im/im-gift")
public class ImGiftController {

    @DubboReference
    private ImGiftApi imGiftApi;

    @ApiOperation("送礼物")
    @GetMapping("/givingGift")
    public RE givingGift(@RequestHeader("UID")Integer uid, Integer acceptedId, Integer giftId, String number){
        return imGiftApi.givingGift(uid,acceptedId,giftId,number);
    }

    @ApiOperation("获取礼物列表")
    @GetMapping("/getGiftList")
    public RE getGiftList(){
        return imGiftApi.getGiftList();
    }


}
