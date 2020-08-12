package com.sg.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/7/13
 */
@Data
public class YyPageRequest implements Serializable {
    private static final long serialVersionUID = -9053426259832178066L;

    /**
     * 第几页
     */
    private Integer page = 1;

    /**
     * 每页多少条
     */
    private Integer rows = 10;

    /**
     * 是否导出excel
     */
    private boolean export = false;
}
