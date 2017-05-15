package pl.edu.agh.toik.worker;

public interface StopStrategy {
    boolean shouldContinue();
    void registerIteration();
}
