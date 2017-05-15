package pl.edu.agh.toik.starter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import pl.edu.agh.lab.toik.comm.v2.INamingService;
import pl.edu.agh.toik.Neighbour;
import pl.edu.agh.toik.agent.Stage;
import pl.edu.agh.toik.starter.mocks.MockId;
import pl.edu.agh.toik.starter.mocks.agent.MockAgentConfig;
import pl.edu.agh.toik.topology.TopologyFactory;
import pl.edu.agh.toik.topology.TopologyType;
import pl.edu.agh.toik.worker.AgentConfig;
import pl.edu.agh.toik.worker.StopStrategy;
import pl.edu.agh.toik.worker.WorkerConfig;

public class DefaultStarter implements Starter {
	private String configPath;
	
	public DefaultStarter(String configPath) {
		this.configPath = configPath;
	}
	
	@SuppressWarnings("unchecked")
	public void start() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(configPath);
		List<Stage> stages = (List<Stage>) ctx.getBean("stages");
		
		TopologyFactory topologyFactory = (TopologyFactory) ctx.getBean("topology-factory");
		TopologyType topologyType = (TopologyType) ctx.getBean("topology-type");
		StopStrategy stopStrategy = (StopStrategy) ctx.getBean("stop-strategy");
		
		INamingService namingService = (INamingService) ctx.getBean("naming-service");
		List<WorkerConfig> workerConfigs = (List<WorkerConfig>) ctx.getBean("worker-configs");
		Map<String, WorkerConfig> workerIdToConfig = new HashMap<>();
		
		for (WorkerConfig config : workerConfigs) {
			workerIdToConfig.put(config.getId().getValue(), config);
		}
		
		/* TODO: We need a method to retrieve a workerID -> agent IDs map from
		 * the Naming Service */
		
		Map<String, List<String>> workerAgentMap = new HashMap<>();
		for (WorkerConfig config : workerConfigs) {
			String id = config.getId().getValue();
			workerAgentMap.put(id, namingService.GetAgents(id));
		}
		
		Map<String, List<Neighbour>> agentToNeighbours = topologyFactory.createTopology(topologyType, workerAgentMap);
		Map<String, List<AgentConfig>> workerToAgentConfigs = new HashMap<>();
		
		for (Entry<String, List<String>> agents : workerAgentMap.entrySet()) {
			String workerId = agents.getKey();
			List<AgentConfig> agentConfigs = new ArrayList<>();
			
			for (String agentId : agents.getValue()) {
				agentConfigs.add(new MockAgentConfig(
						new MockId(agentId), 
						agentToNeighbours.get(agentId)
							.stream()
							.map(n -> new MockId(n.getAgentID()))
							.collect(Collectors.toList()),
						stages));
			}
			
			workerIdToConfig.get(workerId)
				.getAgentConfigs()
				.addAll(agentConfigs);
			workerToAgentConfigs.put(workerId, agentConfigs);
		}

		while (stopStrategy.shouldContinue()) {
			stopStrategy.registerIteration();
		}
	}
}
