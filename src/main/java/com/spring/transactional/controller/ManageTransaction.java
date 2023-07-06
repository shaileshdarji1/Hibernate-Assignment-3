package com.spring.transactional.controller;

import com.spring.transactional.entity.Product;
import com.spring.transactional.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class ManageTransaction {

    @Autowired
    ProductService productService;

    @PostMapping("/default")
    @Transactional
    public void saveProductsdefault(@RequestBody Product product){
        for(int i=0;i<5;i++){
            product.setId(product.getId()+i);
            productService.saveProduct(product);

            if(i==2){
                throw new RuntimeException("Runtime Exception");
            }

        }
    }

    @PostMapping("/exception")
    @Transactional(rollbackFor = Exception.class)
    public void saveProducts(@RequestBody Product product) throws Exception {
        for(int i=0;i<5;i++){
            product.setId(product.getId()+i);
            productService.saveProduct(product);

            if(i==2){
                throw new Exception("Checked Exception");
            }

        }
    }

    @PostMapping("/runtime")
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveProductNoRollback(@RequestBody Product product) {
        TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();
        if(status.isNewTransaction())
        {
            System.out.println("Transaction is active && New Transaction is created");
        }
        else{
            System.out.println("Transaction is used existing");
        }
        for(int i=0;i<5;i++){
            product.setId(product.getId()+i);
            productService.saveProduct(product);

            if(i==2){
                throw new RuntimeException("Runtime Exception");
            }

        }
    }
}
