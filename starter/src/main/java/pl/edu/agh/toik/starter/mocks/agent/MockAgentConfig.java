package pl.edu.agh.toik.starter.mocks.agent;

import java.util.List;

import pl.edu.agh.toik.agent.Stage;
import pl.edu.agh.toik.communication.Id;
import pl.edu.agh.toik.worker.AgentConfig;

public class MockAgentConfig implements AgentConfig {
	private Id id;
	private List<Id> neighbours;
	private List<Stage> stages;
	
	public MockAgentConfig(Id id, List<Id> neighbours, List<Stage> stages) {
		this.id = id;
		this.neighbours = neighbours;
		this.stages = stages;
	}
	
	public Id getAgentId() {
		return this.id;
	}

	public List<Id> getNeighbours() {
		return this.neighbours;
	}

	public List<Stage> getStages() {
		return this.stages;
	}

}
