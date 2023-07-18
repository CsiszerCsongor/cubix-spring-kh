package com.sb_kh.week1;

import com.sb_kh.week1.service.InitDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class Week1Application  implements CommandLineRunner {

	private final InitDbService initDbService;
	public static void main(String[] args) {
		SpringApplication.run(Week1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initDbService.deletePreviousData();
		initDbService.deleteAudTables();
		initDbService.addInitData();
	}
}
