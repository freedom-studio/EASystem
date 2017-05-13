package pl.edu.agh.toik.worker;

import pl.edu.agh.toik.communication.Id;
import pl.edu.agh.toik.communication.MessagingService;

class Communication {
    private final MessagingService messagingService;

    Communication(MessagingService messagingService) {

        this.messagingService = messagingService;
    }

    void register(Id id) {

    }

    WorkerContext getContext(Id id) {
        return null;
    }

    void runCommunication() {

    }
}
