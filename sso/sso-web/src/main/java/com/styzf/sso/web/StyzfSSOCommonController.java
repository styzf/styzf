package com.styzf.sso.web;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.styzf.core.common.redis.RedisUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 用户主表 前端控制器
 * </p>
 *
 * @author styzf
 * @since 2018-08-14
 */
@Api(value = "测试接口")
@RestController
@RequestMapping(value = "/sso/common", method = {RequestMethod.GET, RequestMethod.POST})
public class StyzfSSOCommonController {
    
    @Autowired
    private RedisUtil redisUtil;
    
    private static char[] codes = new char[62];
    
    static {
        int num_A = 'A';
        int num_a = 'a';
        int num = '0';
        for (int i = 0; i < codes.length; i++) {
            if (i < 26) {
                codes[i] = (char) num_A;
                num_A ++;
                continue;
            }
            if (i < 26 * 2) {
                codes[i] = (char) num_a;
                num_a ++;
                continue;
            }
            codes[i] = (char) num;
            num ++;
        }
        System.out.println(codes);
    }
    
    //得到随机的类
    private Random ran = new Random();
    
    @ApiOperation(value = "生成验证码", notes = "生成验证码")
    @RequestMapping("code")
    public void securityCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        // 随机下标
        int index = ran.nextInt(codes.length);
        // 随机得到一个字符
        char c = codes[index];
        sb.append(c);
        // 将每个字符画到图片，位置：5+(i*20), 20
        graphics.drawString(String.valueOf(c), 5+(i*20), 20);
        }
        //把字符串写入到会话域
        redisUtil.set(sb.toString(), sb.toString(), 60 * 5L);
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
        ImageIO.write(img, "png", response.getOutputStream());
    }
    /**
     * 随机得到一种颜色
     * @return
     */
    private Color getColor() {
        //每种是0-255
        int r = ran.nextInt(256);
        int g = ran.nextInt(256);
        int b = ran.nextInt(256);
        return new Color(r, g, b);
    }
}

