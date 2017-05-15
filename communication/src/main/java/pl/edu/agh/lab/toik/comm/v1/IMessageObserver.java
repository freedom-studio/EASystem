package pl.edu.agh.lab.toik.comm.v1;

import pl.edu.agh.lab.toik.comm.v1.impl.Message;

public interface IMessageObserver {

	public void handleIncomingMessage(String agent, Message message);

	public String getName();

}
