package pl.edu.agh.lab.toik.comm.v1.impl;

import pl.edu.agh.lab.toik.comm.v1.ICommunicator;
import pl.edu.agh.lab.toik.comm.v1.IMessageObserver;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;


public class MessagingService implements ICommunicator.IMessagingService {

	private List<IMessageObserver> messageHandlers;

	public MessagingService() {
		messageHandlers = new ArrayList<IMessageObserver>();
	}

	public List<IMessageObserver> getMessageHandlers() {
		return messageHandlers;
	}

	public void setMessageHandlers(List<IMessageObserver> messageHandlers) {
		this.messageHandlers = messageHandlers;
	}

	public void invokeCommunication(String worker, String agent, Message message) {
		for (IMessageObserver messageObserver : messageHandlers)
			if (messageObserver.getName().equals(worker)) {
				messageObserver.handleIncomingMessage(agent, message);
			}
	}

	public void addMessageHandler(IMessageObserver observer) {
		messageHandlers.add(observer);
	}

	public void removeMessageHandler(IMessageObserver observer) {
		messageHandlers.remove(observer);
	}

}
