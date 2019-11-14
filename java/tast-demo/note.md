### 异步(async)
1. @EnableAsync 开启异步注解功能
2. @Async 异步方法

### 定时(Scheduled)
1. @EnableScheduling 开启定时注解功能
2. @Scheduled(cron = "0 * * * * MON-FRI") 指定周一到周五每分钟执行一次 
   
   
### 邮件()
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>
```

