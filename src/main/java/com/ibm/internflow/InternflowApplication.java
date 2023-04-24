package com.ibm.internflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InternflowApplication {
	public static void main(String[] args) {
		System.out.println("Hello intern! :)");
		SpringApplication.run(InternflowApplication.class, args);
	}

}
