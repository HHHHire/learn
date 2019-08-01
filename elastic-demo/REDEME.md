### elastic
springboot 默认使用springData ElasticSearch模块进行操作

索引：数据库  
类型：表  
文档：记录，元组  
属性：属性，列

**版本不适配**

***
springboot 默认支持两种技术来和ES交互
1. Jest(默认不生效) 需导入包
```xml
    <dependency>
        <groupId>io.searchbox</groupId>
        <artifactId>jest</artifactId>
        <version>5.3.3</version>
    </dependency>
```

```properties
spring.elasticsearch.jest.uris=http://192.168.1.118:9200
```
2. springData ElasticSearch
    1. client 节点信息clusterNodes:clusterName
    2. ElasticsearchTemplate 操作
    3. 编写一个ElasticsearchRepository 的子接口来操作ES
    