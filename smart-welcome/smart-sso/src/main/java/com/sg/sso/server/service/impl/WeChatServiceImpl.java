package com.sg.sso.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sg.api.SgResponse;
import com.sg.domain.response.WxLoginResponse;
import com.sg.domain.response.WxMobileLoginResponse;
import com.sg.sso.server.service.WeChatService;
import com.sg.sso.server.utils.JsCode2SessionUtil;
import com.sg.sso.server.utils.Oauth2LoginUtil;
import com.sg.utils.MobileMsgUtil;
import com.sg.utils.StringRedisUtil;
import com.sg.utils.TokenGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/5/26
 */
@Slf4j
@Service("WeChatServiceImpl")
public class WeChatServiceImpl implements WeChatService {

    @Value("${weChat.appid}")
    private String appid;

    @Value("${weChat.secret}")
    private String secret;

    @Value("${weChat.sessionKeyExpirationTime}")
    private Long sessionKeyExpirationTime;

    @Value("${ali.sms.template.code}")
    private String templateCode;

    private static final String SMS_CODE_REDIS_KEY = "smsCodeRedisKey";

    private static final String SMS_CODE_SEND_STINT_REDIS_KEY = "smsCodeSendStintRedisKey";

    private static final String WE_CHAT_ACCESS_TOKEN = "wechatAccessToken";

    @Autowired
    private StringRedisUtil stringRedisUtil;

  /*  @Reference
    private UserAccountService userAccountService;*/

/*    @Reference
    private MemberService memberService;

    @Reference
    private MemberLevelService memberLevelService;

    @Reference
    private SmsCodeService smsCodeService;*/

    @Autowired
    private Oauth2LoginUtil oauth2LoginUtil;

   /* @Reference
    private AliyunMsgService aliyunMsgService;*/

    @Override
    public SgResponse<String> wxLogin(String code) throws Exception {

        String jsonObject = jsCode2Session(code);

        Assert.notNull(jsonObject, "微信登录失败");

        WxLoginResponse wxLoginResponse = JSON.parseObject(jsonObject, WxLoginResponse.class);

        if (StringUtils.isNotBlank(wxLoginResponse.getOpenid()) && StringUtils.isNotBlank(wxLoginResponse.getSession_key())) {

            String token = TokenGenerateUtil.generateToken();
            stringRedisUtil.set(token, jsonObject, sessionKeyExpirationTime);

            return SgResponse.ok(token);
        }

        return SgResponse.failed("无效code");
    }

    @Override
    @Transactional(rollbackFor = {Throwable.class})
    public SgResponse<Object> wxMobileLogin(String token, String encryptedData, String iv, String storeId, HttpHeaders headers) throws Exception {
        String jsonObject = stringRedisUtil.get(token);
        Assert.notNull(jsonObject, "微信登录token过期");
        WxLoginResponse wxLoginResponse = JSON.parseObject(jsonObject, WxLoginResponse.class);
        String decryptData = JsCode2SessionUtil.getDecryptData(encryptedData, wxLoginResponse.getSession_key(), iv);
        Assert.notNull(decryptData, "解密数据为空");
        WxMobileLoginResponse wxMobileLoginResponse = JSON.parseObject(decryptData, WxMobileLoginResponse.class);

        String openid = wxLoginResponse.getOpenid();
        String mobile = wxMobileLoginResponse.getPurePhoneNumber();

        /*UserAccountVO userAccountVO = this.queryUserAccount(openid, mobile);
        if (Objects.isNull(userAccountVO)) {
            userAccountVO = saveUserAccountAndMember(openid, mobile, storeId);
        }
        JSONObject result = oauth2LoginUtil.getToken(userAccountVO.getUserName(), mobile, Constants.REQUEST_PASSWORD, headers);
        result.put("memberId", userAccountVO.getUdf5());
        result.put("isNew", "Y".equals(userAccountVO.getUdf4()));
        log.info("oauth2Login result:{}", result);
        if (result.containsKey("access_token")) {
            return SgResponse.ok().data(result);
        } else {
            return SgResponse.failed().data("微信手机号登录失败");
        }*/
        return null;
    }

