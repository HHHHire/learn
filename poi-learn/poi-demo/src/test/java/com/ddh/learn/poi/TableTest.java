package com.ddh.learn.poi;

import com.ddh.learn.poi.base.DetailTablePolicy;
import com.ddh.learn.poi.base.model.DetailData;
import com.ddh.learn.poi.base.model.Goods;
import com.ddh.learn.poi.base.model.Labor;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/20 9:54
 * @description: 表格
 */
public class TableTest {

    private static List<Goods> goodsList = new ArrayList<>();
    private static List<Labor> labors = new ArrayList<>();

    static {
        IntStream.range(1, 4).forEach(i -> {
            Goods goods = Goods.builder().count(i).name("巴雷特" + i).desc("一枪一个").tax(30 + i)
                    .price(100 + i).discount(10 + i).totalPrice(70 + i).build();
            goodsList.add(goods);
            Labor labor = Labor.builder().category("海军陆战队" + i).people(100 + i).price(1000 + i)
                    .totalPrice(100000 + i).build();
            labors.add(labor);
        });
    }

    /**
     * 循环表格行
     *
     * @throws Exception
     */
    @Test
    public void tableRowTest() throws Exception {
        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
        Configure config = Configure.builder().bind("goods", policy)
                .bind("labors", policy).build();
        XWPFTemplate.compile("table-test.docx", config).render(
                new HashMap<String, Object>() {{
                    put("goods", goodsList);
                    put("labors", labors);
                    put("title", "hello world");
                }}
        ).writeAndClose(new FileOutputStream("output.docx"));
    }

    /**
     * 循环表格列和上面的类似
     */

    /**
     * 动态表格
     */
    @Test
    public void DynamicTableTest() throws Exception {
        List<RowRenderData> goodsList = new ArrayList<>();
        List<RowRenderData> laborList = new ArrayList<>();
        IntStream.range(1, 4).forEach(i -> {
            RowRenderData goods = Rows.of(String.valueOf(i), "巴雷特" + i, "一枪一个" + i, String.valueOf(10 + i),
                    String.valueOf(30 + i), String.valueOf(100 + i), String.valueOf(70 + i))
                    .create();
            RowRenderData labor = Rows.of("海军陆战队" + i, String.valueOf(100 + i),
                    String.valueOf(1000 + i), String.valueOf(10000 + i)).create();
            goodsList.add(goods);
            laborList.add(labor);
        });
        DetailData detailData = new DetailData(goodsList, laborList);

        Configure config = Configure.builder().bind("table_detail", new DetailTablePolicy(2, 5)).build();
        XWPFTemplate.compile("table-test.docx", config).render(
                new HashMap<String, Object>() {{
                    put("table_detail", detailData);
                }}
        ).writeAndClose(new FileOutputStream("output.docx"));
    }

}
