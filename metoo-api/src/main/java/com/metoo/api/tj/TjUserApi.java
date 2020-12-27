package com.metoo.api.tj;

import com.loongya.core.util.RE;

public interface TjUserApi {
    RE getUserById(Integer id);

    RE getList();
}
