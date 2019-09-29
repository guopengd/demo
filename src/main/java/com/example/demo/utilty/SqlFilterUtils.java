package com.example.demo.utilty;

import org.apache.commons.lang3.StringUtils;

import static org.springframework.util.StringUtils.split;

/**
 * 防止sql注入
 *
 * @author gpd
 * @date 2019/3/29
 */
public class SqlFilterUtils {

    public static String sqlInj(String str) {
        // 是否为空
        if (StringUtils.isBlank(str)) {
            return null;
        }

        String injStr = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
        String[] injSql = split(injStr, "|");

        assert injSql != null;
        for (int i = 0; i < injSql.length; i++) {
            if (str.toLowerCase().contains(injSql[i])) {
                return null;
            }
        }
        return str;
    }
}
