package pl.edu.agh.toik.worker;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalAmount;

class ClockStub extends Clock {

    private Instant now;

    ClockStub() {
        this.now = Instant.ofEpochMilli(0);
    }

    void addTime(TemporalAmount amount) {
        now = now.plus(amount);
    }

    @Override
    public Instant instant() {
        return now;
    }

    @Override public ZoneId getZone() {
        return ZoneId.systemDefault();
    }

    @Override public Clock withZone(ZoneId zone) {
        throw new UnsupportedOperationException();
    }
}
