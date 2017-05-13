package pl.edu.agh.toik.worker;

import pl.edu.agh.toik.communication.Id;
import pl.edu.agh.toik.communication.Message;
import pl.edu.agh.toik.communication.MessagingService;

import java.util.*;

class Communication {
    private final MessagingService messagingService;
    private final Map<Id, CommunicationContext> contexts;
    private final List<Message> messagesToDeliver;

    Communication(MessagingService messagingService) {
        this.messagingService = messagingService;
        this.messagesToDeliver = new ArrayList<>();
        this.contexts = new HashMap<>();
        messagingService.registerHandler(messagesToDeliver::add);
    }

    void register(Id id) {
        contexts.put(id, new CommunicationContext());
    }

    WorkerContext getContext(Id id) {
        if (contexts.containsKey(id)) {
            return contexts.get(id);
        } else {
            throw new NoSuchElementException();
        }
    }

    void runCommunication() {
        deliverMessagesFromOutsideToContexts();
        sendMessagesFromContextsOutside();
    }

    private void deliverMessagesFromOutsideToContexts() {
        contexts.values().forEach(CommunicationContext::clearReceivedMessages);
        Iterator<Message> messages = messagesToDeliver.iterator();
        while (messages.hasNext()) {
            Message message = messages.next();
            Id recipient = message.getRecipient();
            if (contexts.containsKey(recipient)) {
                contexts.get(recipient).addReceivedMessage(message);
                messages.remove();
            }
        }
    }

    private void sendMessagesFromContextsOutside() {
        contexts.values().stream()
                .flatMap(c -> c.takeMessagesToSend().stream())
                .forEachOrdered(messagingService::send);
    }
}
