package com.spring.transactional.controller;

import com.spring.transactional.entity.Product;
import com.spring.transactional.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.Scanner;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    Scanner sc = new Scanner(System.in);


    @Transactional(isolation = Isolation.READ_COMMITTED)
    @RequestMapping("read-committed/{id}")
    public Product getProductcommit(@PathVariable Integer id){
        return productService.getProduct(id);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @RequestMapping("read-uncommitted/{id}")
    public Product getProductUncommit(@PathVariable Integer id){
        return productService.getProduct(id);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @RequestMapping("read-repeat/{id}")
    public void getProductRepeat(@PathVariable Integer id){
        while(true){
            System.out.println("1.Get Product Data");
            System.out.println("2.Exit");
            int num = sc.nextInt();
            if(num==1){
                System.out.println(productService.getProduct(id));
            }
            if(num==2){
                break;
            }
        }

    }
}
