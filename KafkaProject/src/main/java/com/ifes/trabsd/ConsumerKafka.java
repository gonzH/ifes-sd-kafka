package com.ifes.trabsd;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class ConsumerKafka {
    private final Consumer<String, CustomObject> kafkaConsumer ;
    private final String recipient;
    private final String topicName;
    private final String groupName;

    public ConsumerKafka(String recipient, String topicName, String groupName) {
        this.recipient = recipient;
        this.topicName = topicName;
        this.groupName = groupName;
        
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", this.recipient+":9092");
        props.setProperty("group.id", this.groupName);
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");

        kafkaConsumer = new KafkaConsumer<String, CustomObject>(props, new StringDeserializer() ,new KafkaJsonDeserializer<CustomObject>(CustomObject.class));
    }

    public Consumer consume(){
        kafkaConsumer.subscribe(Collections.singletonList(this.topicName));
        
        return kafkaConsumer;
    }

}
