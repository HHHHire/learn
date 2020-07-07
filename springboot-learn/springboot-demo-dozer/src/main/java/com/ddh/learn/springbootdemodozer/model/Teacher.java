package com.ddh.learn.springbootdemodozer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dozer.Mapping;

import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/7 13:43
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Mapping("username")
    private String teacherName;
    private String address;
    private Double age;
    private List<Phone> phone;
}
