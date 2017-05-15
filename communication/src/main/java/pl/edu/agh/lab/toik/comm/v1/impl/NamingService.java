package pl.edu.agh.lab.toik.comm.v1.impl;

import pl.edu.agh.lab.toik.comm.v1.INamingService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by regis on 4/14/17.
 */
public class NamingService implements INamingService {
    private HashMap<String, String> namingService;
    private HashMap<String, ArrayList<String>> agents;
    private int workers = 1;

    public String GetWorker(String name) {
        return namingService.get(name);
    }

    public ArrayList<String> GetAgents(String name) {
        return agents.get(name);
    }

    private void CreateWorker(String address) {
        String name = "w"+workers;
        workers++;
        namingService.put(name, address);
        agents.put(name, new ArrayList<>());
    }

    private void CreateAgent(String worker) {
        ArrayList<String> list = agents.get(worker);
        list.add(worker+"a"+list.size());
    }

    public NamingService(String conf) {
        namingService = new HashMap<>();
        agents = new HashMap<>();
        this.CreateWorker("host1");
        this.CreateWorker("host2");
        this.CreateAgent("w1");
        this.CreateAgent("w1");
        this.CreateAgent("w2");
        this.CreateAgent("w2");
        this.CreateAgent("w2");
    }
}
