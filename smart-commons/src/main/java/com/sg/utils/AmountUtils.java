package com.sg.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * com.util.AmountUtils
 *
 * @author huguang
 * @description 金额元分之间转换工具类
 * @date 2020/2/24
 */
public class AmountUtils {


    /**
     * 金额为分的格式
     */
    public static final String CURRENCY_FEN_REGEX = "-?[0-9]+";


    /**
     * 将分为单位的转换为元并返回金额格式的字符串 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeF2Y(Long amount) throws Exception {
        if (!amount.toString().matches(CURRENCY_FEN_REGEX)) {
            throw new Exception("金额格式有误");
        }


        int flag = 0;
        String amString = amount.toString();
        if (amString.charAt(0) == '-') {
            flag = 1;
            amString = amString.substring(1);
        }
        StringBuilder result = new StringBuilder();
        if (amString.length() == 1) {
            result.append("0.0").append(amString);
        } else if (amString.length() == 2) {
            result.append("0.").append(amString);
        } else {
            String intString = amString.substring(0, amString.length() - 2);
            for (int i = 1; i <= intString.length(); i++) {
                if ((i - 1) % 3 == 0 && i != 1) {
                    result.append(",");
                }
                result.append(intString, intString.length() - i, intString.length() - i + 1);
            }
            result.reverse().append(".").append(amString.substring(amString.length() - 2));
        }
        if (flag == 1) {
            return "-" + result.toString();
        } else {
            return result.toString();
        }
    }


    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeF2y(String amount) throws Exception {
        if (!amount.matches(CURRENCY_FEN_REGEX)) {
            throw new Exception("金额格式有误");
        }
        return BigDecimal.valueOf(Long.parseLong(amount)).divide(new BigDecimal(100)).toString();
    }


    /**
     * 将元为单位的转换为分 （乘100）
     *
     * @param amount
     * @return
     */
    public static String changeY2F(Double amount) {
        return BigDecimal.valueOf(amount).multiply(new BigDecimal(100)).toString();
    }


    /**
     * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额
     *
     * @param amount
     * @return
     */
    public static String changeY2F(String amount) {
        // 处理包含, ￥
        String currency = amount.replaceAll("[$￥,]", "");
        // 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        long amLong = 0L;
        if (index == -1) {
            amLong = Long.parseLong(currency + "00");
        } else if (length - index >= 3) {
            amLong = Long.parseLong((currency.substring(0, index + 3)).replace(".", ""));
        } else if (length - index == 2) {
            amLong = Long.parseLong((currency.substring(0, index + 2)).replace(".", "") + 0);
        } else {
            amLong = Long.parseLong((currency.substring(0, index + 1)).replace(".", "") + "00");
        }
        return Long.toString(amLong);
    }

    public static Integer changeD2I(Double amount){
        DecimalFormat decimalFormat = new DecimalFormat("0");
       return Integer.valueOf(decimalFormat.format(amount));
    }

}