    @Override
    public SgResponse<Object> wxMobileAndCodeLogin(String mobile, String code, String storeId, HttpHeaders headers) throws Exception {
        String redisCode = stringRedisUtil.get(SMS_CODE_REDIS_KEY + mobile);
        Assert.notNull(redisCode, "验证码已过期,请重新获取");
        Assert.isTrue(redisCode.equals(code), "验证码错误");
       /* UserAccountVO userAccountVO = this.queryUserAccount(null, mobile);
        if (Objects.isNull(userAccountVO)) {
            userAccountVO = saveUserAccountAndMember(null, mobile, storeId);
        }
        JSONObject result = oauth2LoginUtil.getToken(userAccountVO.getUserName(), mobile, Constants.REQUEST_PASSWORD, headers);
        result.put("memberId", userAccountVO.getUdf5());
        if (result.containsKey("access_token")) {
            return SgResponse.ok().data(result);
        } else {
            return SgResponse.failed().data("手机号+验证码登录失败");
        }*/
       return null;
    }

    @Override
    public SgResponse wxMobileMsgSend(String mobile) throws Exception {
        //获取redis数据
        String msgCodeSend = stringRedisUtil.get(SMS_CODE_SEND_STINT_REDIS_KEY + mobile);
        //非空说明一分钟内发过短信，拒绝
        Assert.isTrue(StringUtils.isBlank(msgCodeSend), "1分钟内不可重复发送短信");
        //记录发送的手机号，1分钟有效期
        stringRedisUtil.set(SMS_CODE_SEND_STINT_REDIS_KEY + mobile, "防重key", 60 * 1000);
        //获取随机6位数
        String randomCode = MobileMsgUtil.createRandomCode();
        //插入短信推送流水
        saveSmsCodeVO(mobile, randomCode);
        //准备好短信模板变量——验证码code
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", randomCode);
        String templateParam = jsonObject.toJSONString();
        //推送短信
       /* SgResponse resultBody = aliyunMsgService.sendSms(mobile, templateCode, templateParam);
        //判断结果
        if (resultBody.isOk()) {
            stringRedisUtil.set(SMS_CODE_REDIS_KEY + mobile, randomCode, 10 * 60 * 1000);
            return SgResponse.ok();
        }*/
        return SgResponse.failed("短信系统异常,请稍后再试!");
    }

    @Override
    public String getAccessToken() throws Exception {
        String access_token = stringRedisUtil.get(WE_CHAT_ACCESS_TOKEN);
        if (!StringUtils.isEmpty(access_token)) {
            return access_token;
        }
        String jsonObject = JsCode2SessionUtil.getAccessToken(appid, secret, "client_credential");
        JSONObject json = JSON.parseObject(jsonObject, JSONObject.class);
        if (json.containsKey("access_token")) {
            access_token = json.getString("access_token");
            long expired_in = json.getLong("expires_in");
            stringRedisUtil.set(WE_CHAT_ACCESS_TOKEN, access_token, expired_in);
            return json.getString("access_token");
        }
        return "";
    }

    private void saveSmsCodeVO(String mobile, String randomCode) {
       /* SmsCodeVO smsCodeVO = new SmsCodeVO();
        smsCodeVO.setCode(randomCode);
        smsCodeVO.setMobile(mobile);
        smsCodeVO.setIsActive(Constants.YesNo.YES);
        smsCodeVO.setType("13");
        smsCodeVO.setExpiredTime(MobileMsgUtil.getExpiredTime());
        smsCodeService.save(smsCodeVO);*/
    }

