package pl.edu.agh.toik.communication;

public interface Message {
    MessageType getType();
    Object getPayload();
}
