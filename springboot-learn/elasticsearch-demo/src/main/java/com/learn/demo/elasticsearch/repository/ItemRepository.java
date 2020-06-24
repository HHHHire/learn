package com.learn.demo.elasticsearch.repository;

import com.learn.demo.elasticsearch.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author: ddh
 * @data: 2020/3/29 17:11
 * @description
 */
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

    List<Item> findByTitle(String title);

    List<Item> findByPriceBetween(Double d1, Double d2);
}
