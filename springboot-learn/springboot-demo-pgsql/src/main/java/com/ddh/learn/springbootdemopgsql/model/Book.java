package com.ddh.learn.springbootdemopgsql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 10:04
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String title;

    @Column(columnDefinition = "text")
    private String description;

    /**
     * 收藏数
     */
    @Column(name = "fav_count")
    private Integer favCount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @CreatedDate
    private String createTime;

    @LastModifiedDate
    private String updateTime;
}
