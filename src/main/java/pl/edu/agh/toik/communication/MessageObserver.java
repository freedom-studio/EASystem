package pl.edu.agh.toik.communication;

public interface MessageObserver {
    void handleMessage(String agent, Message message);
}
