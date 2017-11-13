package com.tasfe.framework.encrypt.api.annotation;

import com.tasfe.framework.encrypt.api.Decryptable;
import com.tasfe.framework.encrypt.api.Encryptable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lait.zhang@gmail.com
 * @Date 20161019
 * @Description:<code>加密注解</code>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Encrypt {
    /**
     * 加密字段的key
     * @return
     */
    String value() default "";

    /**
     * 解密规则实现类
     * @return
     */
    Class<?> decrypter() default Decryptable.class ;

    /**
     * 加密实现类
     * @return
     */
    Class<?> encrypter() default Encryptable.class;
}
