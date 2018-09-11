package com.topic;

import com.domain.ActiveMQCommon;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppProducer {

    public static void main(String[] args) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQCommon.PRODUCER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(ActiveMQCommon.TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);
        for (int i = 1; i <= 10; i++) {
            producer.send(session.createTextMessage("topic-test=" + i));
        }
        connection.close();
        System.out.println("生产完毕");
    }
}
