package com.ddh.learn.poi;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deepoove.poi.XWPFTemplate;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/6/3 10:17
 * @description:
 */
public class TemplateTest {
    @Test
    public void generatedTemplate() throws Exception {
        Map<String, Object> dandi = new HashMap<>();
        dandi.put("land_area_total_m", "26788.1");
        dandi.put("land_area_total_mu", "40.18");


        List<Map> duodiList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> subMap = new HashMap<>();
            subMap.put("sub_land_number", "05-12" + i);
            subMap.put("sub_land_area_m", "21147.97" + i);
            subMap.put("sub_land_area_mu", "31.74" + i);
            duodiList.add(subMap);
        }

        Map<String, Object> duodiMap = new HashMap<>();
        duodiMap.put("land_area_total_m", "26788.1");
        duodiMap.put("land_area_total_mu", "40.18");
        duodiMap.put("xunhuan", duodiList);

        Map<String, Object> map = new HashMap<>();
        map.put("world_title", "瑞安市自然资源和规划局");
        map.put("region_name", "北部组团塘下片区");
        map.put("land_number", "T8-1-4");
        map.put("land_supply_mode", "出让");
        map.put("approval_number", "瑞规条字〔2020〕63号");
        map.put("planning_basis", "《瑞安市北部组团（塘下片区）控制性详细规划修改（T6-4-5～T6-4-9、T8-1-1、T8-1-4～T8-1-7等地块）》（瑞政发〔2020〕73号）、《瑞安市城市规划建设管理技术规定》（2020版）和《瑞安市北部组团（塘下片区）T8-1-4地块规划条件论证报告》（瑞资规论〔2020〕7号）");
        map.put("land_location", "项目位于瑞安市塘下镇，康欣路以东，塘梅路以南，塘下大道以西，上马河以北，地块编号T8-1-4（详见附图一、附图二）。");
        map.put("build_land_properties", "二类住宅用地（R21）兼容商业设施用地（B1）");
        // 单地块
        map.put("dandikuai", null);
//        map.put("land_area_total_m", "26788.1");
//        map.put("land_area_total_mu", "40.18");

        // 多地块
        map.put("duodikuai", duodiMap);

