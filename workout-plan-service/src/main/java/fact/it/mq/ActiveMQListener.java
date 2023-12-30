package fact.it.mq;

import fact.it.dto.UserRequest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQListener {

    @JmsListener(destination = "user-create")
    public void receiveCreateMessage(final UserRequest userRequest) {
        System.out.println("The user " + userRequest.getName() + " has been created!");
    }

    @JmsListener(destination = "user-delete")
    public void receiveDeleteMessage(final UserRequest userRequest) {
        System.out.println("The user " + userRequest.getName() + " has been deleted!");
    }

    @JmsListener(destination = "user-update")
    public void receiveUpdateMessage(final UserRequest userRequest) {
        System.out.println("The user " + userRequest.getName() + " has been updated!");
    }
}
