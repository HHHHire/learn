package cn.edu.jxust.springbootdemomongodb;

import cn.edu.jxust.springbootdemomongodb.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author: ddh
 * @data: 2019/12/11 19:55
 * @description
 **/
@SpringBootTest
class MongoTest1 {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Test
    void insertData() {
        for (int i = 0; i < 10; i++) {
            Article article = new Article();
            article.setTitle("MongoTemplate的基本使用");
            article.setAuthor("yinjihuan");
            article.setUrl("http://cxytiandi.com/blog/detail/" + i);
            article.setTags(Arrays.asList("java", "mongodb", "spring"));
            article.setVisitCount(0L);
            article.setAddTime(new Date());
            mongoTemplate.save(article);
        }
    }

    @Test
    void findData() {
        Query query;
        // 查询所有
        List<Article> all = mongoTemplate.findAll(Article.class);
        for (Article article : all) {
            System.out.println(article);
        }
        // 根据条件查询
        /*query = Query.query(Criteria.where("author").is("yinjihuan"));
        System.out.println(mongoTemplate.find(query, Article.class));*/
        // 模糊查询 author中包含a的
        /*query = Query.query(Criteria.where("author").regex("a"));
        System.out.println(mongoTemplate.find(query, Article.class));*/
        // or查询 查询author=json 或 visitCount = 0
        /*query = Query.query(Criteria.where("").orOperator(
                Criteria.where("author").is("json"),
                Criteria.where("visitCount").is(0)
        ));
        System.out.println(mongoTemplate.find(query, Article.class));*/
    }

    @Test
    void updateData() {
        Query query;
        Update update;

        // 修改第一条author=yinjihuan的title为mongoTemplate，visitCount为10
        // set 如果没有这个key则会创建，unset 则会删除这个key  rename 修改key的名字  inc("money", 10) 在之前的基础上加10
        query = Query.query(Criteria.where("author").is("yinjihuan"));
        update = Update.update("title", "mongoTemplate").set("visitCount", 10);
        mongoTemplate.updateFirst(query, update, Article.class);
        // 修改全部符合条件的
//        mongoTemplate.updateMulti(query, update, Article.class);

//        mongoTemplate.upsert(query, update, Article.class);

    }

    @Test
    void deleteData() {
        Query query;
        /*query = Query.query(Criteria.where("title").is("mongoTemplate"));
        mongoTemplate.remove(query, Article.class);*/

        // 删除集合 可以传类名或集合名称
//        mongoTemplate.dropCollection(Article.class);

        // 删除数据库
//        mongoTemplate.getDb().drop();

        // 删除第一条title=mongoTemplate的文档
        query = Query.query(Criteria.where("title").is("mongoTemplate"));
        mongoTemplate.findAndRemove(query, Article.class);
    }
}
