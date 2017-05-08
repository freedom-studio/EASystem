package pl.edu.agh.eas.starter.mocks.worker;

import pl.edu.agh.eas.starter.mocks.algorithm.CrossoverAlgorithm;

public class MockWorkerConfig implements WorkerConfig {
	private int agentsCount;
	private CrossoverAlgorithm crossoverAlgorithm;
	
	public MockWorkerConfig(int agentsCount, CrossoverAlgorithm crossoverAlgorithm) {
		this.agentsCount = agentsCount;
		this.crossoverAlgorithm = crossoverAlgorithm;
	}
	
	public int getAgentsCount() {
		return this.agentsCount;
	}

	public CrossoverAlgorithm getCrossoverAlgorithm() {
		return this.crossoverAlgorithm;
	}

}
