package com.learn.demo.elasticsearch;

import com.learn.demo.elasticsearch.pojo.Item;
import com.learn.demo.elasticsearch.repository.ItemRepository;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MultiMatchQuery;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: ddh
 * @data: 2020/3/29 16:48
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchApplicationTest {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testIndex() {
        // 删除索引
//        elasticsearchTemplate.deleteIndex(Item.class);

        // 创建索引库 (数据库)
        elasticsearchTemplate.createIndex(Item.class);
        // 创建映射
        elasticsearchTemplate.putMapping(Item.class);

    }

    @Test
    public void testCrud() {
        Item item = new Item(2L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.leyou.com/13123.jpg");
        // 新增或更新文档 (记录)
        itemRepository.save(item);
        /*// 新增一个集合
        itemRepository.saveAll(null);*/

        // 删除文档
//        itemRepository.delete(item);

//        List<Item> list = new ArrayList<>();
//        list.add(new Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.leyou.com/123.jpg"));
//        list.add(new Item(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.leyou.com/3.jpg"));
//        // 接收对象集合，实现批量新增
//        itemRepository.saveAll(list);
    }

    @Test
    public void testQuery() {
        /*Optional<Item> item = itemRepository.findById(1L);
        System.out.println(item.get());*/

//        Iterable<Item> items = itemRepository.findAll(Sort.by("price").descending());
//        items.forEach(System.out::println);

        // 自定义方法
//        List<Item> it = itemRepository.findByTitle("手机");
//        it.forEach(System.out::println);

        // 自定义方法
        List<Item> byPriceBetween = itemRepository.findByPriceBetween(3399d, 3499d);
        byPriceBetween.forEach(System.out::println);
    }

    @Test
    public void indexList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
    }

    /**
     * 高级查询
     */
    @Test
    public void testSearch() {
        // 通过查询构建器工具构建查询条件
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "小米");
        // 执行查询，获取结果集
        Iterable<Item> search = itemRepository.search(matchQueryBuilder);
        search.forEach(System.out::println);
    }

    @Test
    public void testNative() {
        // 构建自定义查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本的查询条件
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "手机"));
        // 执行查询，获取结果集
        Page<Item> search = itemRepository.search(queryBuilder.build());
        System.out.println(search.getTotalPages());
        System.out.println(search.getTotalElements());
        search.getContent().forEach(System.out::println);
    }

    @Test
    public void testPage() {
        // 构建自定义查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本的查询条件
        queryBuilder.withQuery(QueryBuilders.matchQuery("category", "手机"));
        // 添加分页条件，页码是从零开始的
//        queryBuilder.withPageable(PageRequest.of(1, 2));
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        // 执行查询，获取结果集
        Page<Item> search = itemRepository.search(queryBuilder.build());
        System.out.println(search.getTotalPages());
        System.out.println(search.getTotalElements());
        search.getContent().forEach(System.out::println);
    }

    /**
     * 聚合查询
     */
    @Test
    public void testAggs() {
        // 构建自定义查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加聚合
        queryBuilder.addAggregation(AggregationBuilders.terms("brandAgg").field("brand"));
        // 添加结果集过滤，不包括任何字段
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{}, null));
        // 执行聚合查询
        AggregatedPage<Item> search = (AggregatedPage<Item>)itemRepository.search(queryBuilder.build());
        // 解析聚合结果集，根据聚合的类型及字段类型要进行强转，brand-是字符串类型的，聚合类型-词条聚合，brandAgg-通过聚合名称获取集合对象
        StringTerms brandAgg = (StringTerms)search.getAggregation("brandAgg");
        // 获取桶的集合
        List<StringTerms.Bucket> buckets = brandAgg.getBuckets();
        buckets.forEach(bucket -> {
            System.out.println(bucket.getKeyAsString());
            System.out.println(bucket.getDocCount());
        });
    }

    @Test
    public void testSubAggs() {
        // 构建自定义查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加聚合
        queryBuilder.addAggregation(AggregationBuilders.terms("brandAgg").field("brand")
                    .subAggregation(AggregationBuilders.avg("price_avg").field("price")));
        // 添加结果集过滤，不包括任何字段
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{}, null));
        // 执行聚合查询
        AggregatedPage<Item> search = (AggregatedPage<Item>)itemRepository.search(queryBuilder.build());
        // 解析聚合结果集，根据聚合的类型及字段类型要进行强转，brand-是字符串类型的，聚合类型-词条聚合，brandAgg-通过聚合名称获取集合对象
        StringTerms brandAgg = (StringTerms)search.getAggregation("brandAgg");
        // 获取桶的集合
        List<StringTerms.Bucket> buckets = brandAgg.getBuckets();
        buckets.forEach(bucket -> {
            System.out.println(bucket.getKeyAsString());
            System.out.println(bucket.getDocCount());
            // 获取子集的map集合：key-聚合名称，value-对应的聚合子集
            Map<String, Aggregation> stringAggregationMap = bucket.getAggregations().asMap();
            InternalAvg price_avg = (InternalAvg)stringAggregationMap.get("price_avg");
            System.out.println(price_avg.getValue());
        });
    }
}
