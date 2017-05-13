package pl.edu.agh.toik.starter.mocks.worker;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.toik.communication.Id;
import pl.edu.agh.toik.observers.AgentObserver;
import pl.edu.agh.toik.worker.AgentConfig;
import pl.edu.agh.toik.worker.StopStrategy;
import pl.edu.agh.toik.worker.WorkerConfig;

public class MockWorkerConfig implements WorkerConfig {
	private Id id;
	private StopStrategy stopStrategy;
	private List<AgentObserver> observers;
	private List<AgentConfig> agentConfigs;
	private int agentCount;
	
	public MockWorkerConfig(Id id, List<AgentConfig> agentConfigs,
			List<AgentObserver> observers, StopStrategy stopStrategy) {
		this.id = id;
		this.agentConfigs = agentConfigs;
		this.agentCount = agentConfigs.size();
		this.stopStrategy = stopStrategy;
		this.observers = observers;
	}
	
	public MockWorkerConfig(Id id, int agentCount, StopStrategy stopStrategy) {
		this(id, new ArrayList<AgentConfig>(), new ArrayList<AgentObserver>(), stopStrategy);
		this.agentCount = agentCount;
	}
	
	public Id getId() {
		return this.id;
	}

	public int getAgentCount() {
		return this.agentCount;
	}
	
	public StopStrategy getStopStrategy() {
		return this.stopStrategy;
	}

	public List<AgentObserver> getObservers() {
		return this.observers;
	}

	public List<AgentConfig> getAgentConfigs() {
		return this.agentConfigs;
	}
}
