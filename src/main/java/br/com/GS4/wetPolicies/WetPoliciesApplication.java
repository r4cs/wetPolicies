package br.com.GS4.wetPolicies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WetPoliciesApplication {

	public static void main(String[] args) {
		SpringApplication.run(WetPoliciesApplication.class, args);
	}
}




/*
//package br.com.GS4.wetPolicies;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.context.ApplicationContext;
//
//@SpringBootApplication
//@EnableFeignClients
//public class WetPoliciesApplication {
//
//	private final GerarWetPoliciesDb gerarWetPoliciesDb;
//
//	public WetPoliciesApplication(GerarWetPoliciesDb gerarWetPoliciesDb) {
//		this.gerarWetPoliciesDb = gerarWetPoliciesDb;
//	}
//
//	public static void main(String[] args) {
//		ApplicationContext context = SpringApplication.run(WetPoliciesApplication.class, args);
//		WetPoliciesApplication app = context.getBean(WetPoliciesApplication.class);
//		app.startDataGeneration();
//	}
//
//	public void startDataGeneration() {
//		gerarWetPoliciesDb.init();
//	}
//}
 */