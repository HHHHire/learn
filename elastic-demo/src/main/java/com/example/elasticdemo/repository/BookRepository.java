package com.example.elasticdemo.repository;

import com.example.elasticdemo.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookRepository extends ElasticsearchRepository<Book, Integer> {

}
