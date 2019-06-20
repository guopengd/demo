package com.example.demo.test;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author pengdong.guo
 * @date 2019/6/20
 */
public class CompressionTest {

    public static void main(String[] args) throws IOException {
        //压缩图片 第一个参数是原图路径 后面那个路径是压缩以后的输出路径
        Thumbnails.of("D:\\MyConfiguration\\pengdong.guo\\Desktop\\Screenshot_2019-05-19-15-30-42-275_com.miui.video.png")
                .outputQuality(1f)
                .scale(1f)
                .toFile("D:\\MyConfiguration\\pengdong.guo\\Desktop\\1.jpg");
    }
}
