package com.example.demo.test;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class JsonTest {

    @Test
    public void JsonChange() {
        String str = "{'local_address':'127.0.0.1','local_port':1080,'port_password':{'8980':'gpd6482'," +
                "'8981':'xlf6666','8982':'1097133067','8983':'gpd520'},'server':'0.0.0.0','timeout':300,'" +
                "method':'rc4-md5','fast_open':true,'workers':10}";
        JSONObject json = JSONObject.parseObject(str);
        Object obj = JSONObject.toJSON(json);
        System.out.println(obj);
        System.out.println(json.get("local_address"));
        json.put("local_address", "fsf");
        System.out.println(json);
    }
}
