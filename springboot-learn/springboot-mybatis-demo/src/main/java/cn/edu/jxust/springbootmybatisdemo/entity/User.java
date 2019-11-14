package cn.edu.jxust.springbootmybatisdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ddh
 * @date: 2019/10/31  22:46
 * @description:
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String userName;
    private String password;
}