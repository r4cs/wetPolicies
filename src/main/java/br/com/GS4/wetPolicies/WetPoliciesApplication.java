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
//	private final GerarVotacoesDb gerarVotacoesDb;
//
//	public WetPoliciesApplication(GerarWetPoliciesDb gerarWetPoliciesDb, GerarVotacoesDb gerarVotacoesDb) {
//		this.gerarWetPoliciesDb = gerarWetPoliciesDb;
//		this.gerarVotacoesDb = gerarVotacoesDb;
//	}
//
//	public static void main(String[] args) {
//		ApplicationContext context = SpringApplication.run(WetPoliciesApplication.class, args);
//		WetPoliciesApplication app = context.getBean(WetPoliciesApplication.class);
//		app.startDataGeneration();
//	}
//
//	public void startDataGeneration() {
//		gerarVotacoesDb.init();
////		gerarWetPoliciesDb.init();
//	}
//}

 */