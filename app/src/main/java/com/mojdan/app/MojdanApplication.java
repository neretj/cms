package com.mojdan.app;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mojdan.app.model.user.User;
import com.mojdan.app.model.user.UserRepository;

@EntityScan("com.mojdan.app.model")
@SpringBootApplication
public class MojdanApplication {

	private static final Logger log = LoggerFactory.getLogger(MojdanApplication.class);

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	  public static void main(String[] args) {
	    SpringApplication.run(MojdanApplication.class);
	  }

	  @Bean
	  public CommandLineRunner demo(UserRepository repository) {
	    return (args) -> {
	      // save a few users
	     
	    	repository.save(new User("jack", bCryptPasswordEncoder.encode("prueba1"), "Jack", "Aaa"));
			repository.save(new User("chloe", bCryptPasswordEncoder.encode("prueba2"), "Chloe", "Heinz"));
			repository.save(new User("kim", bCryptPasswordEncoder.encode("prueba3"), "Kim", "Jones"));
			repository.save(new User("michelle", bCryptPasswordEncoder.encode("prueba4"), "Michelle", "Hanss"));
			repository.save(new User("admin", bCryptPasswordEncoder.encode("admin"), "admin", "--"));
			repository.save(new User("jack", bCryptPasswordEncoder.encode("user"), "user", "Radd"));

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
