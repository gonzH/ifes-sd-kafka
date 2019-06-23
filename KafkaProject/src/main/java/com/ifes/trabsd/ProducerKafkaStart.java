package com.ifes.trabsd;

public class ProducerKafkaStart {
    static ProducerKafka producer = new ProducerKafka("192.168.88.129", "meuteste");
    
    public static void main(String[] args) {
        

        //send message
        //for(int i=0;i<10;i++) {
        producer.send(new CustomObject("1", "ULTIMO TESTE ANTES DO COMMIT", (0+1)));
        //}
        
        

    }
}
