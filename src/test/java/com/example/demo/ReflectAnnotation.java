package com.example.demo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author pengdong.guo
 * @date 2019/5/23
 */
public class ReflectAnnotation {

    public static void main(String[] args) {
        // 此处要用反射将字段中的注解解析出来
        Class<ReflectAnnotation> clz = ReflectAnnotation.class;
        // 判断类上是否有次注解
        boolean clzHasAnno = clz.isAnnotationPresent(FieldTypeAnnotation.class);
        if (clzHasAnno) {
            // 获取类上的注解
            FieldTypeAnnotation annotation = clz.getAnnotation(FieldTypeAnnotation.class);
            // 输出注解上的属性
            int age = annotation.age();
            String name = annotation.name();
            String sex = annotation.sex();
            System.out.println(clz.getName() + " age = " + age + ", name = " + name + " sex = " + sex);
        }
        // 解析字段上是否有注解
        // ps：getDeclaredFields会返回类所有声明的字段，包括private、protected、public，但是不包括父类的
        // getFields:则会返回包括父类的所有的public字段，和getMethods()一样
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            boolean fieldHasAnno = field.isAnnotationPresent(FieldTypeAnnotation.class);
            if (fieldHasAnno) {
                FieldTypeAnnotation fieldAnno = field.getAnnotation(FieldTypeAnnotation.class);
                //输出注解属性
  /*              int age = fieldAnno.age();
                String[] nea = fieldAnno.hobby();
                String type = fieldAnno.type();
                System.out.println(field.getName() + " age = " + age + ", hobby = " + Arrays.asList(hobby).toString() + " type = " + type);*/
            }
        }
        //解析方法上的注解
        Method[] methods = clz.getDeclaredMethods();
        for (Method method : methods) {
            boolean methodHasAnno = method.isAnnotationPresent(MethodAnnotation.class);
            if (methodHasAnno) {
                //得到注解
                MethodAnnotation methodAnno = method.getAnnotation(MethodAnnotation.class);
                //输出注解属性
                String desc = methodAnno.desc();
                System.out.println(method.getName() + " desc = " + desc);
            }
        }
    }
}
