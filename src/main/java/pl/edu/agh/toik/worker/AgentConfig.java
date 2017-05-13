package pl.edu.agh.toik.worker;

import javafx.stage.Stage;
import pl.edu.agh.toik.communication.Id;

import java.util.List;

public interface AgentConfig {
    Id getAgentId();
    List<Id> getNeighbours();
    List<Stage> getStages();
}