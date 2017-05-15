package pl.edu.agh.toik.worker;

import pl.edu.agh.toik.communication.Message;

import java.util.Objects;

class MessageToAgent {
    private final String agentId;
    private final Message message;

    MessageToAgent(String agentId, Message message) {
        this.agentId = agentId;
        this.message = message;
    }

    String getAgentId() {
        return agentId;
    }

    Message getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageToAgent that = (MessageToAgent) o;
        return Objects.equals(agentId, that.agentId) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agentId, message);
    }

    @Override
    public String toString() {
        return "MessageToAgent{" +
                "agentId='" + agentId + '\'' +
                ", message=" + message +
                '}';
    }
}