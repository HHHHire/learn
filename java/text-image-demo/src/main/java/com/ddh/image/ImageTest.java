package com.ddh.image;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author: ddh
 * @data: 2020/4/17 21:09
 * @description
 */
public class ImageTest {
    public static void main(String[] args) throws Exception {
        String texts = "豆豉,镇江香醋,鹏州一级大豆油,蜂蜜,山西老陈醋,迎客芝麻调和油,海天蚝油";
        String[] split = StringUtils.split(texts, ",");
        for (String s : split) {
            createImage(s, new Font("微软雅黑", Font.PLAIN, 64), new File("f:/test/" + s + ".png"), 500, 500);
        }
    }

    // 根据str,font的样式以及输出文件目录
    public static void createImage(String str, Font font, File outFile,
                                   Integer width, Integer height) throws Exception {
        // 创建图片
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setClip(0, 0, width, height);
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景
        g.setColor(Color.black);// 在换成黑色
        g.setFont(font);// 设置画笔字体
        /** 用于获得垂直居中y */
        Rectangle clip = g.getClipBounds();
//        FontMetrics fm = g.getFontMetrics(font);
//        int ascent = fm.getAscent();
//        int descent = fm.getDescent();
//        int y = (clip.height - (ascent + descent)) / 2 + ascent;
//        for (int i = 0; i < 6; i++) {// 256 340 0 680
//            g.drawString(str, i * 680, y);// 画出字符串
//        }
//        g.dispose();
        FontRenderContext frc = new FontRenderContext(null, true, true);

        Rectangle2D r2D = font.getStringBounds(str, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (clip.width / 2) - (rWidth / 2) - rX;
        int b = (clip.height / 2) - (rHeight / 2) - rY;

        g.setFont(font);
        g.drawString(str, clip.x + a, clip.y + b);
        ImageIO.write(image, "png", outFile);// 输出png图片
    }
}
