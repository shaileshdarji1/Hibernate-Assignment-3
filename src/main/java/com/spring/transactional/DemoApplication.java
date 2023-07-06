package com.spring.transactional;

import com.spring.transactional.entity.Product;
import com.spring.transactional.repository.ProductRepository;
import com.spring.transactional.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
