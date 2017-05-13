package pl.edu.agh.toik.worker;

public class IterationCountStopStrategy implements StopStrategy {
    private final int limit;
    private int iterations;

    public IterationCountStopStrategy(int limit) {
        this.limit = limit;
        this.iterations = 0;
    }

    @Override
    public boolean shouldContinue() {
        return iterations < limit;
    }

    @Override
    public void registerIteration() {
        iterations++;
    }
}
