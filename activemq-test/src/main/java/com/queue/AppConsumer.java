package com.queue;

import com.domain.ActiveMQCommon;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消费者
 */
public class AppConsumer {

    public static void main(String[] args) throws JMSException {

        //建立工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQCommon.CONSUMER_URL);

        //建立连接
        Connection connection = connectionFactory.createConnection();

        connection.start();

        //建立会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //建立目标
        Queue queue = session.createQueue(ActiveMQCommon.QUEUE_NAME);

        //创建消费者
        MessageConsumer consumer = session.createConsumer(queue);

        //创建监听器
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接收消息=" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
