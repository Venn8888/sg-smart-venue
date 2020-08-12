package com.sg.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 过闸记录
 * @author: liu weichen
 * @create: 2020/7/9
 */
@Data
@ExcelTarget(value = "GateRecordExcel")
public class CrossingRecordExcel implements Serializable {
    private static final long serialVersionUID = -72096970297460139L;
    // 过闸时间	会员姓名	会员手机号	产品类型	进出类型	票码
    /*
    SELECT
    mm.`name`, mm.udf1 phone, ssr.`record_time` recordTime, tmp.type,
            (CASE tmp.type WHEN 'COMPANY' THEN '陪同票' WHEN 'ONCE' THEN '单次票' WHEN 'EXERCISE' THEN '练习票'
    WHEN 'EVENT' THEN '活动票' WHEN 'COURSE' THEN '课程票' END) productType,
    IF(ssr.`in_or_out` = '1', '进', '出') inOrOut, ssr.`udf1` tocketCode
    FROM sk_scan_record ssr
    LEFT JOIN (
            SELECT ttd.doc_id, tt.`type` FROM tk_ticket_doc ttd LEFT JOIN tk_ticket tt ON ttd.`ticket_id` = tt.`ticket_id`
            UNION
            SELECT `doc_item_id` doc_id, 'EVENT' TYPE FROM te_even_doc_item
    ) tmp ON ssr.`instance_id` = tmp.doc_id
    LEFT JOIN mm_member mm ON ssr.`udf2` = mm.`member_id`
    */
    @Excel(name = "过闸时间",  orderNum = "1", exportFormat = "yyyy-MM-dd HH:mm:ss", width = 20)
    private Date recordTime;

    @Excel(name = "会员姓名",  orderNum = "2", width = 20)
    private String name;

    @Excel(name = "会员手机号",  orderNum = "3", width = 15)
    private String phone;

    @Excel(name = "产品类型",  orderNum = "4", replace = {"陪同票_COMPANY", "单次票_ONCE", "练习票_EXERCISE", "活动票_EVENT", "课程票_COURSE"})
    private String productType;

    @Excel(name = "进出类型",  orderNum = "5")
    private String inOrout;

    @Excel(name = "票码",  orderNum = "6", width = 60)
    private String ticketCode;
}
