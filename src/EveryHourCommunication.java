import java.util.List;

/**
 * Created by Zuzanna on 5/8/2017.
 */
public class EveryHourCommunication extends Communication {

    @Override
    public void send(List<String> updates) {
        System.out.println("sending updates every hour...");
    }
}
