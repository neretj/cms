package com.mojdan.app;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mojdan.app.model.user.User;
import com.mojdan.app.model.user.UserRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    //@Qualifier("jdbcUserRepository")              // Test JdbcTemplate
    @Qualifier("namedParameterJdbcUserRepository")  // Test NamedParameterJdbcTemplate
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("StartApplication...");
        runJDBC();
    }

    void runJDBC() {

        log.info("Creating tables for testing...");

        jdbcTemplate.execute("DROP TABLE users IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE users(" +
                "id SERIAL, name VARCHAR(255))");

        List<User> users = Arrays.asList(
                new User("Thinking in Java"),
                new User("Mkyong in Java"),
                new User("Getting Clojure"),
                new User("Head First Android Development")
        );

        log.info("[SAVE]");
        users.forEach(user -> {
            log.info("Saving...{}", user.getName());
            userRepository.save(user);
        });

        // count
        log.info("[COUNT] Total users: {}", userRepository.count());

        // find all
        log.info("[FIND_ALL] {}", userRepository.findAll());

        // find by id
        log.info("[FIND_BY_ID] :2L");
        User user = userRepository.findById(2L).orElseThrow(IllegalArgumentException::new);
        log.info("{}", user);

        // find by name (like)
        log.info("[FIND_BY_NAME] : like '%Java%'");
        log.info("{}", userRepository.findByName("Java"));

        // get name (string) by id
        log.info("[GET_NAME_BY_ID] :1L = {}", userRepository.getNameById(1L));

        // update
        log.info("[UPDATE] :2L :99.99");
        user.setName("Test");
        log.info("rows affected: {}", userRepository.update(user));

        // delete
        log.info("[DELETE] :3L");
        log.info("rows affected: {}", userRepository.deleteById(3L));

        // find all
        log.info("[FIND_ALL] {}", userRepository.findAll());

    }

}