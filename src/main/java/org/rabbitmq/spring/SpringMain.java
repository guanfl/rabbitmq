/**
* ClassName : SpringMain.java
* Create on ：2016年5月23日
* Copyrights 2016 guanfl All rights reserved.
* Email : guanfl@163.com
*/
package org.rabbitmq.spring;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("resource")
public class SpringMain {

    public static void main(String[] args) throws InterruptedException {
        
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/spring-rabbitmq.xml");
        
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        
        //发送消息
        template.convertAndSend("Hello,spring-amqp,spring-rabbit");
        TimeUnit.SECONDS.sleep(1);
        //销毁容器
        ctx.destroy();
    }

}
