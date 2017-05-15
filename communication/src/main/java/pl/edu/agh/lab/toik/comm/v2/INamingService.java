package pl.edu.agh.lab.toik.comm.v2;

import java.util.ArrayList;

/**
 * Created by regis on 5/6/17.
 */
public interface INamingService {
    public INamingService create(String conf);

    public String GetWorker(String name);

    public ArrayList<String> GetAgents(String name);
}
