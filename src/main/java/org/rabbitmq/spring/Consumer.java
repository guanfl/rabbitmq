/**
* ClassName : Consumer.java
* Create on ：2016年5月23日
* Copyrights 2016 guanfl All rights reserved.
* Email : guanfl@163.com
*/
package org.rabbitmq.spring;

public class Consumer {
    
    //执行具体的业务方法
    public void listen(String foo){
        System.out.println("========> running method call...");
    }
}
