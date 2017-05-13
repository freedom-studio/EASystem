package pl.edu.agh.toik.topology;

import pl.edu.agh.toik.Neighbour;

import java.util.List;
import java.util.Map;

public interface TopologyFactory {
    Map<String, List<Neighbour>> createTopology(TopologyType topologyType, Map<String, List<String>> workerAgentMap);
}
