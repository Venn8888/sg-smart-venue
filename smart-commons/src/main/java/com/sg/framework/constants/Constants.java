package com.sg.framework.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * <b> 全局常量信息 </b>
 * <p>
 * 功能描述:全局常量及通用方法类
 * </p>
 *
 * @author 
 * @date 2017/9/5
 * @time 18:20
 * @Path com.yy.framework.base.BaseConstants
 */
public class Constants {
    public static final String REQUEST_USERNAME = "username";
    public static final String REQUEST_PASSWORD = "password";

    public static final String SSO_CACHE = "SSO_CACHE";
	
	public final static String BEAN_EXECUTE_URL = "BEAN_EXECUTE_URL_";
	public final static String BEAN_EXECUTE_ARGS = "BEAN_EXECUTE_ARGS_";

    public final static String OPEN_ID = "openid";
//    public final static String AUTHORITY_PREFIX_MENU = "MENU_";
//    public final static String AUTHORITY_PREFIX_ROLE = "ROLE_";
//    public final static String AUTHORITY_PREFIX_API = "API_";
//    public final static String AUTHORITY_PREFIX_ACTION = "ACTION_";
    
    public final static String MAIN_ID = "mainId";
    public final static String DATA_ORG_ID = "dataOrgId";
    public final static String USER_TYPE = "userType";
    
    /**
     * 服务名称
     */
    public static final String BASE_SERVER = "zlzk-cloud-base-server";

    /**
     * 默认接口分类
     */
    public final static String DEFAULT_API_CATEGORY = "default";

    /**
     * 账号类型:
     * username:系统用户名、email：邮箱、mobile：手机号、qq：QQ号、weixin：微信号、weibo：微博
     */
    public final static String ACCOUNT_TYPE_USERNAME = "username";
    public final static String ACCOUNT_TYPE_EMAIL = "email";
    public final static String ACCOUNT_TYPE_MOBILE = "mobile";
    public final static String ACCOUNT_TYPE_QQ = "qq";
    public final static String ACCOUNT_TYPE_WX = "wx";
    
    public static final String QUEUE_SCAN_API_RESOURCE = "platform.scan.api.resource";
    public static final String QUEUE_ACCESS_LOGS = "platform.access.logs";


    /**
     * 默认超级管理员账号
     */
    public final static String ROOT = "admin";

    /**
     * 默认最小页码
     */
    public static final int MIN_PAGE = 0;
    /**
     * 最大显示条数
     */
    public static final int MAX_LIMIT = 999;
    /**
     * 默认页码
     */
    public static final int DEFAULT_PAGE = 1;
    /**
     * 默认显示条数
     */
    public static final int DEFAULT_LIMIT = 10;
    /**
     * 页码 KEY
     */
    public static final String PAGE_KEY = "page";
    /**
     * 显示条数 KEY
     */
    public static final String PAGE_LIMIT_KEY = "limit";
    /**
     * 排序字段 KEY
     */
    public static final String PAGE_SORT_KEY = "sort";
    /**
     * 排序方向 KEY
     */
    public static final String PAGE_ORDER_KEY = "order";

    /**
     * 客户端ID KEY
     */
    public static final String SIGN_APP_ID_KEY = "appId";

    /**
     * 客户端秘钥 KEY
     */
    public static final String SIGN_SECRET_KEY = "secretKey";

    /**
     * 随机字符串 KEY
     */
    public static final String SIGN_NONCE_KEY = "nonce";
    /**
     * 时间戳 KEY
     */
    public static final String SIGN_TIMESTAMP_KEY = "timestamp";
    /**
     * 签名类型 KEY
     */
    public static final String SIGN_SIGN_TYPE_KEY = "signType";
    /**
     * 签名结果 KEY
     */
    public static final String SIGN_SIGN_KEY = "sign";

    
	//用户类型
	public static class UserType {
		public static String ADMIN ="M";
		public static String SUPPER ="S";
		public static String NORMAL ="N";
	}
	
	//是否
	public static class YesNo {
		public static String YES ="Y";
		public static String NO ="N";
	}
	
	
	public static String DEFAULT = "default";
	public static String TEMPLATE = "template";
	public static String REPORT = "report";

	
	public static class Operation {
		public static final String ADD = "add";
		public static final String DELETE = "delete";
		public static final String UPDATE = "update";
		public static final String VIEW = "view";
		public static final String LIST = "list";
		public static final String CALL = "call";
		public static final String IMPORT = "in";
		public static final String EXPORT = "out";
	}
	
	public static class OperationName {
		public static final String ADD = "新增";
		public static final String DELETE = "删除";
		public static final String UPDATE = "修改";
		public static final String VIEW = "显示";
		public static final String LIST = "查询";
		public static final String CALL = "调用";
		public static final String IMPORT = "导入";
		public static final String EXPORT = "导出";
	}

	public static Map<String, String> operations = new HashMap<String, String>();
	static{
		operations.put(Operation.ADD, OperationName.ADD);
		operations.put(Operation.DELETE, OperationName.DELETE);
		operations.put(Operation.UPDATE, OperationName.UPDATE);
		operations.put(Operation.VIEW, OperationName.VIEW);
		operations.put(Operation.LIST, OperationName.LIST);
//		operations.put(Operation.CALL, OperationName.CALL);
//		operations.put(Operation.IMPORT, OperationName.IMPORT);
//		operations.put(Operation.EXPORT, OperationName.EXPORT);
	}
}
