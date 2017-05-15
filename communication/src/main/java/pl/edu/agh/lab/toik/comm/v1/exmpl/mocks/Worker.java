package pl.edu.agh.lab.toik.comm.v1.exmpl.mocks;

import pl.edu.agh.lab.toik.comm.v1.ICommunicator;
import pl.edu.agh.lab.toik.comm.v1.IMessageObserver;
import pl.edu.agh.lab.toik.comm.v1.impl.Message;

/**
 * Created by regis on 5/9/17.
 */
public class Worker implements IMessageObserver {
    private ICommunicator communicator;
    private String name;

    public Worker(String name, ICommunicator communicator) {
        this.name = name;
        this.communicator = communicator;
        this.communicator.init(this.name);
    }

    @Override
    public void handleIncomingMessage(String agent, Message message) {
        System.out.println(name+ ": Dostałem wiadomość!!");
    }

    @Override
    public String getName() {
        return name;
    }

    public void send(String worker, String agent, Message message) {
        this.communicator.sendMessage(worker, agent, message);
    }

    public void addMessageObserver(IMessageObserver observer) {
        communicator.getService().addMessageHandler(observer);
    }

    public void removeMessageObserver(IMessageObserver observer) {
        communicator.getService().removeMessageHandler(observer);
    }

}
