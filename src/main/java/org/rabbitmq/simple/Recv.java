/**
* ClassName : Recv.java
* Create on ：2016年5月23日
* Copyrights 2016 guanfl All rights reserved.
* Email : guanfl@163.com
*/
package org.rabbitmq.simple;

import java.io.IOException;

import org.rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;

/** 接收者 / 消费者模型 */
public class Recv {
    private static final String QUEUE_NAME = "hello";

    /**
     * <p>
     * The extra DefaultConsumer is a class implementing the Consumer interface
     * we'll use to buffer the messages pushed to us by the server.
     * </p>
     */
    public static void main(String args[]) {
        // 获取连接
        Connection connection = null;
        Channel channel = null;
        try {
            connection = ConnectionUtil.getConnection();
            // 获取通道
            channel = connection.createChannel();
            // 声明队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 定义队列消费者
            QueueingConsumer consumer = new QueueingConsumer(channel);
            // 监听队列
            channel.basicConsume(QUEUE_NAME, true, consumer);//自动确认消息消费状态

            // 阻塞式获取队列消息
            while (true) {
                Delivery delivery = consumer.nextDelivery();
                String recv = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + recv + "'");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ShutdownSignalException e) {
            e.printStackTrace();
        } catch (ConsumerCancelledException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
