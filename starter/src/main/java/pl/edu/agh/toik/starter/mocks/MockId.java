package pl.edu.agh.toik.starter.mocks;

import pl.edu.agh.toik.communication.Id;

public class MockId implements Id{
	private final String idString;
	
	public MockId(String id) {
		this.idString = id;
	}
	
	public String getValue() {
		return this.idString;
	}

}
