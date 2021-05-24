package com.ddh.learn.poi;

import com.deepoove.poi.XWPFTemplate;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.HashMap;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/19 10:18
 * @description:
 */
public class TestPoi {
    @Test
    public void test() throws Exception {
        XWPFTemplate.compile("test.docx").render(
                new HashMap<String, String>() {{
                    put("title", "hello world");
                }}
        ).writeAndClose(new FileOutputStream("output.docx"));
    }
}