    /**
     * 新建用户及会员信息
     *
     * @param openid 小程序openid
     * @param mobile 微信关联手机号
     * @return UserAccountVO
     */
   /* private UserAccountVO saveUserAccountAndMember(String openid, String mobile, String storeId) throws Exception {

        UserAccountVO vo = new UserAccountVO();
        if (StringUtils.isNotBlank(openid)) {
            vo.setOpenId(openid);
        }
        vo.setUserName(mobile);
        vo.setPassword(mobile);
        vo.setIsEnable(Constants.YesNo.YES);
        vo.setIsAuthenticated(Constants.YesNo.YES);
        vo.setType(UserAccountVO.Member);
        vo.setRoleCode("Member");
        vo.setDefaultOrgId(storeId != null ? storeId : "726897922775449600");
        vo.setDefaultMainId("673672593789681664");
        vo.setDataOrgId(storeId != null ? storeId : "726897922775449600");
        vo.setMainId("673672593789681664");
        vo = userAccountService.save(vo);
        Assert.notNull(vo.getAccountId(), "登录失败");

       *//* MemberVO memberVO = new MemberVO();
        memberVO.setAccountId(vo.getAccountId());
        memberVO.setUdf1(mobile);
        if (StringUtils.isNotBlank(openid)) {
            memberVO.setUdf5(openid);
        }
        memberVO.setName("会员");
        memberVO.setIsActive(Constants.YesNo.YES);
        // 查询初级会员
        MemberLevelFilter memberLevelFilter = new MemberLevelFilter();
        memberLevelFilter.setName("初级会员");
        List<MemberLevelVO> memberLevelList = memberLevelService.findByFilter(memberLevelFilter);
        if (memberLevelList.size() > 0) {
            memberVO.setGrade(memberLevelList.get(0).getLevelId());
        }
        memberVO.setTotalMoney(0.00);
        memberVO.setPoint(0);
        memberVO.setTotalPoint(0);
        memberVO.setUsedPoint(0);
        memberVO.setOrganizationId(storeId != null ? storeId : "726897922775449600");
        memberVO.setDataOrgId(storeId != null ? storeId : "726897922775449600");
        memberVO.setMainId("673672593789681664");
        memberVO.setUdf4("Y");
        memberVO.setRegisterTime(new Date());
        memberVO = memberService.save(memberVO);
        vo.setUdf5(memberVO.getMemberId());
        vo.setUdf4(memberVO.getUdf4());*//*
        return vo;
    }

    *//**
     * 根据openid及mobile查询用户是否存在
     *
     * @param openid 微信openid
     * @param mobile 微信手机号
     * @return UserAccountVO
     *//*
    private UserAccountVO queryUserAccount(String openid, String mobile) throws Exception {
        UserAccountFilter userAccountFilter = new UserAccountFilter();
        userAccountFilter.setUserName(mobile);
        List<UserAccountVO> userAccounts = userAccountService.findByFilter(userAccountFilter);
        if (CollectionUtils.isEmpty(userAccounts) || userAccounts.size() <= 0) {
            return null;
        }
        UserAccountVO userAccountVO = userAccounts.get(0);
        if (StringUtils.isNotBlank(openid) && StringUtils.isBlank(userAccountVO.getOpenId())) {
            userAccountVO.setOpenId(openid);
            userAccountService.update(userAccountVO);
        }
       *//* MemberFilter filter = new MemberFilter();
        filter.setAccountId(userAccountVO.getAccountId());
        List<MemberVO> memberVOS = memberService.findByFilter(filter);
        if (memberVOS.size() > 0) {
            MemberVO memberVO = memberVOS.get(0);
            userAccountVO.setUdf5(memberVO.getMemberId());
            userAccountVO.setUdf4(memberVO.getUdf4());
        }*//*
        return userAccountVO;
    }
*/
    /**
     * 登录凭证校验
     *
     * @param code 微信登录小程序code
     * @return 结果
     * @throws Exception
     */
    private String jsCode2Session(String code) throws Exception {
        //登录grantType固定
        return JsCode2SessionUtil.jsCode2session(appid, secret, code, "authorization_code");
    }
}
