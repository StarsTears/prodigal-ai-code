package com.prodigal.aicode.common;

import lombok.Data;

/**
 * @program: prodigal-ai-code
 * @author: Lang
 * @description: 请求封装类
 **/
@Data
public class PageRequest {
    /**
     * 当前页号
     */
    private long current = 1;
    /**
     * 页面大小
     */
    private long pageSize=10;
    /**
     * 排序字段
     */
    private String sortField;
    /**
     * 排序顺寻(默认降序)
     */
    private String sortOrder = "descend";
}
