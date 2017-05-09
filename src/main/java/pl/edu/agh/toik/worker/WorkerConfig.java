package pl.edu.agh.toik.worker;

import pl.edu.agh.toik.observables.AgentObserver;

import java.util.List;

public interface WorkerConfig {
    List<AgentObserver> getObservers();
    List<AgentConfig> getAgentConfigs();
}
