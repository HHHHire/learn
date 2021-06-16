package com.ddh.learn.springbootdemopgsql.model.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/28 15:56
 * @description:
 */
@MappedSuperclass
public class BasePo implements Identifiable, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
