package pl.edu.agh.toik.starter;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import pl.edu.agh.toik.Neighbour;
import pl.edu.agh.toik.agent.Stage;
import pl.edu.agh.toik.topology.TopologyFactory;
import pl.edu.agh.toik.topology.TopologyType;
import pl.edu.agh.toik.worker.WorkerConfig;

public class App {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/application-context.xml");
		
		List<WorkerConfig> workerConfigs = (List<WorkerConfig>) ctx.getBean("worker-configs");
		List<Stage> stages = (List<Stage>) ctx.getBean("stages");
		TopologyFactory topologyFactory = (TopologyFactory) ctx.getBean("topology-factory");
		TopologyType topologyType = (TopologyType) ctx.getBean("topology-type");
		
//		Map<String, Neighbour> agentToNeighbour = topologyFactory.createTopology(topologyType, workerAgentMap)
	}
}
