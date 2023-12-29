package fact.it.mq;

import fact.it.controller.UserController;
import fact.it.dto.Nameable;
import fact.it.dto.UserRequest;
import fact.it.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JmsProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    public <T extends Nameable> void sendMessage(final T message, final UserController.UserTopic topic){
        try{
            log.info("Attempting Send message from " + message.getUserName());
            jmsTemplate.convertAndSend(topic.getName(), message);
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
    }


}
