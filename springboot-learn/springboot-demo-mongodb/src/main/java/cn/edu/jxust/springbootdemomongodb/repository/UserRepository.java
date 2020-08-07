package cn.edu.jxust.springbootdemomongodb.repository;

import cn.edu.jxust.springbootdemomongodb.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/3 14:32
 */
//@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
