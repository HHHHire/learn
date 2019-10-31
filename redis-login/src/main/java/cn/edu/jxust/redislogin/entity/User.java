package cn.edu.jxust.redislogin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author: ddh
 * @date: 2019/10/29  15:12
 * @description:
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    private String id;
    private String userName;
    private String password;

    public static String getKeyName() {
        return "user:";
    }

    /**
     * 锁定限制登陆key: user:loginTime:lock:用户名
     * @param userName 用户名
     * @return String
     */
    public static String getLoginTimeLockKey(String userName) {
        return "user:loginTime:lock:" + userName;
    }

    /**
     * 登陆失败次数key: user:loginCount:fail:用户名
     * @param userName 用户名
     * @return String
     */
    public static String getLoginCountFailKey(String userName) {
        return "user:loginCount:fail:" + userName;
    }
}
