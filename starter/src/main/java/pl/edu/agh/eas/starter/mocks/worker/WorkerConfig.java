package pl.edu.agh.eas.starter.mocks.worker;

import pl.edu.agh.eas.starter.mocks.algorithm.CrossoverAlgorithm;

public interface WorkerConfig {
	public int getAgentsCount();
	public CrossoverAlgorithm getCrossoverAlgorithm();
}
