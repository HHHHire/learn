package com.ddh.learn.springbootdemopgsql.model.dto;

import com.ddh.learn.springbootdemopgsql.model.Author;
import lombok.Builder;
import lombok.Data;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 10:35
 * @description
 */
@Data
@Builder
public class BookDTO {

    private Long id;

    private String type;

    private String title;

    private String description;

    private Integer favCount;

    private Author author;
}
