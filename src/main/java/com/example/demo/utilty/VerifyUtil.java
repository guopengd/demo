package com.example.demo.utilty;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;


/**
 * 图形验证码生成
 *
 * @author gpd
 * @date 2019/3/29
 */
public class VerifyUtil {

    /**
     * 验证码字符集
     */
    private static final char[] chars = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /**
     * 字符数量
     */
    private static final int SIZE = 4;

    /**
     * 干扰线数量
     */
    private static final int LINES = 5;

    /**
     * 宽度
     */
    private static final int WIDTH = 80;

    /**
     * 高度
     */
    private static final int HEIGHT = 40;

    /**
     * 字体大小
     */
    private static final int FONT_SIZE = 30;

    /**
     * 生成随机验证码及图片
     * Object[0]：验证码字符串；
     * Object[1]：验证码图片base64。
     */
    public static Object[] createImage() {
        StringBuilder sb = new StringBuilder();
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = image.getGraphics();
        graphic.setColor(Color.LIGHT_GRAY);
        graphic.fillRect(0, 0, WIDTH, HEIGHT);
        // 画随机字符
        Random ran = new Random();
        for (int i = 0; i < SIZE; i++) {
            int n = ran.nextInt(chars.length);
            graphic.setColor(getRandomColor());
            graphic.setFont(new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE));
            graphic.drawString(chars[n] + "", i * WIDTH / SIZE, HEIGHT * 2 / 3);
            sb.append(chars[n]);
        }
        // 画干扰线
        for (int i = 0; i < LINES; i++) {
            graphic.setColor(getRandomColor());
            graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT), ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        String base64Img = encoder.encode(outputStream.toByteArray());
        // 返回验证码和图片base64编码
        return new Object[]{sb.toString(), base64Img};
    }

    /**
     * 随机取色
     */
    public static Color getRandomColor() {
        Random ran = new Random();
        return new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
    }
}
