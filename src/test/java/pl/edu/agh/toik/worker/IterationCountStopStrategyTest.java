package pl.edu.agh.toik.worker;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class IterationCountStopStrategyTest {

    @Test public void shouldAllowToContinueBeforeLimitIsReached() {
        IterationCountStopStrategy stopStrategy = new IterationCountStopStrategy(3);

        // 0 iterations
        assertThat(stopStrategy.shouldContinue()).isTrue();
        // 1 iterations
        stopStrategy.registerIteration();
        assertThat(stopStrategy.shouldContinue()).isTrue();
        // 2 iterations
        stopStrategy.registerIteration();
        assertThat(stopStrategy.shouldContinue()).isTrue();
    }


    @Test public void shouldStopWhenLimitIsReached() {
        IterationCountStopStrategy stopStrategy = new IterationCountStopStrategy(3);

        stopStrategy.registerIteration();
        stopStrategy.registerIteration();
        stopStrategy.registerIteration();
        assertThat(stopStrategy.shouldContinue()).isFalse();
    }

}
