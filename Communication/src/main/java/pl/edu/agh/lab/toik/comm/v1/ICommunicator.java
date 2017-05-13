package pl.edu.agh.lab.toik.comm.v1;

import pl.edu.agh.lab.toik.comm.v1.impl.Message;
import pl.edu.agh.lab.toik.comm.v1.impl.MessagingService;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICommunicator {

	public void init(String name);

	public void sendMessage(String worker, String agent, Message message);

	public MessagingService getService();

	interface IMessagingService extends Remote {

        public void invokeCommunication(String worker, String agent, Message message)
                throws RemoteException;

    }
}