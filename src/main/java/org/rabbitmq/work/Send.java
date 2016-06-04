/**
* ClassName : Send.java
* Create on ：2016年5月23日
* Copyrights 2016 guanfl All rights reserved.
* Email : guanfl@163.com
*/
package org.rabbitmq.work;

import java.util.concurrent.TimeUnit;

import org.rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/** 信息发送者 */
public class Send {
    private final static String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 通过连接获取通道（channel）
        Channel channel = connection.createChannel();

        // 通过channel发送信息到MQ
        // 发送之前需要声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        for (int i = 0; i < 50; i++) {
            String massage = "" + i;
            // 发布消息到消息队列中
            channel.basicPublish("", QUEUE_NAME, null, massage.getBytes());
            // 发布成功
            System.out.println("[X] Send [" + massage + "]");
            TimeUnit.MILLISECONDS.sleep(i * 10);
        }
    }
}
