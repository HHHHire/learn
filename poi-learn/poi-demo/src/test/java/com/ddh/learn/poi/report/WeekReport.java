package com.ddh.learn.poi.report;

import cn.hutool.core.convert.Convert;
import com.ddh.learn.poi.util.DateUtil;
import com.ddh.learn.poi.base.model.Report;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.*;
import java.util.stream.IntStream;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/21 16:52
 * @description:
 */
public class WeekReport {
    @Test
    public void generateWeekReport() throws Exception {
        // 日期应当是一周的周日
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 4, 22);
        Date baseDate = calendar.getTime();

        List<Date> weekList = new ArrayList<>();
        List<Report> reports = new ArrayList<>();
        weekList.add(baseDate);
        // 获取一周的日期
        IntStream.range(0, 6).forEach(i -> {
            calendar.add(Calendar.DATE, -1);
//            weekList.add(calendar.getTime());
            Date time = calendar.getTime();
            Integer week = DateUtil.getDayInWeek(time);
            String weeks = Convert.numberToChinese(week, false);
            Report report = Report.builder().month(DateUtil.getMonthByDate(time))
                    .day(DateUtil.getDayByDate(time))
                    .week(weeks).build();
            reports.add(report);
        });


        Map<String, Object> model = new HashMap<>();
        // 标题
        model.put("year", DateUtil.getYearByDate(baseDate));
        model.put("month", DateUtil.getMonthByDate(baseDate));
        model.put("week", DateUtil.getWeekByDate(baseDate));
        // 表格
        model.put("report", reports);

        Configure config = Configure.builder().bind("report", new LoopRowTableRenderPolicy()).build();
        XWPFTemplate.compile("F:\\tmp\\2021年5月第4周报.docx", config).render(model)
                .writeAndClose(new FileOutputStream("F:\\tmp\\周报.docx"));
    }
}
