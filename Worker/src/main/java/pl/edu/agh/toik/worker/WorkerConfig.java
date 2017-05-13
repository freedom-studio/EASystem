package pl.edu.agh.toik.worker;

import pl.edu.agh.toik.observers.AgentObserver;

import java.util.List;

public interface WorkerConfig {
    StopStrategy getStopStrategy();
    List<AgentObserver> getObservers();
    List<AgentConfig> getAgentConfigs();
    int getAgentCount();
}
