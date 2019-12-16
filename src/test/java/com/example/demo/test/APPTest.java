package com.example.demo.test;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class APPTest {

    public static void main(String[] args) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Resource> httpEntity = new HttpEntity<>(headers);
        for (int i = 1; i <= 75; i++) {
            ResponseEntity<byte[]> response = template.exchange("https://i3.vzan.cc/m3u8/20191104/21b0868ed9fb4f5694bcc391b3184c07/v.f24306_" + i + ".ts", HttpMethod.GET, httpEntity, byte[].class);
            try {
                File file = new File("D:\\MyConfiguration\\pengdong.guo\\Downloads\\1\\v.f24306_" + i + ".ts");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(Objects.requireNonNull(response.getBody()));
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
