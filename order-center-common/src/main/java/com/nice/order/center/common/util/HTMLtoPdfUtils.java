package com.nice.order.center.common.util;

import java.io.File;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

/**
 * HTML 转 PDF
 *
 * @author hai.huang.a@outlook.com
 * @date 2022/3/8 10:51
 */

@Slf4j
public final class HTMLtoPdfUtils {


    public static void convert10Mul15(String wkhtmltopdfPath, String srcPath, String destPath, String height,
                                      String width) {
        File file = new File(destPath);
        File parent = file.getParentFile();
        // 如果 PDF 保存路径不存在，则创建路径
        if (!parent.exists()) {
            if (!parent.mkdirs()) {
                throw new RuntimeException("Fail to create file path...");
            }
        }
        StringBuilder cmd = new StringBuilder();
        cmd.append(wkhtmltopdfPath);
        cmd.append(" ");

        cmd.append("--page-height ").append(height);
        cmd.append(" ");
        cmd.append("--page-width  ").append(width);
        cmd.append(" ");
        cmd.append("--disable-smart-shrinking");
        cmd.append(" ");
        cmd.append("--margin-bottom 0");
        cmd.append(" ");
        cmd.append("--margin-right 0");
        cmd.append(" ");
        cmd.append("--margin-left 0");
        cmd.append(" ");
        cmd.append("--margin-top 0");
        cmd.append(" ");
        cmd.append(srcPath);
        cmd.append(" ");
        cmd.append(destPath);

        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec(cmd.toString());
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void convert10Mul15(String srcPath, String destPath) {
        convert10Mul15("", srcPath, destPath, "150", "100");
    }

    public static void convert10Mul15(String srcPath, String destPath, String height, String width) {
        convert10Mul15("", srcPath, destPath, height, width);
    }


}
