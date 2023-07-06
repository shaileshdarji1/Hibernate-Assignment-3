package com.spring.transactional.service;

import com.spring.transactional.entity.Product;

public interface ProductService {

    public Product saveProduct(Product product);

    public Product getProduct(Integer id);

}
