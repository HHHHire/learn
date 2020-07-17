package com.ddh.learn.springbootdemopgsql.dao;

import com.ddh.learn.springbootdemopgsql.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 10:26
 * @description
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    /**
     * 查询一个book对象
     * @param id
     * @return
     */
    @Query(value = "from Book b where b.id = ?1")
    Book searchById(Long id);
}
