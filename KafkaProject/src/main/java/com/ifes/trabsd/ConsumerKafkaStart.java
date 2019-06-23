package com.ifes.trabsd;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

public class ConsumerKafkaStart {
    static ConsumerKafka consumerKfk = new ConsumerKafka("192.168.88.129", "meuteste", "meuteste");
    static final ConnectPostgres cp = new ConnectPostgres();
    
    public static void main(String[] args) {
        
        //consume and process consumed message
        final Consumer<String, CustomObject> consumer = consumerKfk.consume();

        boolean listening = true;
        int timer = 0;
        String query;
        //consumer listen 20 seconds till stop waiting for messages
        while(listening) {
            final ConsumerRecords<String, CustomObject> consumerRecords = consumer.poll(1000);
            
            if (consumerRecords.count() == 0) {
                timer++;
                
                if (timer > 20) {
                    System.out.println("saiu");
                    listening = false;
                }
            }
            
            for (ConsumerRecord<String, CustomObject> record : consumerRecords) {
                System.out.println("Message received " + record.value());

                query = "INSERT INTO variaveis (temperatura, umidade, pressao, coletado) VALUES (" +
                        record.value().getTemperature() + ", " + 
                        record.value().getHumidity() + ", " + 
                        record.value().getPressure() + ", " + 
                        "'" + record.value().getDate() + "'" + ");";
                cp.executeSql(query);
            }   
            
            consumer.commitAsync();
        }
        cp.closeStatement();
        cp.closeConnection();
        consumer.close();

    }
}
