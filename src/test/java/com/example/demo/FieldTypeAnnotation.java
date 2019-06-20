package com.example.demo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 *
 * @author pengdong.guo
 * @date 2019/5/23
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})//次注解作用于类和字段上
public @interface FieldTypeAnnotation {

    String name();

    String sex() default "人妖";

    int age() default 23;

}

