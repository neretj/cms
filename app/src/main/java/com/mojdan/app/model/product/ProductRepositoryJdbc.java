package com.mojdan.app.model.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProductRepositoryJdbc implements ProductRepository {
	
	// Spring Boot will create and configure DataSource and JdbcTemplate
		// To use it, just @Autowired
	    @Autowired
	    private JdbcTemplate jdbcTemplate;

	    @Override
	    public int count() {
	        return jdbcTemplate
	                .queryForObject("select count(*) from products", Integer.class);
	    }

	    @Override
	    public int save(Product product) {
	        return jdbcTemplate.update(
	                "insert into products (name, price) values(?,?)",
	                product.getName(), product.getPrice());
	    }

	    @Override
	    public int update(Product product) {
	        return jdbcTemplate.update(
	                "update products set price = ? where id = ?",
	                product.getPrice(), product.getId());
	    }


	    @Override
	    public int deleteById(Long id) {
	        return jdbcTemplate.update(
	                "delete products where id = ?",
	                id);
	    }

	    @Override
	    public List<Product> findAll() {
	        return jdbcTemplate.query(
	                "select * from products",
	                (rs, rowNum) ->
	                        new Product(
	                                rs.getLong("id"),
	                                rs.getString("name"),
	                                rs.getBigDecimal("price")
	                        )
	        );
	    }

	    // jdbcTemplate.queryForObject, populates a single object
	    @Override
	    public Optional<Product> findById(Long id) {
	        return jdbcTemplate.queryForObject(
	                "select * from products where id = ?",
	                new Object[]{id},
	                (rs, rowNum) ->
	                        Optional.of(new Product(
	                                rs.getLong("id"),
	                                rs.getString("name"),
	                                rs.getBigDecimal("price")
	                        ))
	        );
	    }

	    @Override
	    public List<Product> findByNameAndPrice(String name, BigDecimal price) {
	        return jdbcTemplate.query(
	                "select * from products where name like ? and price <= ?",
	                new Object[]{"%" + name + "%", price},
	                (rs, rowNum) ->
	                        new Product(
	                                rs.getLong("id"),
	                                rs.getString("name"),
	                                rs.getBigDecimal("price")
	                        )
	        );
	    }

	    @Override
	    public String getNameById(Long id) {
	        return jdbcTemplate.queryForObject(
	                "select name from products where id = ?",
	                new Object[]{id},
	                String.class
	        );
	    }

}
