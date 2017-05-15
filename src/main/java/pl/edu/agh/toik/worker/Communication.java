package pl.edu.agh.toik.worker;

import pl.edu.agh.toik.communication.Communicator;

import java.util.*;

class Communication {
    private final Communicator communicator;
    private final Map<String, CommunicationContext> contexts;
    private final List<MessageToAgent> messagesToDeliver;

    Communication(String workerName, Communicator communicator) {
        this.communicator = communicator;
        this.communicator.init(workerName);
        this.messagesToDeliver = new ArrayList<>();
        this.contexts = new LinkedHashMap<>();
        communicator.registerObserver((agent, msg) -> messagesToDeliver.add(new MessageToAgent(agent, msg)));
    }

    void register(String id) {
        contexts.put(id, new CommunicationContext());
    }

    WorkerContext getContext(String id) {
        if (contexts.containsKey(id)) {
            return contexts.get(id);
        } else {
            throw new NoSuchElementException();
        }
    }

    void runCommunication() {
        clearReceiveBuffers();
        deliverMessagesFromOutsideToContexts();
        sendMessagesFromContextsOutside();
    }

    private void deliverMessagesFromOutsideToContexts() {
        Iterator<MessageToAgent> messages = messagesToDeliver.iterator();
        while (messages.hasNext()) {
            MessageToAgent messageToAgent = messages.next();
            String recipient = messageToAgent.getAgentId();
            if (contexts.containsKey(recipient)) {
                contexts.get(recipient).addReceivedMessage(messageToAgent.getMessage());
                messages.remove();
            }
        }
    }

    private void clearReceiveBuffers() {
        contexts.values().forEach(CommunicationContext::clearReceivedMessages);
    }

    private void sendMessagesFromContextsOutside() {
        contexts.values().stream()
                .flatMap(c -> c.takeMessagesToSend().stream())
                .forEachOrdered(msg -> {
                    String workerName = communicator.getWorkerName(msg.getAgentId());
                    communicator.sendMessage(workerName, msg.getAgentId(), msg.getMessage());
                });
    }
}
