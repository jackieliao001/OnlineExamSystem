package com.rabbiter.oes.common.resp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageRequest {
    /**
     * 当前页码
     */
    private long current = 0;
    /**
     * 每页显示条数
     */
    private long size = 20;
}
