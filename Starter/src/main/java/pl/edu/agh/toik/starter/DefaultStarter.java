package pl.edu.agh.toik.starter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import pl.edu.agh.lab.toik.comm.v2.INamingService;
import pl.edu.agh.toik.Neighbour;
import pl.edu.agh.toik.agent.Stage;
import pl.edu.agh.toik.topology.TopologyFactory;
import pl.edu.agh.toik.topology.TopologyType;
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
		List<WorkerConfig> workerConfigs = (List<WorkerConfig>) ctx.getBean("worker-configs");
		INamingService namingService = (INamingService) ctx.getBean("naming-service");
		
		/* TODO: We need a method to retrieve a workerID -> agent IDs map from
		 * the Naming Service */
		
		Map<String, List<String>> workerAgentMap = new HashMap<>();
		for (WorkerConfig config : workerConfigs) {
			String id = config.getId().getValue();
			workerAgentMap.put(id, namingService.GetAgents(id));
		}
		
		Map<String, List<Neighbour>> agentToNeighbour = topologyFactory.createTopology(topologyType, workerAgentMap);
	}
}
