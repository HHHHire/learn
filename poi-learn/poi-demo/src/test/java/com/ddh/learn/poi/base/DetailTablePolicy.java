package com.ddh.learn.poi.base;

import com.ddh.learn.poi.base.model.DetailData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.util.TableTools;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/20 9:37
 * @description:
 */
public class DetailTablePolicy extends DynamicTableRenderPolicy {

    private int goodsStartRow;
    private int laborStartRow;


    public DetailTablePolicy(int goodsStartRow, int laborStartRow) {
        this.goodsStartRow = goodsStartRow;
        this.laborStartRow = laborStartRow;
    }

    public void render(XWPFTable table, Object data) throws Exception {
        if (!(data instanceof DetailData)) {
            return;
        }
        DetailData detailData = (DetailData) data;
        // 人工费
        List<RowRenderData> laborList = detailData.getLaborList();
        if (CollectionUtils.isEmpty(laborList)) {
            return;
        }
        table.removeRow(laborStartRow);
        laborList.forEach(labor -> {
            XWPFTableRow xwpfTableRow = table.insertNewTableRow(laborStartRow);
            for (int i = 0; i < 7; i++) {
                xwpfTableRow.createCell();
            }
            // 合并单元格
            TableTools.mergeCellsHorizonal(table, laborStartRow, 0, 3);
            try {
                TableRenderPolicy.Helper.renderRow(table.getRow(laborStartRow), labor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        // 货物
        List<RowRenderData> goodsList = detailData.getGoodsList();
        if (CollectionUtils.isEmpty(goodsList)) {
            return;
        }
        // 移除第一行
        table.removeRow(goodsStartRow);
        goodsList.forEach(goods -> {
            XWPFTableRow xwpfTableRow = table.insertNewTableRow(goodsStartRow);
            for (int j = 0; j < 7; j++) {
                xwpfTableRow.createCell();
            }
            try {
                TableRenderPolicy.Helper.renderRow(table.getRow(goodsStartRow), goods);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
