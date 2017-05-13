package pl.edu.agh.toik.worker;

import pl.edu.agh.toik.communication.Message;

import java.util.ArrayList;
import java.util.List;

public class CommunicationContext implements WorkerContext {
    private final List<Message> receiveBuffer;
    private final List<Message> sendBuffer;

    CommunicationContext() {
        this.sendBuffer = new ArrayList<>();
        this.receiveBuffer = new ArrayList<>();
    }

    @Override
    public void sendMessage(Message message) {
        sendBuffer.add(message);
    }

    @Override
    public List<Message> takeReceivedMessages() {
        return returnCopyAndClear(receiveBuffer);
    }

    List<Message> takeMessagesToSend() {
        return returnCopyAndClear(sendBuffer);
    }

    void addReceivedMessage(Message message) {
        receiveBuffer.add(message);
    }

    private List<Message> returnCopyAndClear(List<Message> list) {
        ArrayList<Message> messages = new ArrayList<>(list);
        list.clear();
        return messages;
    }
}
