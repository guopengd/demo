package com.example.demo.utilty;

import org.apache.commons.lang.StringUtils;

import static org.springframework.util.StringUtils.split;

public class SqlFilterUtils {

    public static String sqlInj(String str) {
        // 是否为空
        if (StringUtils.isBlank(str)) {
            return null;
        }

        String injStr = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
        String injSql[] = split(injStr, "|");

        for (int i = 0; i < injSql.length; i++) {
            if (str.toLowerCase().indexOf(injSql[i]) != -1) {
                return null;
            }
        }
        return str;
    }
}
