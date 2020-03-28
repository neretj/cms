package com.mojdan.app.model.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserRepositoryJdbc implements UserRepository{

	// Spring Boot will create and configure DataSource and JdbcTemplate
		// To use it, just @Autowired
	    @Autowired
	    private JdbcTemplate jdbcTemplate;

	    @Override
	    public int count() {
	        return jdbcTemplate
	                .queryForObject("select count(*) from users", Integer.class);
	    }

	    @Override
	    public int save(User user) {
	        return jdbcTemplate.update(
	                "insert into users (name) values(?)",
	                user.getName());
	    }

	    @Override
	    public int update(User user) {
	        return jdbcTemplate.update(
	                "update users set name = ? where id = ?",
	                user.getName(), user.getId());
	    }


	    @Override
	    public int deleteById(Long id) {
	        return jdbcTemplate.update(
	                "delete users where id = ?",
	                id);
	    }

	    @Override
	    public List<User> findAll() {
	        return jdbcTemplate.query(
	                "select * from users",
	                (rs, rowNum) ->
	                        new User(
	                                rs.getLong("id"),
	                                rs.getString("name")
	                        )
	        );
	    }

	    // jdbcTemplate.queryForObject, populates a single object
	    @Override
	    public Optional<User> findById(Long id) {
	        return jdbcTemplate.queryForObject(
	                "select * from users where id = ?",
	                new Object[]{id},
	                (rs, rowNum) ->
	                        Optional.of(new User(
	                                rs.getLong("id"),
	                                rs.getString("name")
	                        ))
	        );
	    }

	    @Override
	    public List<User> findByName(String name) {
	        return jdbcTemplate.query(
	                "select * from users where name like ?",
	                new Object[]{"%" + name + "%"},
	                (rs, rowNum) ->
	                        new User(
	                                rs.getLong("id"),
	                                rs.getString("name")
	                        )
	        );
	    }

	    @Override
	    public String getNameById(Long id) {
	        return jdbcTemplate.queryForObject(
	                "select name from users where id = ?",
	                new Object[]{id},
	                String.class
	        );
	    }
	    
}
