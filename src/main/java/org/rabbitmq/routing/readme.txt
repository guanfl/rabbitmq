routing模式：
同样是一个生产者，多个消费者且不直接将消息发布到队列中，而是发布到交换机中
但是不同的是，当队列绑定到交换机的时候，会多一个routingkey

生产者发布消息时候也会带有routingkey这个标识！！~

exchange type：交换机的类型
1、direct
2、fanout
3、headers
4、topic

消息生产者
1、需要声明交换机的类型
2、在发布消息的时候需要带上routingkey

消息消费者
1、在绑定队列到交换机的时候，需要指定routingkey,从而来实现路由功能

