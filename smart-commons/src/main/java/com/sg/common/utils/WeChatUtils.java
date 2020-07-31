package com.sg.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName 微信小程序订阅消息推送类
 * @Description: TODO
 * @Author admin
 * @Date 2020/7/22
 * @Version V1.0
 **/
@Component
public class WeChatUtils {
    //推送消息
    private static final String SNED_URL="https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";
    //添加模板
    private static final String ADD_TEMPLETE_URL="https://api.weixin.qq.com/wxaapi/newtmpl/addtemplate?access_token=ACCESS_TOKEN";
    //删除模板
    private static final String DELETE_TEMPLETE_URL="https://api.weixin.qq.com/wxaapi/newtmpl/deltemplate?access_token=ACCESS_TOKEN";
    //查询模板列表
    private static final String TEMPLETE_LIST_URL="https://api.weixin.qq.com/wxaapi/newtmpl/gettemplate?access_token=ACCESS_TOKEN";

    /**
     *
     * @param access_token
     * @param type 1-添加模板 2-删除模板 3-发送消息
     * @param param json字符串参数
     * @return
     * @throws Exception
     */
    public String push(String access_token,String param,int type) throws Exception {
        if(StringUtils.isEmpty(access_token)){
            return null;
        }
        //替换URL中的ACCESS_TOKEN
        String url;
        switch (type){
            case 1:
                url=replaceUrl("ACCESS_TOKEN",access_token,ADD_TEMPLETE_URL);
                break;
            case 2:
                url=replaceUrl("ACCESS_TOKEN",access_token,DELETE_TEMPLETE_URL);
                break;
            case 3:
                url=replaceUrl("ACCESS_TOKEN",access_token,SNED_URL);
                break;
            default:
                url=null;
                break;
        }
        if(StringUtils.isEmpty(url)){
            return null;
        }
        String jsonObject=MyHttpClientUtils.sendPost(url,param);
        return jsonObject;
    }

    /**
     * 获取模板列表
     * @param access_token
     * @return
     * @throws Exception
     */
    public String getTmpList(String access_token) throws Exception {
        if (StringUtils.isEmpty(access_token)) {
            return null;
        }
        String url = replaceUrl("ACCESS_TOKEN",access_token, TEMPLETE_LIST_URL);
        String jsonObject = MyHttpClientUtils.sendGet(url);
        return jsonObject;
    }

    /**
     * 字符串替换公共方法
     * @param key 需要替换的字符串
     * @param val 替换的字符串的值
     * @param url 需要喜欢的连接
     * @return
     */
    private String replaceUrl(String key,String val,String url){
        if(StringUtils.isEmpty(val)){
            return url;
        }
        url=url.replace(key,val);
        return url;
    }

}
