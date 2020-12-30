package com.gtx.backend.activeMQ;

import com.gtx.backend.response.UserRest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.logging.Logger;

@Component
@Slf4j
public class JmsConsumer implements MessageListener {
    private static final Logger LOGGER = Logger.getLogger(JmsConsumer.class.getName());


    @JmsListener(destination = "test")
    @Override
    public void onMessage(Message message) {
        try{
            ObjectMessage objectMessage = (ObjectMessage)message;
            UserRest user = (UserRest)objectMessage.getObject();
        LOGGER.info("Received Message: "+ user.toString());
        } catch(Exception e) {
            LOGGER.severe("Received Exception : "+ e);
        }

    }
}