package pl.edu.agh.toik.worker;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

public class TimeLimitStopStrategy implements StopStrategy {

    private final Duration expectedDuration;
    private final Clock clock;
    private Instant startTime;
    private boolean shouldContinue;

    public TimeLimitStopStrategy(Duration expectedDuration, Clock clock) {
        this.expectedDuration = expectedDuration;
        this.clock = clock;
        this.shouldContinue = true;
    }

    public TimeLimitStopStrategy(Duration expectedDuration) {
        this(expectedDuration, Clock.systemDefaultZone());
    }

    @Override
    public boolean shouldContinue() {
        return shouldContinue;
    }

    @Override
    public void registerIteration() {
        if (startTime == null) {
            startTime = clock.instant();
        } else {
            Instant now = clock.instant();
            if (Duration.between(startTime, now).compareTo(expectedDuration) >= 0) {
                shouldContinue = false;
            }
        }
    }
}
