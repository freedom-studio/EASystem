package pl.edu.agh.toik.worker;

import javafx.stage.Stage;

import java.util.List;

public interface AgentConfig {
    String getAgentId();
    List<String> getNeighbours();
    List<Stage> getStages();
}
