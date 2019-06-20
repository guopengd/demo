package com.example.demo;

import lombok.Data;

/**
 * 要拦截的demo类
 *
 * @author pengdong.guo
 * @date 2019/5/23
 */
@FieldTypeAnnotation(name = "叶总监想要的demo类")
@Data
public class DemoEntity {

    @FieldTypeAnnotation(name = "叶总监")
    private String stupid;

    private String empty;
}
