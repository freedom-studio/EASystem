package pl.edu.agh.toik.starter;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import pl.edu.agh.toik.worker.WorkerConfig;

public class App {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/application-context.xml");
		List<WorkerConfig> workerConfigs = (List<WorkerConfig>) ctx.getBean("worker-configs");
		workerConfigs.forEach(c -> System.out.println(c.getAgentCount()));
	}
}
