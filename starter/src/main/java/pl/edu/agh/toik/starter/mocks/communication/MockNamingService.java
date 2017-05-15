package pl.edu.agh.toik.starter.mocks.communication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.agh.lab.toik.comm.v2.INamingService;
import pl.edu.agh.lab.toik.comm.v2.impl.NamingService;
import pl.edu.agh.toik.worker.WorkerConfig;

public class MockNamingService implements INamingService{
	private HashMap<String, String> workerToAddress;
    private HashMap<String, ArrayList<String>> workerToAgents;

    public String GetWorker(String workerId) {
        return workerToAddress.get(workerId);
    }

    public ArrayList<String> GetAgents(String workerId) {
        return workerToAgents.get(workerId);
    }

    public MockNamingService(List<WorkerConfig> workerConfigs) {
    	workerToAddress = new HashMap<>();
    	workerToAgents = new HashMap<>();
        
        for (WorkerConfig config : workerConfigs) {
        	String id = config.getId().getValue();
        	workerToAddress.put(id, id);
        	ArrayList<String> agents = new ArrayList<>();
        	
        	for (int i = 1; i <= config.getAgentCount(); i++) {
        		agents.add(id + "-agent" + i);
        	}
        	
        	workerToAgents.put(id, agents);
        }
    }

    public NamingService create(String conf) {
    	return new NamingService(conf);
    }
}
