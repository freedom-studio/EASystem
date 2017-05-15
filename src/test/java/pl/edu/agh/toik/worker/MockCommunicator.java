package pl.edu.agh.toik.worker;

import pl.edu.agh.toik.communication.Communicator;
import pl.edu.agh.toik.communication.Message;
import pl.edu.agh.toik.communication.MessageObserver;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MockCommunicator implements Communicator {

    private final List<MessageObserver> handlers;
    private final List<MessageToAgent> messagesFromAgentsToSend;

    MockCommunicator() {
        handlers = new ArrayList<>();
        messagesFromAgentsToSend = new ArrayList<>();
    }

    void messageComesFromOutside(String agent, Message message) {
        handlers.forEach(h -> h.handleMessage(agent, message));
    }

    void expectNoMessagesWereSent() {
        assertThat(messagesFromAgentsToSend).isEmpty();
    }

    void expectMessagesWereSent(MessageToAgent... messages) {
        assertThat(messagesFromAgentsToSend).containsExactly(messages);
        messagesFromAgentsToSend.clear();
    }

    @Override
    public void init(String workerName) {

    }

    @Override
    public void registerObserver(MessageObserver observer) {
        handlers.add(observer);
    }

    @Override
    public void sendMessage(String worker, String agent, Message message) {
        messagesFromAgentsToSend.add(new MessageToAgent(agent, message));
    }

    @Override
    public String getWorkerName(String agent) {
        return "w234";
    }
}
