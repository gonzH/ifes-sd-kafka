package com.ifes.trabsd;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerKafka {
    private final Producer<String, CustomObject> kafkaProducer;
    private final String recipient;
    private final String topicName;

    public ProducerKafka(String recipient, String topicName) {
        this.recipient = recipient;
        this.topicName = topicName;
        
        Properties props = new Properties();
        props.put("bootstrap.servers", this.recipient+":9092");

        kafkaProducer = new KafkaProducer<String, CustomObject>(props, new StringSerializer(), new KafkaJsonSerializer());
    }

    public void send(CustomObject res) {
        kafkaProducer.send(new ProducerRecord<String, CustomObject>(this.topicName, res));
        kafkaProducer.flush();
        kafkaProducer.close();
        System.out.println(".send() Done! ");
    }

}
