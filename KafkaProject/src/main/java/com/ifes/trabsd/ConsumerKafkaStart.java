package com.ifes.trabsd;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

public class ConsumerKafkaStart {
    static ConsumerKafka consumerK = new ConsumerKafka("192.168.88.129", "meuteste", "meuteste");
    
    public static void main(String[] args) {
        
        //consume and process consumed message
        final Consumer<String, CustomObject> consumer = consumerK.consume();

        boolean listening = true;
        int timer = 0;
        
        //consumer listen 100 seconds till stop waiting for messages
        while(listening) {
            final ConsumerRecords<String, CustomObject> consumerRecords = consumer.poll(1000);
            
            if (consumerRecords.count() == 0) {
                timer++;
                
                if (timer > 100) {
                    System.out.println("saiu");
                    listening = false;
                }
            }
            
            for (ConsumerRecord<String, CustomObject> record : consumerRecords) {
                System.out.println("Message received " + record.value());
            }

            consumer.commitAsync();
        }

        consumer.close();

    }
}
