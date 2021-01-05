package com.metoo.ps.ps.api;

import com.metoo.api.ps.PsAnswerApi;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 心理测量题目答案表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class PsAnswerApiImpl implements PsAnswerApi {

}
