package com.sg.framework.constants;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/6/2
 */
public class YyConstants {

    public static final String HTTP_METHOD_POST = "POST";

    public static final String HTTP_METHOD_GET = "GET";

    /**
     * 验证成功返回结果code
     */
    public static final int VALID_SUCCESS = 0;
    /**
     * 验证失败返回结果code
     */
    public static final int VALID_FAIL = -1;
    /**
     * 验证失败, 票码时长超时，返回结果code
     */
    public static final int VALID_FAIL_TICKET_USE_TIME_OUT = 1504;

    /**
     * 返回结果code
     */
    public static final String RESULT_CODE = "SUCCESS";

    /**
     * 订单类型--全部订单
     */
    public static final String DOC_TYPE_ALL = "0";

    /**
     * 订单类型--课程
     */
    public static final String DOC_TYPE_COUPON = "1";

    /**
     * 订单类型--票
     */
    public static final String DOC_TYPE_TICKET = "2";

    /**
     * 订单类型--活动
     */
    public static final String DOC_TYPE_EVENT = "3";

    /**
     * 订单类型--缴费
     */
    public static final String DOC_TYPE_CHARGE = "4";

    /**
     * 票编码--识别词
     */
    public static final String DOC_CODE_TICKET = "TICKET";

    /**
     * 活动编码--识别词
     */
    public static final String DOC_CODE_EVENT = "EVENT";

    /**
     * 课程编码--识别词
     */
    public static final String DOC_CODE_COURSE = "COURSE";

    /**
     * 缴费编码--识别词
     */
    public static final String DOC_CODE_CHARGE = "CHARGE";

    /**
     * 各状态说明
     * <p>
     * 卡卷状态(1:未使用,2:订单锁定,3:已使用,4:核销（付款后又退款）,5:已过期)
     * <p>
     * 总订单状态(1:待付款 2:已付款 3:已取消 4:退款申请中 5:已退款 )
     * <p>
     * 票实例状态(0:初始化 1:退场,2:延迟, 3:可用,4:不可用)
     * <p>
     * 活动实例状态(1:有效 2:待付款 3：退款 4：结束)
     * <p>
     * 支付订单状态(1:待支付,2:已支付)
     * <p>
     * 退款订单状态(0:待审核,1:待退款,2:已退款,3:审核不通过-驳回)
     * <p>
     * 支付订单类型(1：收入,2:支出)
     * <p>智慧场馆Smart venue
     * 状态--1
     */
    public static final String STATUS_ONE = "1";

    /**
     * 状态--2
     */
    public static final String STATUS_TWO = "2";

    /**
     * 状态--3
     */
    public static final String STATUS_THREE = "3";

    /**
     * 状态--4
     */
    public static final String STATUS_FOUR = "4";

    /**
     * 状态--5
     */
    public static final String STATUS_FIVE = "5";

    /**
     * 状态--0
     */
    public static final String STATUS_ZERO = "0";

    /**
     * 微信支付appid对应配置key
     */
    public static final String WX_PAY_APP_ID = "wx.pay.app.id";

    /**
     * 微信支付商户id对应配置key
     */
    public static final String WX_PAY_MCH_ID = "wx.pay.mch.id.";

    /**
     * 微信支付key对应配置key
     */
    public static final String WX_PAY_KEY = "wx.pay.key.";

    /**
     * 微信支付证书路径对应配置key
     */
    public static final String WX_PAY_CERT_PATH = "wx.pay.cert.path.";

    /**
     * 微信支付商户id绑定的门店id对应配置key
     */
    public static final String WX_PAY_STORE_ID = "wx.pay.store.id.";

    /**
     * 微信支付回调url对应配置key
     */
    public static final String WX_PAY_CALLBACK_URL = "wx.pay.callback.url";

    /**
     * 微信支付退款回调url对应配置key
     */
    public static final String WX_PAY_REFUND_CALLBACK_URL = "wx.pay.refund.callback.url";

    /**
     * 票类型--单次票
     */
    public static final String TICKET_TYPE_ONCE = "ONCE";

    /**
     * 票类型--陪同票
     */
    public static final String TICKET_TYPE_COMPANY = "COMPANY";

    /**
     * 票类型--时段票
     */
    public static final String TICKET_TYPE_PERIOD = "PERIOD";

    public static final String TICKET_TYPE_RETIME = "RETIME";

    /**
     * 票类型--练习票
     */
    public static final String TICKET_TYPE_EXERCISE = "EXERCISE";

    /**
     * 票类型--活动票
     */
    public static final String TICKET_TYPE_EVENT = "EVENT";

    /**
     * 票类型--课程票
     */
    public static final String TICKET_TYPE_COURSE = "COURSE";

    /**
     * 借还类型--借
     */
    public static final String SCENE_HARDWARE_BORROW = "0";

    /**
     * 借还类型--还
     */
    public static final String SCENE_HARDWARE_RETURN = "1";

    /**
     * 出闸类型--借
     */
    public static final String SCENE_HARDWARE_IN = "1";

    /**
     * 出闸类型--还
     */
    public static final String SCENE_HARDWARE_OUT = "0";

    public static final String SCENE_TYPE_BORROW = "BORROW";

    public static final String SCENE_TYPE_ACCESS = "ACCESS";

    /**
     * 设备类型--闸机
     */
    public static final Integer HARDWARE_TYPE_GATE = 0;
    /**
     * 设备类型--借还
     */
    public static final Integer HARDWARE_TYPE_BORROW = 1;
    /**
     * 设备类型--存取
     */
    public static final Integer HARDWARE_TYPE_ACCESS = 2;

    public static final String CHANNEL_WE_CHAT = "WeChat";

    /**
     * 会员人脸增量同步TOPIC
     */
    public static final String INCRE_SYNC_MEMBER_FACE = "topic-%s-face-sync";

    public static final String HARDWARE_CROSSING_OPERATING_IN = "IN";

    public static final String HARDWARE_CROSSING_OPERATING_OUT = "OUT";

    public static final String HARDWARE_CROSSING_OPERATING_DEFAULT = "DEFAULT";

}
