package pl.edu.agh.eas.starter;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import pl.edu.agh.eas.starter.mocks.worker.WorkerConfig;

public class App {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/application-context.xml");
		List<WorkerConfig> configs = (List<WorkerConfig>) ctx.getBean("worker-configs");
	}
}
