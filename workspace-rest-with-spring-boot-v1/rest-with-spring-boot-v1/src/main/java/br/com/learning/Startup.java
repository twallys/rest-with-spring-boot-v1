package br.com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.learning.config.FileStorageConfig;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageConfig.class
})
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
		
		//QUANDO FOR GRAVAR UM NOVO USUARIO USAR ISSO
//		  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//		  String result = bCryptPasswordEncoder.encode("123");
//		  System.out.println("My password encrypted " + result);
//		 
		 
	}

}
