package main;

import services.ProductService;

/**
 * @author cvoinea
 */
public class Main {

    public static void main(String[] args) {

        ProductService productService = new ProductService();
        productService.addProduct("bread");
        productService.addProduct("eggs");

        System.out.println(productService.findProducts());

    }
}
