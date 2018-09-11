package producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * @author tanglei
 * @date 18/8/15 下午3:57
 */
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource(name = "activeMQQueue")
    Destination activeMQQueue;

    @Resource(name = "activeMQTopic")
    Destination activeMQTopic;

    public void sendMessageForQueue(final String message) {
        jmsTemplate.send(activeMQQueue, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                return textMessage;
            }
        });
        System.out.println("producer send message=" + message);
    }

    public void sendMessageForTopic(final String message) {
        jmsTemplate.send(activeMQTopic, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                return textMessage;
            }
        });
    }
}
