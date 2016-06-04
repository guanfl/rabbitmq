/**
* ClassName : ConnectionUtil.java
* Create on ：2016年5月23日
* Copyrights 2016 guanfl All rights reserved.
* Email : guanfl@163.com
*/
package org.rabbitmq.util;

import java.io.IOException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
    public static Connection getConnection() throws IOException{
        //定义工厂方法
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("127.0.0.1");//host the default host to use for connections
        //设置端口
        factory.setPort(5672);
        //设置帐号信息
        factory.setVirtualHost("/rabbit");
        factory.setUsername("guanfl");
        factory.setPassword("guanjun@1990");
        Connection conn = factory.newConnection();
        return conn;
    }
}
