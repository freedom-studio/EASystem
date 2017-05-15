package pl.edu.agh.toik.communication;

public interface Communicator {
    void init(String workerName);
    void registerObserver(MessageObserver observer);
    void sendMessage(String worker, String agent, Message message);
    String getWorkerName(String agent);
}
