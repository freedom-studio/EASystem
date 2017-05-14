import java.util.List;

/**
 * Created by Zuzanna on 5/8/2017.
 */
public abstract class Communication {
    protected CommunicationComponent communicationComponent;

    public abstract void send(List<String> updates);
}
