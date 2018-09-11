package com.queue;

import com.domain.ActiveMQCommon;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 生产者
 */
public class AppProducer {

    public static void main(String[] args) throws JMSException {

        //创建连接工厂ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQCommon.PRODUCER_URL);

        //创建连接Connection
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();

        /**
         * 创建会话
         * 第一个参数：是否支持事物，如果为true则会忽略第二个参数，则jms服务器设置为SESSION_TRANSACTED
         * 第二个参数：当第一个参数为false时，参数值为：
         * Session.AUTO_ACKNOWLEDGE：为自动确认，客户端发送和结束消息不需要做额外的工作。哪怕是接收端发生异常，也会被当作正常发送成功
         * Session.CLIENT_ACKNOWLEDGE：为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会当作发送成功，并删除消息。
         * Session.DUPS_OK_ACKNOWLEDGE：允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认
         */
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //消息的目的地-Queue代表一个队列
        Queue destination = session.createQueue(ActiveMQCommon.QUEUE_NAME);

        //Destination 是Queue和Topic的基类
        //        Destination destination =session.createQueue(queueName);

        //创建一个生产者
        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i < 5; i++) {
            TextMessage textMessage = session.createTextMessage("test" + i);
            producer.send(textMessage);
            System.out.println("生产者发送消息=" + textMessage.getText());
        }
        connection.close();
    }
}
