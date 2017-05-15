package pl.edu.agh.toik.worker;

import pl.edu.agh.toik.communication.Message;

import java.util.ArrayList;
import java.util.List;

public class CommunicationContext implements WorkerContext {
    private final List<Message> receiveBuffer;
    private final List<MessageToAgent> sendBuffer;

    CommunicationContext() {
        this.sendBuffer = new ArrayList<>();
        this.receiveBuffer = new ArrayList<>();
    }

    @Override
    public void sendMessage(String agent, Message message) {
        sendBuffer.add(new MessageToAgent(agent, message));
    }

    @Override
    public List<Message> getReceivedMessages() {
        return new ArrayList<>(receiveBuffer);
    }

    void clearReceivedMessages() {
        receiveBuffer.clear();
    }

    List<MessageToAgent> takeMessagesToSend() {
        return returnCopyAndClear(sendBuffer);
    }

    void addReceivedMessage(Message message) {
        receiveBuffer.add(message);
    }

    private <T> List<T> returnCopyAndClear(List<T> list) {
        ArrayList<T> messages = new ArrayList<>(list);
        list.clear();
        return messages;
    }
}
