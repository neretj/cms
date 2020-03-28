package com.mojdan.app;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mojdan.app.model.user.User;
import com.mojdan.app.model.user.UserRepository;

@SpringBootApplication
public class AccessingDataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	  public static void main(String[] args) {
	    SpringApplication.run(AccessingDataJpaApplication.class);
	  }

	  @Bean
	  public CommandLineRunner demo(UserRepository repository) {
	    return (args) -> {
	      // save a few users
	      repository.save(new User("Jack"));
	      repository.save(new User("Chloe"));
	      repository.save(new User("Kim"));
	      repository.save(new User("David"));
	      repository.save(new User("Michelle"));

	      // fetch all users
	      log.info("Users found with findAll():");
	      log.info("-------------------------------");
	      for (User user : repository.findAll()) {
	        log.info(user.toString());
	      }
	      log.info("");

	      // fetch an individual user by ID
	      Optional<User> user = repository.findById(1L);
	      log.info("User found with findById(1L):");
	      log.info("--------------------------------");
	      log.info(user.toString());
	      log.info("");

	      // fetch users by last name
	      log.info("User found with findByLastName('Bauer'):");
	      log.info("--------------------------------------------");
	      repository.findByName("Bauer").forEach(bauer -> {
	        log.info(bauer.toString());
	      });
	      // for (User bauer : repository.findByLastName("Bauer")) {
	      //  log.info(bauer.toString());
	      // }
	      log.info("");
	    };
	  }

}
