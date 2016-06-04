发布订阅模型
producer --->(x)--->(queue)-->consumer
			    --->(queue)-->consumer
同样是一个生产者，多个消费者
与work模型不同的是，生产者发送消息到统一的订阅平台（交换机exchanger），每个消费这都有一份消息的拷贝
		消费者拿到的是所有生产者的消息，
		work模型消费者只能拿到部分消息
		
1、一个生产者，多个消费者
2、每个消费者都有自己的队列
3、生产者没有将消息直接发送到队列，而是发送到了交换机
4、每个队列都要绑定到交换机
5、生产者发送消息，经过交换机，到达

生产者：
1、获取连接 getConnection();
2、获取通道 connection.createChannel();
3、声明交换机 channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
4、发布消息到交换机
channel.basicPublish(EXCHANGE_NAME, "", null, massage.getBytes());

消息发送到没有绑定队列的交换机时，消息将丢失。因为，交换机没有存储数据的能力，消息只能存在队列中。

消费者：
1、获取连接 getConnection();
2、获取通道 connection.createChannel();
3、声明队列 channel.queueDeclare(QUEUE_NAME, false, false, false, null);
4、绑定队列到交换机：channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
5、声明消费者： QueueingConsumer consumer = new QueueingConsumer(channel);
6、监听队列
7、获取数据
