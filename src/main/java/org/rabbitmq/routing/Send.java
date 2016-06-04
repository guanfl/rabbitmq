/**
* ClassName : Send.java
* Create on ：2016年5月23日
* Copyrights 2016 guanfl All rights reserved.
* Email : guanfl@163.com
*/
package org.rabbitmq.routing;

import org.rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/** 信息发送者 */
public class Send {
    // 交换机名称
    private final static String EXCHANGE_NAME = "exchange_direct";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 通过连接获取通道（channel）
        Channel channel = connection.createChannel();

        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String massage = "id=9999的商品删除了";
        // 发布消息到交换机
        channel.basicPublish(EXCHANGE_NAME, "delete", null, massage.getBytes()); // channel.basicPublish发布消息的时候带有routingKey
        // 发布成功
        System.out.println("[X] Send [" + massage + "]");

        channel.close();
        connection.close();
    }
}
