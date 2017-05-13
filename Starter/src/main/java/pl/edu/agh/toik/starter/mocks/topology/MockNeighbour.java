package pl.edu.agh.toik.starter.mocks.topology;

import pl.edu.agh.toik.Neighbour;

public class MockNeighbour implements Neighbour {
	private String workerId;
	private String agentId;
	
	public MockNeighbour(String workerId, String agentId) {
		this.workerId = workerId;
		this.agentId = agentId;
	}
	
	@Override
	public String getWorkerID() {
		return this.workerId;
	}

	@Override
	public String getAgentID() {
		return this.agentId;
	}

}
