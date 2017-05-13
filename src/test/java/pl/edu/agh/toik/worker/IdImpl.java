package pl.edu.agh.toik.worker;

import pl.edu.agh.toik.communication.Id;

import java.util.Objects;

public class IdImpl implements Id {
    private final String value;

    IdImpl(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdImpl id = (IdImpl) o;
        return Objects.equals(value, id.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
