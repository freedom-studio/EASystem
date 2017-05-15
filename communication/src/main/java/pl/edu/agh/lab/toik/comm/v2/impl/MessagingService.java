package pl.edu.agh.lab.toik.comm.v2.impl;

import pl.edu.agh.lab.toik.comm.v2.ICommunicator;
import pl.edu.agh.lab.toik.comm.v2.IMessageObserver;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;


@SuppressWarnings("restriction")
@WebService(endpointInterface = "main.java.pl.edu.agh.lab.toik.comm.IMessagingService", serviceName = "IMessagingService")
public class MessagingService implements ICommunicator.IMessagingService {

	private List<IMessageObserver> messageHandlers;

	public MessagingService() {
		messageHandlers = new ArrayList<IMessageObserver>();
	}

	@WebMethod(exclude = true)
	public List<IMessageObserver> getMessageHandlers() {
		return messageHandlers;
	}

	@WebMethod(exclude = true)
	public void setMessageHandlers(List<IMessageObserver> messageHandlers) {
		this.messageHandlers = messageHandlers;
	}

	public void invokeCommunication(Message message) {
		for (IMessageObserver messageObserver : messageHandlers)
			messageObserver.handleIncomingMessage(message);
	}

	@WebMethod(exclude = true)
	public void addMessageHandler(IMessageObserver observer) {
		messageHandlers.add(observer);
	}

	@WebMethod(exclude = true)
	public void removeMessageHandler(IMessageObserver observer) {
		messageHandlers.remove(observer);
	}

}
