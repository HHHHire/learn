package com.ddh.learn.springbootdemopgsql.dao;

import com.ddh.learn.springbootdemopgsql.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 10:27
 * @description
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {
}
