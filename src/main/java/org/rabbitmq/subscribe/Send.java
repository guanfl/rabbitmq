/**
* ClassName : Send.java
* Create on ：2016年5月23日
* Copyrights 2016 guanfl All rights reserved.
* Email : guanfl@163.com
*/
package org.rabbitmq.subscribe;

import java.util.concurrent.TimeUnit;

import org.rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/** 信息发送者 */
public class Send {
    //交换机名称
    private final static String EXCHANGE_NAME = "exchange_fanout";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 通过连接获取通道（channel）
        Channel channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        for (int i = 0; i < 50; i++) {
            String massage = "" + i;
            // 发布消息到交换机
            channel.basicPublish(EXCHANGE_NAME, "", null, massage.getBytes());
            // 发布成功
            System.out.println("[X] Send [" + massage + "]");
            TimeUnit.MILLISECONDS.sleep(i * 10);
        }
        
        channel.close();
        connection.close();
    }
}
