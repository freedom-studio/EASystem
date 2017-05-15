package pl.edu.agh.lab.toik.comm.v1.exmpl;

import pl.edu.agh.lab.toik.comm.v1.ICommunicator;
import pl.edu.agh.lab.toik.comm.v1.INamingService;
import pl.edu.agh.lab.toik.comm.v1.MessageType;
import pl.edu.agh.lab.toik.comm.v1.exmpl.mocks.Worker;
import pl.edu.agh.lab.toik.comm.v1.impl.Message;
import pl.edu.agh.lab.toik.comm.v1.impl.NamingService;
import pl.edu.agh.lab.toik.comm.v1.impl.TCPCommunicator;

/**
 * Created by regis on 5/9/17.
 */
public class Main {
    public static void main(String[] args) {
        ICommunicator communicator = new TCPCommunicator("test");
        Worker host1 = new Worker("host1", communicator);
        Worker host2 = new Worker("host2", communicator);

        host1.addMessageObserver(host1);
        host2.addMessageObserver(host2);

        Message m = new Message(MessageType.CONFIG, "muahaha");
        host1.send("host2", "a1", m);
        host2.send("host1", "a1", m);
    }
}
