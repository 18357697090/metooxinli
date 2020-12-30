package com.metoo.web.controller.ps;


import com.loongya.core.util.RE;
import com.metoo.api.ps.PsCommentApi;
import com.metoo.tools.CommentsTool;
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
@RequestMapping("/ps/psComment")
public class PsCommentController {

    @DubboReference
    private PsCommentApi psCommentApi;


    @ApiOperation("量表评论")
    @PostMapping("/comment")
    public RE comment(@RequestBody CommentsTool commentsTool, @RequestHeader("UID") Integer uid){
        return psCommentApi.comment(commentsTool, uid);

    }

}
