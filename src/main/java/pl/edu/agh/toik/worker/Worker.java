package pl.edu.agh.toik.worker;

import pl.edu.agh.toik.communication.Message;
import pl.edu.agh.toik.communication.MessagingService;

public class Worker {

    public Worker(MessagingService messagingService, String xmlConfig) {
        messagingService.registerHandler(this::dispatchMessage);
        WorkerConfig workerConfig = parse(xmlConfig);
        spawnAgents(workerConfig);
    }

    public void step() {

    }

    private void dispatchMessage(Message message) {
    }

    private void spawnAgents(WorkerConfig workerConfig) {

    }

    private WorkerConfig parse(String workerConfig) {
        return null;
    }

}
