package pl.edu.agh.toik.worker;

import pl.edu.agh.toik.communication.Message;
import pl.edu.agh.toik.communication.MessageType;

import java.util.Objects;

class MessageStub implements Message {

    private final String payload;

    MessageStub(String payload) {
        this.payload = payload;
    }

    @Override
    public MessageType getType() {
        return null;
    }

    @Override
    public String getPayload() {
        return payload;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageStub that = (MessageStub) o;
        return Objects.equals(payload, that.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payload);
    }

    @Override
    public String toString() {
        return "MessageStub{" +
                "payload='" + payload + '\'' +
                '}';
    }
}
