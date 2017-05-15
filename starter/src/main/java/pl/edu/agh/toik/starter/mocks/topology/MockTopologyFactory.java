package pl.edu.agh.toik.starter.mocks.topology;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import pl.edu.agh.toik.Neighbour;
import pl.edu.agh.toik.topology.TopologyFactory;
import pl.edu.agh.toik.topology.TopologyType;

public class MockTopologyFactory implements TopologyFactory {

	public Map<String, List<Neighbour>> createTopology(TopologyType topologyType,
			Map<String, List<String>> workerAgentMap) {
		
		Map<String, List<Neighbour>> agentToNeighbour = new HashMap<>();
		for (Entry<String, List<String>> worker : workerAgentMap.entrySet()) {
			for (String agentId : worker.getValue()) {
				agentToNeighbour.put(agentId, worker.getValue().stream()
						.map(n -> new MockNeighbour(agentId, n))
						.collect(Collectors.toList()));
			}
		}
		
		return agentToNeighbour;
	}

}
