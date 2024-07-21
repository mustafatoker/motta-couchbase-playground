package com.mustafatoker.motta;

import org.springframework.boot.SpringApplication;

public class TestMottaApplication {

	public static void main(String[] args) {
		SpringApplication.from(MottaApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
