package cn.edu.jxust.springbootdemomongodb.repository;

import cn.edu.jxust.springbootdemomongodb.entity.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/3 15:18
 */
public interface TeacherRepository extends MongoRepository<Teacher, String> {
}
