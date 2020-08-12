package com.sg.domain.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/7/14
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class SalesExcelRequest extends YyPageRequest implements Serializable {

    private static final long serialVersionUID = -6066886295587903107L;

    /**
     * 时间 yyyy
     */
    private String date;

    /**
     * 类型{"单次票_ONCE","时段票_PERIOD","练习票_EXERCISE","陪同票_COMPANY","活动_EVENT","期课_QIKE","课_COURSE"}
     */
    private String type;

}
