package fact.it.mq;

import fact.it.dto.WorkoutRequest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQListener {

    @JmsListener(destination = "create-workout")
    public void receiveCreateMessage(final WorkoutRequest userRequest) {
        System.out.println("The workout " + userRequest.getName() + " has been created!");
    }

}
