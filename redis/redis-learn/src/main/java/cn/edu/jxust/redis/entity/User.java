package cn.edu.jxust.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ddh
 * @date: 2019/10/28  20:13
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String userName;

    public static String getKeyName() {
        return "hash:";
    }
}
