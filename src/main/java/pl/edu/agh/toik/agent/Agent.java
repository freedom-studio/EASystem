package pl.edu.agh.toik.agent;

import pl.edu.agh.toik.worker.WorkerContext;

public interface Agent {
    String getId();

    void step(WorkerContext context);
}
