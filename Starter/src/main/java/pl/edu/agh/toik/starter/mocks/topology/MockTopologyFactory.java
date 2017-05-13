package pl.edu.agh.toik.starter.mocks.topology;

import java.util.List;
import java.util.Map;

import pl.edu.agh.toik.Neighbour;
import pl.edu.agh.toik.topology.TopologyFactory;
import pl.edu.agh.toik.topology.TopologyType;

public class MockTopologyFactory implements TopologyFactory {

	public Map<String, List<Neighbour>> createTopology(TopologyType topologyType,
			Map<String, List<String>> workerAgentMap) {
		return null;
	}

}
