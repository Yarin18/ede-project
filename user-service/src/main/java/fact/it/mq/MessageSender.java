package fact.it.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.controller.UserController;
import fact.it.dto.Nameable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public <T extends Nameable> void sendMessage(final T msg, final UserController.UserTopic topic) {
//        try {
//            String jsonString = objectMapper.writeValueAsString(msg);
//            jmsMessagingTemplate.convertAndSend(topic.getName(), jsonString);
//            System.out.println("Sent message!");
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }

}
