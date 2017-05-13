package pl.edu.agh.lab.toik.comm.v2.impl;

import pl.edu.agh.lab.toik.comm.v2.INamingService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by regis on 4/14/17.
 */
public class NamingService implements INamingService {
    private HashMap<String, String> namingService;
    private HashMap<String, ArrayList<String>> agents;
    private int workers = 0;

    public String GetWorker(String name) {
        return namingService.get(name);
    }

    public ArrayList<String> GetAgents(String name) {
        return agents.get(name);
    }

    private void CreateWorker(String address) {
        namingService.put("w"+workers++, address);
    }

    private void CreateAgent(String worker) {
        ArrayList<String> list = agents.get(worker);
        list.add(worker+"a"+list.size());
    }

    public NamingService(String conf) {
        namingService = new HashMap<>();
        this.CreateWorker("http://192.168.2.4:8081/host1");
        agents = new HashMap<>();
    }

    public NamingService create(String conf) {
        NamingService ns = new NamingService(conf);
        return ns;
    }
}
