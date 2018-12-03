package com.styzf.sso.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.styzf.core.common.util.Assert;

public class CodeUtil {
    private static Random ran = new Random();
    
    private static Logger logger = LoggerFactory.getLogger(CodeUtil.class);
    
    public static void codeImage(String code, HttpServletResponse response) {
        int width = 95, height = 30;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取画笔对象
        Graphics  graphics = img.getGraphics();
        // 设置画笔颜色，并且填充矩形区域
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        // 设置字体，大小为18，设置字的颜色随机
        graphics.setFont(new Font(Font.DIALOG, Font.BOLD+Font.ITALIC, 20));
        // 创建一个StringBuiler
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            graphics.setColor(getColor());
            char c = code.charAt(i);
            sb.append(c);
            // 将每个字符画到图片，位置：5+(i*20), 20
            graphics.drawString(String.valueOf(c), 5+(i*20), 20);
        }
        // 8) 画干扰线10条线，线的位置是随机的，x范围在width之中，y的范围在height之中。
        for (int i = 0; i <10 ; i++) {
            //随机得到颜色
            graphics.setColor(getColor());
            //随机得到4个坐标
            int x1 = ran.nextInt(width);
            int y1 = ran.nextInt(height);
            int x2 = ran.nextInt(width);
            int y2 = ran.nextInt(height);
            graphics.drawLine(x1, y1, x2, y2);
        }
        // 9) 将缓存的图片输出到响应输出流中
        try {
            ImageIO.write(img, "png", response.getOutputStream());
        } catch (IOException e) {
            logger.error("com.styzf.sso.util.CodeUtil.codeImage:图片输出失败");
            Assert.throwException("com.styzf.sso.util.CodeUtil.codeImage");
        }
    }

    /**
     * 随机得到一种颜色
     * @return
     */
    private static Color getColor() {
        //每种是0-255
        int r = ran.nextInt(256);
        int g = ran.nextInt(256);
        int b = ran.nextInt(256);
        return new Color(r, g, b);
    }
}
