package pl.edu.agh.toik.agent;

import pl.edu.agh.toik.communication.Id;

public interface AgentFactory {
    Agent create(Id agentId);
}
