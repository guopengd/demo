package com.example.demo.test;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author pengdong.guo
 * @date 2019/6/20
 */
public class CompressionTest {

    public static void main(String[] args) throws IOException {
        OutputStream os = new FileOutputStream(new File("/home/guopengdong/文档/a.txt"));
        for (int i = 0; i < 73; i++) {
            String s = "{% fi https://cloud.gpdstudy.club/static/images/" + i + ".jpg@lazy%}\n";
            os.write(s.getBytes());
        }

        //压缩图片 第一个参数是原图路径 后面那个路径是压缩以后的输出路径
        Thumbnails.of("path")
                .outputQuality(1f)
                .scale(1f)
                .toFile("path");
    }


}
