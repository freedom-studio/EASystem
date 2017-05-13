package pl.edu.agh.toik.starter.mocks.worker;

import java.util.concurrent.atomic.AtomicInteger;

import pl.edu.agh.toik.worker.StopStrategy;

public class MaxIterationsStopStrategy implements StopStrategy {
	private final int MAX_ITERS;
	private AtomicInteger iterationCount = new AtomicInteger(0);

	public MaxIterationsStopStrategy(int maxIters) {
		this.MAX_ITERS = maxIters;
	}
	
	public boolean shouldContinue() {
		return (iterationCount.get() < MAX_ITERS);
	}

	public void registerIteration() {
		iterationCount.incrementAndGet();
	}
	
}
