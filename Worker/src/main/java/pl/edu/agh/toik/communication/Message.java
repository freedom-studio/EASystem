package pl.edu.agh.toik.communication;

public interface Message {
    MessageType getType();
    Id getRecipient();
    Id getSender();
    Object getPayload();
}
