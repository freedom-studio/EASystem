package pl.edu.agh.lab.toik.comm.v2;

import pl.edu.agh.lab.toik.comm.v2.impl.Message;

public interface IMessageObserver {

	public void handleIncomingMessage(Message message);

}
