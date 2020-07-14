package com.ddh.learn.springbootdemopgsql.dao;

import com.ddh.learn.springbootdemopgsql.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 10:26
 * @description
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
}
