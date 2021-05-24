package com.ddh.learn.poi;

import com.ddh.learn.poi.base.DetailTablePolicy;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.*;
import com.deepoove.poi.data.style.Style;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.*;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/19 10:37
 * @description: 标签测试
 */
public class TagTest {
    @Test
    public void test1() throws Exception {
        XWPFTemplate.compile("test.docx").render(
                new HashMap<String, Object>() {{
                    put("author", new TextRenderData("\nzhangsan", new Style("000000")));
                }}
        ).writeAndClose(new FileOutputStream("output.docx"));
    }

    /**
     * 通过工厂 Texts 构建
     *
     * @throws Exception
     */
    @Test
    public void textsTest() throws Exception {
        XWPFTemplate.compile("test.docx").render(
                new HashMap<String, Object>() {{
                    put("title", Texts.of("hello").style(Style.builder().buildBold().buildColor("777777").build()).create());
                    put("author", Texts.of("website").link("http://www.baidu.com").create());
                    put("addr", Texts.of("zhej").anchor("hello").create());
                }}
        ).writeAndClose(new FileOutputStream("output.docx"));
    }

    /**
     * 图片测试， {{@image}}
     * 通过工厂类 Pictures 构建
     *
     * @throws Exception
     */
    @Test
    public void PictureTest() throws Exception {
        XWPFTemplate.compile("test.docx").render(
                new HashMap<String, Object>() {{
                    put("image", "image-05-19.jpg");
                    put("image1", Pictures.ofLocal("image-05-19.jpg").size(120, 120).center().create());
                }}
        ).writeAndClose(new FileOutputStream("output.docx"));
    }

    /**
     * 区块对{{?selection}}
     */
    @Test
    public void selectionTest() throws Exception {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("value", null);
        Map<String, Object> map = new HashMap<>();
//        map.put("multi", map1);

        XWPFTemplate.compile("test.docx").render(map).writeAndClose(new FileOutputStream("output.docx"));
    }

    /**
     * 列表{{*value}}
     */
    @Test
    public void ListTest() throws Exception {
        Map<String, Object> map = new HashMap<>();
//        map.put("list", Numberings.create("一、", "二、", "三、"));
        Numberings.NumberingBuilder numberingBuilder = Numberings.ofDecimal();
        String[] array = {"一、", "二、", "三、"};
        Arrays.stream(array).forEach(numberingBuilder::addItem);
        NumberingRenderData numberingRenderData = numberingBuilder.create();
        map.put("list", numberingRenderData);

        XWPFTemplate.compile("test.docx").render(map).writeAndClose(new FileOutputStream("output.docx"));
    }

    /**
     * 包含另一个模板
     *
     * @throws Exception
     */
    @Test
    public void IncludeTest() throws Exception {
        final List<Addr> list = new ArrayList<Addr>();
        list.add(new Addr("上海"));
        list.add(new Addr("浙江"));
        list.add(new Addr("江西"));

        XWPFTemplate.compile("include.docx").render(
                new HashMap<String, Object>() {{
                    put("includ", Includes.ofLocal("test.docx").setRenderModel(list).create());
                }}
        ).writeAndClose(new FileOutputStream("output.docx"));
    }


    @Data
    @AllArgsConstructor
    class Addr {
        private String title;
    }

    /**
     * 图表测试
     *
     * @throws Exception
     */
    @Test
    public void ChartTest() throws Exception {
        final ChartMultiSeriesRenderData chart = Charts.ofMultiSeries("ChartTitle", new String[]{"中文", "English"})
                .addSeries("countries", new Double[]{15.0, 6.0})
                .addSeries("speakers", new Double[]{223.0, 119.0})
                .create();
        XWPFTemplate.compile("test.docx").render(
                new HashMap<String, Object>() {{
                    put("barChart", chart);
                }}
        ).writeAndClose(new FileOutputStream("output.docx"));
    }

    /**
     * 表格行循环
     */
    @Test
    public void tableRowTest() throws Exception {

    }

    /**
     * 动态表格
     * @throws Exception
     */
    @Test
    public void tableTest() throws Exception {
        Configure config = Configure.builder().bind("table_title", new DetailTablePolicy(2, 5)).build();
        XWPFTemplate.compile("test.docx", config).render(
                new HashMap<String, Object>() {{
                    put("table_title", null);
                }}
        ).writeAndClose(new FileOutputStream("output.docx"));
    }

}
