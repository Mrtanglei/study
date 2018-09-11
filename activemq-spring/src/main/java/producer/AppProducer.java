package producer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tanglei
 * @date 18/8/16 上午9:48
 */
public class AppProducer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:producer.xml");
        ProducerService producerService = context.getBean(ProducerService.class);
        for (int i = 1; i <= 10; i++) {
            producerService.sendMessageForQueue("ActiveMQ-Spring=" + i);
            producerService.sendMessageForTopic("ActiveMQ-Spring=" + i);
        }
        context.close();
    }
}
