package com.sg.framework.annotation;

import java.lang.annotation.*;

/**
 * <b> 如果方法不需要TOKEN验证，调用此注解即可 </b>
 * <p>
 * 功能描述:
 * </p>
 *
 * @author 
 * @date 2017/9/15
 * @time 13:56
 * @Path com.yy.framework.base.MethedNotValidateToken
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethedNotValidateToken {
}