        XWPFTemplate.compile("Doc1.docx").render(map).writeAndClose(new FileOutputStream("output.docx"));
    }

    @Test
    public void test() throws Exception {
        File file = new File("template.json");
        String content = FileUtil.readString(file, CharsetUtil.UTF_8);
        JSONObject parse = (JSONObject) JSONObject.parse(content);
        System.out.println(parse.get("name"));
        JSONArray data = parse.getJSONArray("data");
        JSONObject jsonObject = (JSONObject) data.get(0);
        System.out.println(jsonObject.get("DKBH"));


    }

    @Test
    public void test2() throws Exception {
        String content = FileUtil.readString(new File("template.json"), CharsetUtil.UTF_8);
        JSONObject parse = (JSONObject) JSONObject.parse(content);
        JSONObject info = (JSONObject) parse.get("info");
        JSONArray data = parse.getJSONArray("data");


        Map<String, Object> map = new HashMap<>();

        map.put("XZQW", info.getJSONObject("XZQW").get("UpDateInfo"));
        map.put("GDFS", info.getJSONObject("GDFS").get("UpDateInfo"));
        map.put("PZWH", info.getJSONObject("PZWH").get("UpDateInfo"));
        map.put("GHYJ", info.getJSONObject("GHYJ").get("UpDateInfo"));
        map.put("FJRQ", info.getJSONObject("FJRQ").get("UpDateInfo"));
        map.put("YDWZFW", info.getJSONObject("YDWZFW").get("UpDateInfo"));
        map.put("JSYDKZGC", info.getJSONObject("JSYDKZGC").get("UpDateInfo"));
        map.put("JZSYGN", info.getJSONObject("JZSYGN").get("UpDateInfo"));
        map.put("DXKJYQ", info.getJSONObject("DXKJYQ").get("UpDateInfo"));
        map.put("JZWGZWHDXJZTRJL", info.getJSONObject("JZWGZWHDXJZTRJL").get("UpDateInfo"));
        map.put("SSSJ", info.getJSONObject("SSSJ").get("UpDateInfo"));
        map.put("SSSJJJZJWLMGL", info.getJSONObject("SSSJJJZJWLMGL").get("UpDateInfo"));
        map.put("ZYCRKFW", info.getJSONObject("ZYCRKFW").get("UpDateInfo"));
        map.put("TCWPZYQ", info.getJSONObject("TCWPZYQ").get("UpDateInfo"));
        map.put("QT", info.getJSONObject("QT").get("UpDateInfo"));
        map.put("SZSSGHYQ", info.getJSONObject("SZSSGHYQ").get("UpDateInfo"));
        map.put("GGPTSSGHYQ", info.getJSONObject("GGPTSSGHYQ").get("UpDateInfo"));
        map.put("RF", info.getJSONObject("RF").get("UpDateInfo"));
        map.put("HMCSGHYQ", info.getJSONObject("HMCSGHYQ").get("UpDateInfo"));
        map.put("LSJZYQ", info.getJSONObject("LSJZYQ").get("UpDateInfo"));
        map.put("DLXGYQ", info.getJSONObject("DLXGYQ").get("UpDateInfo"));
        map.put("QTGHYQ", info.getJSONObject("QTGHYQ").get("UpDateInfo"));
        map.put("FT", info.getJSONObject("FT").get("UpDateInfo"));

        // 地块信息，涉及到多地块问题
        Map<String, Object> singleLand = new HashMap<>();
        Map<String, Object> multiLand = new HashMap<>();
        if (data.size() > 1) {
            // 多地块，先获取各个地块的面积
            List<Map> subList = new ArrayList<>();
            Double ydzmj = 0.0;
            Double rjlzjzmj = 0.0;
            for (Object d : data) {
                Map<String, Object> subMap = new HashMap<>();
                JSONObject d1 = (JSONObject) d;
                Double ydmj = d1.getJSONObject("YDMJ").getDouble("Value");
                String landNum = d1.getJSONObject("DKBH").getString("Value");
                Double rjl = d1.getJSONObject("RJL").getDouble("Value");
                Integer jzmd = d1.getJSONObject("JZMD").getInteger("Value");
                Integer jzxg = d1.getJSONObject("JZXG").getInteger("Value");
                String ydghlx = d1.getJSONObject("YDGHLX").getString("Value");
                String ydghlxdm = d1.getJSONObject("YDGHLXDM").getString("Value");

                double ydmj_mu = BigDecimal.valueOf(ydmj * 0.0015)
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue();

                String ydmj_mu1 = String.format(String.valueOf(ydmj * 0.0015), "");

                subMap.put("YDMJ_MI", ydmj);
                subMap.put("YDMJ_MU", ydmj_mu);
                subMap.put("DKBH", landNum);
                subMap.put("RJL", rjl);
                subMap.put("JZMD", jzmd);
                subMap.put("RJLJZMJ", ydmj * rjl);
                subMap.put("JZXG", jzxg);
                subMap.put("YDGHLX", ydghlx);
                subMap.put("YDGHLXDM", ydghlxdm);
                subMap.put("NEXT_LINE", "\n    ");

                subList.add(subMap);

                ydzmj += ydmj;
                rjlzjzmj += (ydmj * rjl);
            }

            double ydzmj_mu = BigDecimal.valueOf(ydzmj * 0.0015).setScale(2, RoundingMode.HALF_UP).doubleValue();

            multiLand.put("YDZMJ_MI", ydzmj);
            multiLand.put("YDZMJ_MU", ydzmj_mu);
            multiLand.put("RJLZJZMJ", rjlzjzmj);
            multiLand.put("MULTI_SUB", subList);
            map.put("MULTI", multiLand);
        } else {
            // 单地块
            JSONObject sub = (JSONObject) data.get(0);
            String dkbh = sub.getJSONObject("DKBH").getString("Value");
            String ydghlx = sub.getJSONObject("YDGHLX").getString("Value");
            String ydghlxdm = sub.getJSONObject("YDGHLXDM").getString("Value");
            Double rjl = sub.getJSONObject("RJL").getDouble("Value");
            Integer jzmd = sub.getJSONObject("JZMD").getInteger("Value");
            Integer jzxg = sub.getJSONObject("JZXG").getInteger("Value");
            Double ldl = sub.getJSONObject("LDL").getDouble("Value");
            Integer ydmj = sub.getJSONObject("YDMJ").getInteger("Value");

            double ydmj_mu = new BigDecimal(ydmj * 0.0015).setScale(2, RoundingMode.HALF_UP).doubleValue();

            singleLand.put("DKBH", dkbh);
            singleLand.put("YDGHLX", ydghlx);
            singleLand.put("YDGHLXDM", ydghlxdm);
            singleLand.put("RJL", rjl);
            singleLand.put("JZMD", jzmd);
            singleLand.put("JZXG", jzxg);
            singleLand.put("LDL", ldl);
            singleLand.put("YDMJ_MI", ydmj);
            singleLand.put("YDMJ_MU", ydmj_mu);
            singleLand.put("RJLZJZMJ", ydmj * rjl);
            map.put("SINGLE", singleLand);
        }

        XWPFTemplate.compile("Doc1.docx").render(map).writeAndClose(new FileOutputStream("output.docx"));
    }
}
