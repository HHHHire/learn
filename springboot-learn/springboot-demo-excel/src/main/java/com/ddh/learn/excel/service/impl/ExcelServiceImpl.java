package com.ddh.learn.excel.service.impl;

import com.ddh.learn.excel.service.ExcelService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/14 13:16
 * @desc Excel有两个格式：xls xlsx(2007及以上版本)
 */
@SuppressWarnings("unused")
@Service
public class ExcelServiceImpl implements ExcelService {

    private static final String FILE_EXPORT_PATH = "F:\\test\\excel\\test.xlsx";

    @Override
    public void export(HttpServletResponse response) {
        // 创建表
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建样式
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        // 设置样式，居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 创建工作簿
        XSSFSheet sheet = workbook.createSheet("测试");
        // 模拟数据
        Map<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[]{"one", "two", "three"});
        data.put("2", new Object[]{1, 2, "three"});
        data.put("3", new Object[]{"hello", "world"});

        Set<String> keySet = data.keySet();
        int rowNum = 0;
        // 写入数据
        for (String s : keySet) {
            // 行
            XSSFRow row = sheet.createRow(rowNum++);
            Object[] objects = data.get(s);
            int cellNum = 0;
            // 单元格
            for (Object object : objects) {
                XSSFCell cell = row.createCell(cellNum++);
                if (object instanceof String) {
                    cell.setCellValue((String) object);
                } else if (object instanceof Integer) {
                    cell.setCellValue((Integer) object);
                }
                cell.setCellStyle(cellStyle);
            }
        }

        // 输出
        try {
//            File exportFile = new File(FILE_EXPORT_PATH);
//            FileOutputStream out = new FileOutputStream(exportFile);
            // 输出到网页
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode("test.xlsx", "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void importExcel(MultipartFile multipartFile) {
        try {
            // 通过文件流创建表
            XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
            int numberOfSheets = workbook.getNumberOfSheets();
            // 遍历工作簿
            for (int i = 0; i < numberOfSheets; i++) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                // 逐行遍历
                for (Row row : sheet) {
                    // 单元格遍历
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        CellType cellTypeEnum = cell.getCellTypeEnum();
                        switch (cellTypeEnum) {
                            case STRING: {
                                System.out.println(cell.getStringCellValue());
                                break;
                            }
                            case NUMERIC: {
                                System.out.println(cell.getNumericCellValue());
                                break;
                            }
                            default:
                                throw new IllegalStateException("Unexpected value: " + cellTypeEnum);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
