package pl.edu.agh.toik.worker;

import java.util.List;

import pl.edu.agh.toik.communication.Id;
import pl.edu.agh.toik.observers.AgentObserver;

public interface WorkerConfig {
	Id getId();
    StopStrategy getStopStrategy();
    List<AgentObserver> getObservers();
    List<AgentConfig> getAgentConfigs();
    int getAgentCount();
}
