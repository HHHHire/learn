package com.example.springbootdemodockercompose.repository;

import com.example.springbootdemodockercompose.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: ddh
 * @data: 2019/11/16 9:43
 * @description
 **/
@Repository
public interface VisitorRepository extends JpaRepository<Visitor, String> {

}
