package pl.edu.agh.toik.communication;

import java.util.function.Consumer;

public interface MessagingService {
    void registerHandler(Consumer<Message> handler);
    void send(Message message);
}
