package com.ddh.learn.redis;

import com.ddh.learn.redis.model.UserDTO;
import com.ddh.learn.redis.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/12 19:07
 */
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private TestUtil testUtil;

    @Test
    public void geoAdd() {
        Map<Object, Point> map = new HashMap<>();
        map.put("beijing", new Point(116.405285, 39.904989));
        map.put("dist", new Point(121.607769, 31.187716));
        map.put("shitang", new Point(121.607664, 31.189435));
        map.put("shijigongyuan", new Point(121.552081, 31.215340));
        map.put("zhengda", new Point(121.499328, 31.236926));
        Point point = new Point(116.405285, 39.904989);
        redisTemplate.opsForGeo().add("city", map);
    }

    @Test
    public void geoGet() {
        List<Point> position = redisTemplate.opsForGeo().position("city", "dist", "shitang");
        System.out.println(position);
    }

    @Test
    public void geoGetDistance() {
        Distance distance = redisTemplate.opsForGeo().distance("city", "dist", "shitang", Metrics.KILOMETERS);
        System.out.println(distance);
        assert distance != null;
        System.out.println(distance.getValue() + distance.getUnit());
    }

    @Test
    public void geoNearByXY() {
        Circle circle = new Circle(121.606599,31.188449, Metrics.KILOMETERS.getMultiplier());
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<Object>> results = redisTemplate.opsForGeo()
                .radius("city",circle,args);
        System.out.println(results);
    }

    @Test
    public void geoNearByDistance() {
        Distance distance = new Distance(15,Metrics.KILOMETERS);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<Object>>  results = redisTemplate.opsForGeo()
                .radius("city","dist",distance,args);
        System.out.println(results);
    }

    @Test
    public void geoGetHash() {
        List<String> hash = redisTemplate.opsForGeo().hash("city", "dist");
        System.out.println(hash);
    }

    @Test
    public void testList() {
        UserDTO userDTO = UserDTO.builder().id(111L).name("zhangsan").build();
        BoundListOperations<String, Object> test = redisTemplate.boundListOps("TEST");
        test.leftPush(userDTO);
    }

    @Test
    public void testValue() {
        TestUtil testUtil = new TestUtil();
        System.out.println(testUtil.value);
    }
}
