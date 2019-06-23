package com.ifes.trabsd;

import java.io.FileNotFoundException;

public class ProducerKafkaStart {
    static ProducerKafka producer = new ProducerKafka("192.168.88.129", "meuteste");
    
    public static void main(String[] args) throws FileNotFoundException {
        DataGenerator dataGen = new DataGenerator();

        //send message
        for(int i = 0; i < 3; i++) {
            producer.send(new CustomObject(dataGen.nextTemp(), dataGen.nextHumi(), dataGen.nextPress(), dataGen.nextDate()));
        }
        
        producer.close();
        
    }
}
