package pl.edu.agh.toik.worker;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.toik.communication.Message;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CommunicationTest {

    private MockCommunicator messagingService;
    private Communication communication;
    private final String agent1 = "agent1";
    private final String agent2 = "agent2";
    private final String agent = agent1;
    private final String agent3 = "agent3";
    private final String agent4 = "agent4";

    @Before
    public void init() {
        messagingService = new MockCommunicator();
        communication = new Communication("w1", messagingService);
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

        messagingService.messageComesFromOutside(agent, message("hello"));
        messagingService.messageComesFromOutside(agent, message("hi"));

        communication.runCommunication();

        WorkerContext context = communication.getContext(agent);
        assertThat(context.getReceivedMessages())
                .containsExactly(message("hello"), message("hi"));
    }

    @Test public void shouldDeliverMessagesFromAgent() {
        communication.register(agent);
        WorkerContext context = communication.getContext(agent);

        context.sendMessage(agent2, message("hi"));
        context.sendMessage(agent2, message("hello"));

        messagingService.expectNoMessagesWereSent();

        communication.runCommunication();

        messagingService.expectMessagesWereSent(
                messageToAgent(agent2, "hi"),
                messageToAgent(agent2, "hello"));
    }

    @Test public void shouldDeliverMessagesFromAndToMultipleAgents() {
        communication.register(agent1);
        communication.register(agent2);

        // when each of two agents sends a message
        communication.getContext(agent1).sendMessage(agent3, message("A"));
        communication.getContext(agent2).sendMessage(agent4, message("B"));
        communication.runCommunication();

        // then they should be sent via messaging service
        messagingService.expectMessagesWereSent(messageToAgent(agent3, "A"), messageToAgent(agent4, "B"));


        // when message to each agent comes from messaging service
        messagingService.messageComesFromOutside(agent1, message("X"));
        messagingService.messageComesFromOutside(agent2, message("Y"));
        // and one agent sends one message
        communication.getContext(agent1).sendMessage(agent3, message("C"));
        communication.runCommunication();

        // then message from agent should be sent out
        messagingService.expectMessagesWereSent(messageToAgent(agent3, "C"));
        // and each agent should access only messages to them
        assertThat(communication.getContext(agent1).getReceivedMessages())
                .containsExactly(message("X"));
        assertThat(communication.getContext(agent2).getReceivedMessages())
                .containsExactly(message("Y"));
    }

    @Test public void shouldKeepUndeliveredMessagesAndDeliverThemWhenRecipientRegisters() {
        messagingService.messageComesFromOutside(agent, message("A"));
        communication.runCommunication();
        communication.runCommunication();

        communication.register(agent);
        communication.runCommunication();

        assertThat(communication.getContext(agent).getReceivedMessages())
                .containsExactly(message("A"));
    }

    @Test public void shouldAllowToGetTheSameListOfReceivedMessagesMultipleTimesAndOnlyResetItBetweenRuns() {
        communication.register(agent);
        messagingService.messageComesFromOutside(agent, message("A"));
        communication.runCommunication();

        assertThat(communication.getContext(agent).getReceivedMessages())
                .containsExactly(message("A"));
        assertThat(communication.getContext(agent).getReceivedMessages())
                .containsExactly(message( "A"));

        messagingService.messageComesFromOutside(agent, message("B"));
        communication.runCommunication();
        assertThat(communication.getContext(agent).getReceivedMessages())
                .containsExactly(message("B"));
        assertThat(communication.getContext(agent).getReceivedMessages())
                .containsExactly(message("B"));
    }

    private Message message(String payload) {
        return new MessageStub(payload);
    }

    private MessageToAgent messageToAgent(String agent, String payload) {
        return new MessageToAgent(agent, message(payload));
    }

}
