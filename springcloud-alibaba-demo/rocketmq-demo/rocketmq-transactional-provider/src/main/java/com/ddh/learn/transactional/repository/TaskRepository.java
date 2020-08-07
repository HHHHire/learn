package com.ddh.learn.transactional.repository;

import com.ddh.learn.transactional.entity.TaskLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/6 17:26
 */
public interface TaskRepository extends JpaRepository<TaskLog, String> {
}
