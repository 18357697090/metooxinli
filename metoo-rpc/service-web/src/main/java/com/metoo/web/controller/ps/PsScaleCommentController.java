package com.metoo.web.controller.ps;


import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsScaleCommentApi;
import com.metoo.pojo.login.enums.AuthEnum;
import com.metoo.pojo.ps.vo.PsScaleCommentVo;
import com.metoo.tools.CommentsTool;
import com.metoo.web.config.auth.ThreadLocal;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 心理测量量表用户评论表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psScaleComment")
public class PsScaleCommentController {

    @DubboReference
    private PsScaleCommentApi psScaleCommentApi;


    /**
     * 新增量表评论
     * @param vo
     * @return
     */
    @ApiOperation("新增量表评论")
    @PostMapping("/addComment")
    public RE addComment(PsScaleCommentVo vo){
        Integer userId = ThreadLocal.getUserId();
        if(OU.isBlack(userId)){
            return RE.fail(AuthEnum.LOGIN_TIMEOUT);
        }
        vo.setUserId(userId);
        return psScaleCommentApi.addComment(vo);

    }

}
