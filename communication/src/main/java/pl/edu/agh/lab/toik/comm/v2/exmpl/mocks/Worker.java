package pl.edu.agh.lab.toik.comm.v2.exmpl.mocks;

import pl.edu.agh.lab.toik.comm.v2.ICommunicator;
import pl.edu.agh.lab.toik.comm.v2.IMessageObserver;
import pl.edu.agh.lab.toik.comm.v2.impl.Message;

/**
 * Created by regis on 5/9/17.
 */
public class Worker implements IMessageObserver {
    private ICommunicator communicator;
    private String name;

    @Override
    public void handleIncomingMessage(Message message) {
        System.out.println(name+ ": Dostałem wiadomość!!");
    }
}
