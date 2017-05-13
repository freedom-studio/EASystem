package pl.edu.agh.toik.worker;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.toik.communication.Id;
import pl.edu.agh.toik.communication.Message;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CommunicationTest {

    private MockMessagingService messagingService;
    private Communication communication;
    private final Id agent1 = new IdImpl("agent1");
    private final Id agent2 = new IdImpl("agent2");
    private final Id agent = agent1;
    private final Id worker1 = new IdImpl("worker1");
    private final Id worker2 = new IdImpl("worker2");

    @Before
    public void init() {
        messagingService = new MockMessagingService();
        communication = new Communication(messagingService);
    }

    @Test
    public void shouldNotReturnContextForNonExistingAgent() {
        assertThatThrownBy(() -> communication.getContext(agent1)).isInstanceOf(NoSuchElementException.class);
    }

    @Test public void shouldReturnAContextForRegisteredAgent() {
        communication.register(agent);
        WorkerContext context = communication.getContext(agent);
        assertThat(context).isNotNull();
    }

    @Test public void shouldDeliverMessagesToAgent() {
        communication.register(agent);

        messagingService.messageComesFromOutside(message(agent, "hello"));
        messagingService.messageComesFromOutside(message(agent, "hi"));

        communication.runCommunication();

        WorkerContext context = communication.getContext(agent);
        assertThat(context.getReceivedMessages())
                .containsExactly(message(agent, "hello"), message(agent, "hi"));
    }

    @Test public void shouldDeliverMessagesFromAgent() {
        communication.register(agent);
        WorkerContext context = communication.getContext(agent);

        context.sendMessage(message(worker1, "hi"));
        context.sendMessage(message(worker2, "hello"));

        messagingService.expectNoMessagesWereSent();

        communication.runCommunication();

        messagingService.expectMessagesWereSent(message(worker1, "hi"), message(worker2, "hello"));
    }

    @Test public void shouldDeliverMessagesFromAndToMultipleAgents() {
        communication.register(agent1);
        communication.register(agent2);

        // when each of two agents sends a message
        communication.getContext(agent1).sendMessage(message(worker1, "A"));
        communication.getContext(agent2).sendMessage(message(worker2, "B"));
        communication.runCommunication();

        // then they should be sent via messaging service
        messagingService.expectMessagesWereSent(message(worker1, "A"), message(worker2, "B"));


        // when message to each agent comes from messaging service
        messagingService.messageComesFromOutside(message(agent1, "X"));
        messagingService.messageComesFromOutside(message(agent2, "Y"));
        // and one agent sends one message
        communication.getContext(agent1).sendMessage(message(worker1, "C"));
        communication.runCommunication();

        // then message from agent should be sent out
        messagingService.expectMessagesWereSent(message(worker1, "C"));
        // and each agent should access only messages to them
        assertThat(communication.getContext(agent1).getReceivedMessages())
                .containsExactly(message(agent1, "X"));
        assertThat(communication.getContext(agent2).getReceivedMessages())
                .containsExactly(message(agent2, "Y"));
    }

    @Test public void shouldKeepUndeliveredMessagesAndDeliverThemWhenRecipientRegisters() {
        messagingService.messageComesFromOutside(message(agent, "A"));
        communication.runCommunication();
        communication.runCommunication();

        communication.register(agent);
        communication.runCommunication();

        assertThat(communication.getContext(agent).getReceivedMessages())
                .containsExactly(message(agent, "A"));
    }

    @Test public void shouldAllowToGetTheSameListOfReceivedMessagesMultipleTimesAndOnlyResetItBetweenRuns() {
        communication.register(agent);
        messagingService.messageComesFromOutside(message(agent, "A"));
        communication.runCommunication();

        assertThat(communication.getContext(agent).getReceivedMessages())
                .containsExactly(message(agent, "A"));
        assertThat(communication.getContext(agent).getReceivedMessages())
                .containsExactly(message(agent, "A"));

        messagingService.messageComesFromOutside(message(agent, "B"));
        communication.runCommunication();
        assertThat(communication.getContext(agent).getReceivedMessages())
                .containsExactly(message(agent, "B"));
        assertThat(communication.getContext(agent).getReceivedMessages())
                .containsExactly(message(agent, "B"));
    }

    private Message message(Id recipient, String payload) {
        return new MessageStub(recipient, payload);
    }

}
