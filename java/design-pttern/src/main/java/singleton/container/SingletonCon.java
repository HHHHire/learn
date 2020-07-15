package singleton.container;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 15:02
 * @desc 容器模式，非线程安全。
 */
@SuppressWarnings("unused")
public class SingletonCon {
    private static Map<String, Object> map = new HashMap<>();

    private SingletonCon() {
    }

    private static void register(String key, Object value) {
        if (key != null && !key.isEmpty() && value != null) {
            if (!map.containsKey(key)) {
                map.put(key, value);
            }
        }
    }

    private static Object getObject(String key) {
        return map.get(key);
    }
}
