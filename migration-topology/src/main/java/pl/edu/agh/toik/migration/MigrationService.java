package pl.edu.agh.toik.migration;

import pl.edu.agh.toik.Neighbour;

import java.util.List;

public interface MigrationService {
    void emigrate(List<Neighbour> neighbours, Population population);
    void immigrate(String agentID, Population population);
    void setCommunicator(ICommunicator communicator);
    void handleMigrationMessage(Message message);
}
