package pl.edu.agh.toik.worker;

import org.junit.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeLimitStopStrategyTest {

    @Test public void shouldNotStopIfExpectedTimeHasNotPassed() {
        ClockStub clock = new ClockStub();
        TimeLimitStopStrategy stopStrategy = new TimeLimitStopStrategy(Duration.ofHours(2), clock);

        // no time passed
        assertThat(stopStrategy.shouldContinue()).isTrue();
        // after 1 hour
        clock.addTime(Duration.ofHours(1));
        stopStrategy.registerIteration();
        assertThat(stopStrategy.shouldContinue()).isTrue();
        // after 1 hour 59 minutes
        clock.addTime(Duration.ofMinutes(59));
        stopStrategy.registerIteration();
        assertThat(stopStrategy.shouldContinue()).isTrue();
    }

    @Test public void shouldStopAsSoonAsTimeLimitIsReached() {
        ClockStub clock = new ClockStub();
        TimeLimitStopStrategy stopStrategy = new TimeLimitStopStrategy(Duration.ofHours(2), clock);

        // no time passed
        stopStrategy.registerIteration();
        // after 2 hours
        clock.addTime(Duration.ofHours(2));
        stopStrategy.registerIteration();
        assertThat(stopStrategy.shouldContinue()).isFalse();
    }

}
