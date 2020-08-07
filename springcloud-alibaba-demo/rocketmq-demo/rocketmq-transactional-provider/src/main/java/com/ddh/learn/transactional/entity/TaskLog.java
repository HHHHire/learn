package com.ddh.learn.transactional.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/6 17:25
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskLog {
    @Id
    private String taskId;
    private String taskMsg;
    private Date createTime;
}
