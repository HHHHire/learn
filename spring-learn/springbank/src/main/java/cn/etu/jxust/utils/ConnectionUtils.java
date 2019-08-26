package cn.etu.jxust.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author ddh
 * @date 2019/8/26 10:14
 * @description
 **/
public class ConnectionUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     */
    public Connection getThreadConnection(){
        try {
            // 先从 ThreadLocal 上获取
            Connection connection = tl.get();
            // 判断当前线程上是否有连接
            if (connection == null){
                // 从数据源中获取一个连接，并存入 ThreadLocal 中
                connection = dataSource.getConnection();
                tl.set(connection);
            }
            // 返回当前线程上的连接
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 与当前线程解绑
     */
    public void removeConnection(){
        tl.remove();
    }
}
