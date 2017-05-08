package pl.edu.agh.eas.starter.mocks.worker;

public class Worker {
	private WorkerConfig config;
	
	public Worker(WorkerConfig config) {
		this.config = config;
	}
	
	public WorkerConfig getWorkerConfig() {
		return this.config;
	}
}
