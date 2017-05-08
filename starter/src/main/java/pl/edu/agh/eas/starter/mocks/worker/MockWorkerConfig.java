package pl.edu.agh.eas.starter.mocks.worker;

import pl.edu.agh.eas.starter.mocks.algorithm.CrossoverAlgorithm;

public class MockWorkerConfig implements WorkerConfig {
	private int agentsCount;
	private CrossoverAlgorithm crossoverAlgorithm;
	
	public MockWorkerConfig(int agentsCount, CrossoverAlgorithm crossoverAlgorithm) {
		this.agentsCount = agentsCount;
		this.crossoverAlgorithm = crossoverAlgorithm;
	}
	
	@Override
	public int getAgentsCount() {
		return this.agentsCount;
	}

	@Override
	public CrossoverAlgorithm getCrossoverAlgorithm() {
		return this.crossoverAlgorithm;
	}

}
