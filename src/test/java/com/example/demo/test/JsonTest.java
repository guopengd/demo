package com.example.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.List;

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

    public static void main(String[] args) {
        String str = "[{\"url\":\"https://wofile.oss-cn-shanghai.aliyuncs.com/img/2019-03-28/4cef4617-0ecd-45f4-91ba-f9e6e10148ad.png\",\"width\":828,\"height\":553}," +
                "{\"url\":\"https://wofile.oss-cn-shanghai.aliyuncs.com/img/2019-03-28/34877d6c-e212-42e2-9e26-824889799850.png\",\"width\":828,\"height\":620}]";

        List<CreateImageDto> json = JSON.parseArray(str, CreateImageDto.class);
        System.out.println(json);
    }

}


/**
 * Created by dell on 2019/3/28.
 *
 * @author smj
 */
class CreateImageDto {
    /**
     * 图片url
     */
    private String url;

    /**
     * 图片高度
     */
    private Integer height;

    /**
     * 图片宽度
     */
    private Integer width;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "CreateImageDto{" +
                "url='" + url + '\'' +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}
