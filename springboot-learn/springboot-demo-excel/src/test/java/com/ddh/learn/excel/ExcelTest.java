package com.ddh.learn.excel;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/14 15:39
 * @desc
 */
@SpringBootTest
public class ExcelTest {

    @Test
    public void test1() {
        ExcelWriter writer = ExcelUtil.getWriter("F:\\test\\excel\\test1.xlsx");
        List<String> title = getTitle();
        List<List<String>> lists = generateTableData();
        lists.add(0, title);

        writer.write(lists, true);
        writer.close();
    }

    // 获取头信息
    private List<String> getTitle() {
        List<String> data = new ArrayList<>();
        data.add("项目名称");
        data.add("组织单位");
        data.add("编制单位");
        data.add("规划层级");
        data.add("编制情况");
        data.add("编制时间");
        data.add("项目状态");
        return data;
    }

    // 生成表格数据
    private List<List<String>> generateTableData() {
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<String> data = new ArrayList<>();
            data.add("hello" + i);
            data.add("hello" + i);
            data.add("hello" + i);
            data.add("hello" + i);
            data.add("hello" + i);
            data.add("hello" + i);
            result.add(data);
        }
        return result;
    }
}
