package com.gtx.backend.activeMQ;

import com.gtx.backend.response.UserRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;
@Component
@Slf4j
public class JmsProducer {
    private static final Logger LOGGER = Logger.getLogger(JmsProducer.class.getName());
    @Autowired
    JmsTemplate jmsTemplate;

    @Value("test")
    private String topic;

    public void sendMessage(UserRest message){
        try{
            LOGGER.info("Attempting Send message to Topic: "+ topic);
            jmsTemplate.convertAndSend(topic, message);
        } catch(Exception e){
            LOGGER.severe("Recieved Exception during send Message: "+ e);
        }
    }


}