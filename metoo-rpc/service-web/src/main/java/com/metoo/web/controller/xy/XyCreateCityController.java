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

    //加入国度
    @GetMapping("/joinCity")
    public RE joinCity(@RequestHeader("UID")Integer uid, @RequestBody XyCreateCityVo vo){
        return xyCreateCityApi.joinCity(uid, vo);

    }


    //查看请求
//    @GetMapping()
//    public ReturnMessage (){
//    ReturnMessage returnMessage = new ReturnMessage();
//
//    return returnMessage;
//}

}
