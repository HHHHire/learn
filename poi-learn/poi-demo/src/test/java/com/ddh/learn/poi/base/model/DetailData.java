package com.ddh.learn.poi.base.model;

import com.deepoove.poi.data.RenderData;
import com.deepoove.poi.data.RowRenderData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/20 10:22
 * @description:
 */
@Data
@AllArgsConstructor
public class DetailData {
    private List<RowRenderData> goodsList;
    private List<RowRenderData> laborList;
}
