package com.mojdan.app.model.user;


	import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

	@Repository
	public class NamedParameterJdbcUserRepository extends UserRepositoryJdbc {

	    @Autowired
	    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	    @Override
	    public int update(User user) {
	        return namedParameterJdbcTemplate.update(
	                "update users set name = :name where id = :id",
	                new BeanPropertySqlParameterSource(user));
	    }

	    @Override
	    public Optional<User> findById(Long id) {
	        return namedParameterJdbcTemplate.queryForObject(
	                "select * from users where id = :id",
	                new MapSqlParameterSource("id", id),
	                (rs, rowNum) ->
	                        Optional.of(new User(
	                                rs.getLong("id"),
	                                rs.getString("name")
	                        ))
	        );
	    }

	    @Override
	    public List<User> findByName(String name) {

	        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
	        mapSqlParameterSource.addValue("name", "%" + name + "%");

	        return namedParameterJdbcTemplate.query(
	                "select * from users where name like :name",
	                mapSqlParameterSource,
	                (rs, rowNum) ->
	                        new User(
	                                rs.getLong("id"),
	                                rs.getString("name")
	                        )
	        );
	    }

	}


