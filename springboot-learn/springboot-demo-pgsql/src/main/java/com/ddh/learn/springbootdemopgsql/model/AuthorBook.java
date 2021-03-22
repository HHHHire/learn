package com.ddh.learn.springbootdemopgsql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/3/22 13:43
 */
@Entity
@Table(name = "author_book")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorBook implements Serializable {
    private Long id;
    private Long authorId;
    private Long bookId;
}
