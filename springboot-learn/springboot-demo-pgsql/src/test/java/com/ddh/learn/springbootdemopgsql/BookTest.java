package com.ddh.learn.springbootdemopgsql;

import com.ddh.learn.springbootdemopgsql.model.Author;
import com.ddh.learn.springbootdemopgsql.model.Book;
import com.ddh.learn.springbootdemopgsql.model.dto.AuthorDTO;
import com.ddh.learn.springbootdemopgsql.model.dto.BookDTO;
import com.ddh.learn.springbootdemopgsql.service.AuthorService;
import com.ddh.learn.springbootdemopgsql.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 10:39
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootDemoPgsqlApplication.class)
public class BookTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Test
    public void test1() {
        AuthorDTO authorDTO = AuthorDTO.builder().name("东野圭吾")
                .hometown("tokyo").build();
        Author author = authorService.createAuthor(authorDTO);

        BookDTO bookDTO = BookDTO.builder().title("白夜行")
                .description("我的世界里没有白天")
                .favCount(100)
                .author(author)
                .type("悬疑").build();
        bookService.createBook(bookDTO);
    }

    @Test
    public void test2() {
        AuthorDTO authorDTO = AuthorDTO.builder().name("东野圭吾2")
                .hometown("tokyo2").build();
        Author author = authorService.createAuthor(authorDTO);

        BookDTO bookDTO = BookDTO.builder().title("白夜行2")
                .id(2L)
                .description("我的世界里没有白天2")
                .favCount(1002)
                .author(author)
                .type("悬疑2").build();
        bookService.updateBook(bookDTO);
    }

    @Test
    public void test3() {
        List<Book> books = bookService.findByConditions("白", 100);
        System.out.println(books);
    }

    @Test
    public void test4() {
        Book book = bookService.searchBookById(1L);
        System.out.println(book);
    }
}
