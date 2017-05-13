package pl.edu.agh.lab.toik.comm.v2.exmpl;


import pl.edu.agh.lab.toik.comm.v2.IMessageObserver;
import pl.edu.agh.lab.toik.comm.v2.impl.Message;

public class TestMessageHandler implements IMessageObserver {

	public void handleIncomingMessage(Message message) {
		System.out.println(message.getValue());
	}
}
