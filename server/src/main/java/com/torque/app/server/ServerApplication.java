//Indicates java that this is part of the project
package com.torque.app.server;
//import what is needed from springboot
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//tells java this is a springboot aplication
@SpringBootApplication

public class ServerApplication {

	public static void main(String[] args) {
		//runs the app
		SpringApplication.run(ServerApplication.class, args);
	}

}
