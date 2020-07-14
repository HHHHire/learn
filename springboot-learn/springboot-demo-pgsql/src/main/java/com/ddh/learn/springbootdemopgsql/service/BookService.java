package com.ddh.learn.springbootdemopgsql.service;

import com.ddh.learn.springbootdemopgsql.dao.BookRepository;
import com.ddh.learn.springbootdemopgsql.model.Book;
import com.ddh.learn.springbootdemopgsql.model.dto.BookDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 10:28
 * @description
 */
@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(BookDTO bookDTO) {
        Book build = Book.builder().title(bookDTO.getTitle())
                .description(bookDTO.getDescription())
                .favCount(bookDTO.getFavCount())
                .author(bookDTO.getAuthor())
                .type(bookDTO.getType()).build();
        return bookRepository.save(build);
    }

    public Boolean updateBook(BookDTO bookDTO) {
        Book bookDb = getBook(bookDTO.getId());
        if (bookDb.getId() != null) {
            bookDb.setTitle(bookDTO.getTitle() == null ? bookDb.getTitle() : bookDTO.getTitle());
            bookDb.setType(bookDTO.getType() == null ? bookDb.getType() : bookDTO.getType());
            bookDb.setDescription(bookDTO.getDescription() == null ? bookDb.getDescription() : bookDTO.getDescription());
            bookDb.setFavCount(bookDTO.getFavCount() == null ? bookDb.getFavCount() : bookDTO.getFavCount());
            bookDb.setAuthor(bookDTO.getAuthor() == null ? bookDb.getAuthor() : bookDTO.getAuthor());
            return bookRepository.save(bookDb) != null;
        }
        return false;
    }

    public Book getBook(Long id) {
        if (id == null) {
            return null;
        }
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteBook(Long id) {
        if (id == null) {
            return;
        }
        bookRepository.deleteById(id);
    }

    /**
     * 动态查询，模糊查询和查找大于fav的交集，排序
     *
     * @param title 关键字
     * @param fav   收藏数
     * @return 结果匹配的集合
     */
    public List<Book> findByConditions(String title, Integer fav) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return bookRepository.findAll((Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            // 模糊查询
            if (title != null) {
                predicateList.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
            }
            // 查找大于fav的
            if (fav != null) {
                predicateList.add(criteriaBuilder.gt(root.get("favCount"), fav));
            }
            // 结果取交集
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        }, sort);
    }
}
