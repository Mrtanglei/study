package producer;

/**
 * @author tanglei
 * @date 18/8/15 下午3:56
 */
public interface ProducerService {

    void sendMessageForQueue(String message);

    void sendMessageForTopic(String message);
}
