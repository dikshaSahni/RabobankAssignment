package org.rabobank.process.records;

import org.rabobank.process.records.service.CustomerRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableTask
public class CustomerRecordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerRecordsApplication.class, args);
	}

	@Bean
	public RecordsProcessorTask recordsProcessorTask() {
		return new RecordsProcessorTask();
	}

	public class RecordsProcessorTask implements ApplicationRunner {
		
		@Autowired
		private CustomerRecordsService customerStatmentService;

		@Override
		public void run(ApplicationArguments args) throws Exception {
			customerStatmentService.processInputFiles();
			
		}
	}
}
