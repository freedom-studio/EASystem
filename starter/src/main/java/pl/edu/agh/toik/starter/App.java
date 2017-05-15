package pl.edu.agh.toik.starter;

public class App {
	
	
	public static void main(String[] args) {
		Starter starter = new DefaultStarter("src/main/resources/application-context.xml");
		starter.start();
	}
}
