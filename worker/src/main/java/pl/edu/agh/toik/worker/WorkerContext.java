package pl.edu.agh.toik.worker;

import pl.edu.agh.toik.communication.Id;
import pl.edu.agh.toik.communication.Message;

import java.util.List;

public interface WorkerContext {
    void sendMessage(Id recipient, Message mesage);
    List<Message> getPendingMessages();
}
