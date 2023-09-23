package com.example.demo;

import com.example.demo.entities.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product(null, "Computer", 4300, 3));
        productRepository.save(new Product(null, "Phone", 2000, 5));
        productRepository.save(new Product(null, "book", 500, 4));
        List<Product> products = productRepository.findAll();
        products.forEach(p -> {
            System.out.println(p.toString());
        });
        Product product = productRepository.findById(Long.valueOf(1)).get();
        System.out.println("__________________");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getQuantity());
        System.out.println("___________________");
        productRepository.findByNameContains("C").forEach(p -> {
            System.out.println(p);
        });
        System.out.println("___________________");
        productRepository.search("Phone").forEach(p -> {
            System.out.println(p);
        });
        System.out.println("___________________");
        productRepository.findByPriceGreaterThan(600).forEach(p -> {
            System.out.println(p);
        });
        System.out.println("___________________");
        productRepository.searchByPrice(500).forEach(p -> {
            System.out.println(p);
        });
    }
}
