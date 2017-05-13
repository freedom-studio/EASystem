package pl.edu.agh.toik.worker;

import java.util.List;

import pl.edu.agh.toik.agent.Stage;
import pl.edu.agh.toik.communication.Id;

public interface AgentConfig {
    Id getAgentId();
    List<Id> getNeighbours();
    List<Stage> getStages();
}
