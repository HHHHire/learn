package com.example.springbootdemodockercompose.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author: ddh
 * @data: 2019/11/16 9:40
 * @description
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Visitor {
    @Id
    private String ip;
    private Integer times;
}
