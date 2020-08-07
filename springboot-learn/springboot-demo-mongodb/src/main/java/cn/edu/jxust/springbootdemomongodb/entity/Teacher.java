package cn.edu.jxust.springbootdemomongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/3 15:16
 */
@Document("teacher")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private String id;
    private String tName;
    private String age;
    private String school;
}
