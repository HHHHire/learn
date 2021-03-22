package com.ddh.learn.springbootdemopgsql.dao;

import com.ddh.learn.springbootdemopgsql.model.AuthorBook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/3/22 13:45
 */
public interface AuthorBookRepository extends JpaRepository<AuthorBook, Long> {

}
