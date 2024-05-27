package com.epam.restapitester.api;


import com.epam.restapitester.dto.EmployeeDetailsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;

    @Value("${messaging.queue.name}")
    public static String QUEUE_NAME;


    @Value("${messaging.topic.name}")
    public static String TOPIC_NAME;

    @Value("${messaging.routing.key}")
    public static String ROUTING_KEY;


    void sendMessage(){
        rabbitTemplate.convertAndSend(TOPIC_NAME, ROUTING_KEY, EmployeeDetailsDTO.builder().build());
    }




    @RabbitListener(queues = {"employee_queue"})
    void receiveMessage(EmployeeDetailsDTO employeeDetailsDTO){
        System.out.println(employeeDetailsDTO);
    }
}
