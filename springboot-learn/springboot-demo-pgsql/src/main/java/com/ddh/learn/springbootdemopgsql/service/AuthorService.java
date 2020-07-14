package com.ddh.learn.springbootdemopgsql.service;

import com.ddh.learn.springbootdemopgsql.dao.AuthorRepository;
import com.ddh.learn.springbootdemopgsql.model.Author;
import com.ddh.learn.springbootdemopgsql.model.dto.AuthorDTO;
import org.springframework.stereotype.Service;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 10:42
 * @description
 */
@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author createAuthor(AuthorDTO authorDTO) {
        Author build = Author.builder().name(authorDTO.getName())
                .hometown(authorDTO.getHometown()).build();
        return authorRepository.save(build);
    }
}
