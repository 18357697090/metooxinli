package com.metoo.api.tj;

import com.loongya.core.util.RE;

/**
 * <p>
 * 用户密保问题 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TjSecretGuardApi {

    RE findSecretGuard(String username);
}
