package test;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/1/25 9:21
 */
@Component
public class TestException implements TestExc {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer testException(Integer count) {
        throwE();
        return ++count;
    }

    private void throwE() {
        throw new RuntimeException();
    }
}
