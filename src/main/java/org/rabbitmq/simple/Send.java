/**
* ClassName : Send.java
* Create on ：2016年5月23日
* Copyrights 2016 guanfl All rights reserved.
* Email : guanfl@163.com
*/
package org.rabbitmq.simple;

import java.io.IOException;

import org.rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/** 信息发送者 */
public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;

        try {
            // 获取连接
            connection = ConnectionUtil.getConnection();
            // 通过连接获取通道（channel）
            channel = connection.createChannel();

            // 通过channel发送信息到MQ
            // 发送之前需要声明队列
            /**
             * <p>
             * queue the name of the queuedurable true if we are declaring a
             * durable queue (the queue will survive a server restart)exclusive
             * true if we are declaring an exclusive queue (restricted to this
             * connection)autoDelete true if we are declaring an autodelete
             * queue (server will delete it when no longer in use)arguments
             * other properties (construction arguments) for the queue
             * </p>
             */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String massage = "Hello RabbitMQ";
            // 发布消息到消息队列中
            channel.basicPublish("", QUEUE_NAME, null, massage.getBytes());
            // 发布成功
            System.out.println("[X] Send [" + massage + "]");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
