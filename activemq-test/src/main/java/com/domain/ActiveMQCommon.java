package com.domain;

public class ActiveMQCommon {

    public static final String PRODUCER_URL = "failover:(tcp://127.0.0.1:61617,tcp://127.0.0.1:61618)?randomize=true";

    public static final String CONSUMER_URL = "failover:(tcp://127.0.0.1:61616,tcp://127.0.0.1:61617,tcp://127.0.0.1:61618)?randomize=true";

    public static final String TOPIC_NAME = "topic-test";

    public static final String QUEUE_NAME = "queue-test1";
}
