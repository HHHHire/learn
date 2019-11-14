package cn.edu.jxust.redislogin.service.impl;

import cn.edu.jxust.redislogin.entity.User;
import cn.edu.jxust.redislogin.repository.UserRepository;
import cn.edu.jxust.redislogin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author: ddh
 * @date: 2019/10/29  10:52
 * @description:
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final RedisTemplate redisTemplate;
    private final UserRepository userRepository;
    private ListOperations<String, String> list;

    @Autowired
    public LoginServiceImpl(RedisTemplate redisTemplate, UserRepository userRepository) {
        this.redisTemplate = redisTemplate;
        this.userRepository = userRepository;
        this.list = redisTemplate.opsForList();
    }

    @Override
    public String getString(String key) {
        ValueOperations<String, String> string = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)) {
            // 从redis中取出并返回
            System.out.println("redis:");
            System.out.println(string.get(key));
            return string.get(key);
        } else {
            String result = "test";
            string.set(key, result);
            System.out.println("mysql:");
            return result;
        }
    }

    @Override
    public User login(String userName, String password) {
//        Optional<User> user = userRepository.findByUserName(userName);
//        return user.orElse(null);
        loginValdate(userName);
        return null;
    }

    @Override
    public String loginValdate(String userName) {
        // 获取key
        String key = User.getLoginCountFailKey(userName);
        int num = 5;
        if (!redisTemplate.hasKey(key)) {
            // 如果不存在则创建，
            redisTemplate.opsForValue().set(key, "1");
            redisTemplate.expire(key, 2, TimeUnit.MINUTES);
            System.out.println("登陆失败，在两分钟内还允许输入错误" + (num - 1) + "次");
            return "登陆失败，在两分钟内还允许输入错误" + (num - 1) + "次";
        } else {
            // 如果存在，获取次数
            long loginFailCount = Long.parseLong(String.valueOf(redisTemplate.opsForValue().get(key)));
            if (loginFailCount < (num - 1)) {
                // 如果次数小于4，则次数加一，并且返回剩余时间
                redisTemplate.opsForValue().increment(key, 1);
                Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
                System.out.println(userName + "登陆失败在" + expire + "秒内还能登陆" + (num - loginFailCount) + "次");
                return userName + "登陆失败在" + expire + "秒内还能登陆" + (num - loginFailCount) + "次";
            } else {
                // 如果超过登陆次数 锁定key 1小时
                redisTemplate.opsForValue().set(User.getLoginTimeLockKey(userName), "1");
                redisTemplate.expire(User.getLoginTimeLockKey(userName), 1, TimeUnit.HOURS);
                System.out.println("因登录失败次数超过限制" + num + "次，现在限制登陆1小时");
                return "因登录失败次数超过限制" + num + "次，现在限制登陆1小时";
            }
        }
    }

    @Override
    public Map<String, Object> loginUserLock(String userName) {
        String key = User.getLoginTimeLockKey(userName);
        HashMap<String, Object> map = new HashMap<>();
        if (redisTemplate.hasKey(key)) {
            Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
            map.put("flag", true);
            map.put("time", expire);
        } else {
            map.put("flag", false);
        }
        return map;
    }

    /**
     * ###############################################  LIST
     * 淘宝订单
     * 1.付款完成后，生成订单队列
     */
    public void listQueueInit(String cardId) {
        // 初始化key待完成任务队列
        String key = "prod:" + cardId;
        list.leftPushAll(key, "1.商家出货", "2.发件", "3.北京-》浙江", "4.浙江-》瑞安", "5.瑞安—》目的地");
    }

    /**
     * 触发事件
     *
     * @param cardId
     */
    public void listQueueTouch(String cardId) {
        // 已完成任务队列
        String key = "prod:" + cardId + ":succ";
        // 把已完成的任务从原先队列中取出放入新队列
        list.rightPopAndLeftPush("prod:" + cardId, key);
    }

    /**
     * 客户查询快递到哪了
     *
     * @param cardId 订单号
     * @return List
     */
    public List<String> listQueueSucc(String cardId) {
        String key = "prod:" + cardId + ":succ";
        return list.range(key, 0, -1);
    }

    /**
     * 查看还有多少任务没有执行
     * @param cardId
     * @return
     */
    public List<String> listQueueWait(String cardId) {
        String key = "prod:" + cardId;
        return list.range(key, 0, -1);
    }
}
