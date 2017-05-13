package pl.edu.agh.lab.toik.comm.v2;

import pl.edu.agh.lab.toik.comm.v2.impl.Message;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICommunicator {

	public void init(String name, INamingService namingService);

	public void sendMessage(String worker, String agent, Message message);

	public void addMessageObserver(IMessageObserver observer);

	public void removeMessageObserver(IMessageObserver observer);

	@SuppressWarnings("restriction")
    @WebService
	interface IMessagingService extends Remote {

        public void invokeCommunication(@WebParam(name = "message") Message message)
                throws RemoteException;

    }
}