package pl.edu.agh.toik.worker;

import pl.edu.agh.toik.communication.Message;
import pl.edu.agh.toik.communication.MessagingService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.*;

class MockMessagingService implements MessagingService {

    private final List<Consumer<Message>> handlers;
    private final List<Message> messagesFromAgentsToSend;

    MockMessagingService() {
        handlers = new ArrayList<>();
        messagesFromAgentsToSend = new ArrayList<>();
    }

    @Override
    public void registerHandler(Consumer<Message> handler) {
        handlers.add(handler);
    }

    @Override
    public void send(Message message) {
        messagesFromAgentsToSend.add(message);
    }

    void messageComesFromOutside(Message message) {
        handlers.forEach(h -> h.accept(message));
    }

    void expectNoMessagesWereSent() {
        assertThat(messagesFromAgentsToSend).isEmpty();
    }

    void expectMessagesWereSent(Message... messages) {
        assertThat(messagesFromAgentsToSend).containsExactly(messages);
        messagesFromAgentsToSend.clear();
    }
}
