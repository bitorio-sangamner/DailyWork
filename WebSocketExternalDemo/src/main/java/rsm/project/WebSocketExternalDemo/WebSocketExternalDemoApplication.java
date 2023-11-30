package rsm.project.WebSocketExternalDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebSocketExternalDemoApplication {

	public static void main(String[] args) { SpringApplication.run(WebSocketExternalDemoApplication.class, args);
//		OkxConnectionController okxConnectionController = new OkxConnectionController();
//		okxConnectionController.okxConnect();
	}
}



