package com.spring.transactional.service.impl;

import com.spring.transactional.entity.Product;
import com.spring.transactional.repository.ProductRepository;
import com.spring.transactional.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Product saveProduct(Product product) {
        TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();
        if(status.isNewTransaction())
        {
            System.out.println("Transaction is active && New Transaction is created");
        }
        else{
            System.out.println("Transaction is used existing");
        }
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(Integer id) {
        return productRepository.findById(id).orElse(null);
    }
}
