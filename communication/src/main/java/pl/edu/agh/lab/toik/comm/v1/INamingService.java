package pl.edu.agh.lab.toik.comm.v1;

import pl.edu.agh.lab.toik.comm.v1.impl.NamingService;

import java.util.ArrayList;

/**
 * Created by regis on 5/6/17.
 */
public interface INamingService {
    public String GetWorker(String name);

    public ArrayList<String> GetAgents(String name);
}
