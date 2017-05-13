package pl.edu.agh.lab.toik.comm.v2.impl;

import pl.edu.agh.lab.toik.comm.v2.ICommunicator;
import pl.edu.agh.lab.toik.comm.v2.IMessageObserver;
import pl.edu.agh.lab.toik.comm.v2.INamingService;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class TCPCommunicator implements ICommunicator {
	private NamingService namingService;
	private String name;
	private MessagingService localService;
	private String ID;

	public void init(String name, INamingService namingService) {
		this.name = name;
		this.namingService = (NamingService) namingService;

		ID = this.namingService.GetWorker(this.name);

		try {
			LocateRegistry.createRegistry( Registry.REGISTRY_PORT );
			localService = new MessagingService();
			IMessagingService stub = (IMessagingService) UnicastRemoteObject
					.exportObject(localService, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(ID, stub);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String worker, String agent, Message message) {
		try {
			Registry registry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
			IMessagingService remoteService = (IMessagingService) registry
					.lookup(ID);
			remoteService.invokeCommunication(message);
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addMessageObserver(IMessageObserver observer) {
		localService.addMessageHandler(observer);
	}

	public void removeMessageObserver(IMessageObserver observer) {
		localService.removeMessageHandler(observer);
	}

}
