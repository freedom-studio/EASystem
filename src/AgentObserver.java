import java.util.Observable;

/**
 * Created by Zuzanna on 5/8/2017.
 */
public interface AgentObserver {
    public void update(Observable o, Population population);
}
