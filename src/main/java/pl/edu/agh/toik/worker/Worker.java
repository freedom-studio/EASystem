package pl.edu.agh.toik.worker;

import pl.edu.agh.toik.agent.Agent;
import pl.edu.agh.toik.communication.MessagingService;

import java.util.List;

public class Worker {

    private final Communication communication;
    private final List<Agent> agents;
    private final StopStrategy stopStrategy;

    public Worker(MessagingService messagingService, String xmlConfig) {
        WorkerConfig config = new ConfigParser().parse(xmlConfig);
        stopStrategy = config.getStopStrategy();
        communication = new Communication(messagingService);
        agents = new AgentSpawner().spawn(config.getAgentConfigs(), config.getObservers());
        agents.stream().map(Agent::getId).forEach(communication::register);
    }

    public boolean step() {
        agents.forEach(agent -> {
            WorkerContext context = communication.getContext(agent.getId());
            agent.step(context);
        });
        communication.runCommunication();
        stopStrategy.registerIteration();
        return stopStrategy.shouldContinue();
    }


}
