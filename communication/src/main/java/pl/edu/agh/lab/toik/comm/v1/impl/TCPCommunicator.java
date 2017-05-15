package pl.edu.agh.lab.toik.comm.v1.impl;

import pl.edu.agh.lab.toik.comm.v1.ICommunicator;
import pl.edu.agh.lab.toik.comm.v1.IMessageObserver;
import pl.edu.agh.lab.toik.comm.v1.INamingService;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class TCPCommunicator implements ICommunicator {
	private NamingService namingService;
	private MessagingService localService;
	private String name;

	public TCPCommunicator(String structure) {
        this.namingService = new NamingService(structure);
        this.localService = new MessagingService();
	}

	public void init(String name) {
	    this.name = name;
    }

	public void sendMessage(String worker, String agent, Message message) {
		this.localService.invokeCommunication(worker, agent, message);
	}

    @Override
    public MessagingService getService() {
        return localService;
    }
}
