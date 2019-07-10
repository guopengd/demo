package com.example.demo.test;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author pengdong.guo
 * @date 2019/6/20
 */
public class CompressionTest {

    public static void main(String[] args) throws IOException {
        String path = "D:\\potos";
        String outputPath = "D:\\MyConfiguration\\pengdong.guo\\Desktop\\";
        String textPath = "D:\\MyConfiguration\\pengdong.guo\\Desktop\\a.txt";

        File file = new File(path);
        if (file.isFile()) {
            outputImage(file, outputPath);
        }
        if (file.isDirectory()) {
            File[] tempList = file.listFiles();
            assert tempList != null; // 目录存在则文件永远不会为null
            for (int i = 0; i < tempList.length; i++) {
                outputImage(tempList[i], outputPath + i + ".jpg");
            }

            OutputStream os = new FileOutputStream(new File(textPath));
            for (int i = 0; i < tempList.length; i++) {
                String s = "{% fi https://cloud.ovvow.com/static/images/" + i + ".jpg@lazy%}\n";
                os.write(s.getBytes());
            }
        }
    }

    private static void outputImage(File outFile, String outputPath) throws IOException {
        //压缩图片 第一个参数是原图路径 后面那个路径是压缩以后的输出路径
        Thumbnails.of(outFile).outputQuality(0.9f).scale(1f).toFile(outputPath);
    }
}