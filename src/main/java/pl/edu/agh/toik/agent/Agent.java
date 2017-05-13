package pl.edu.agh.toik.agent;

import pl.edu.agh.toik.communication.Id;
import pl.edu.agh.toik.worker.WorkerContext;

public interface Agent {
    Id getId();

    void step(WorkerContext context);
}
