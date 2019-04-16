package com.example.service;

import com.example.domain.Product;
import com.example.repository.LaptopRepo;
import com.example.repository.PCRepo;
import com.example.repository.PrinterRepo;
import com.example.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private LaptopRepo laptopRepo;
    @Autowired
    private PrinterRepo printerRepo;
    @Autowired
    private PCRepo pcRepo;

    public Product getProductByModel(Integer model) throws NoSuchElementException {
        Product product = productRepo.findById(model).get();//2.0.0.M7
        if (product == null) throw new NoSuchElementException();
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Transactional
    public void createProduct(Product product) {
        productRepo.save(product);
    }

    @Transactional
    public void updateBook(Product product, Integer model) throws NoSuchElementException {
        Product productNew = productRepo.findById(model).get();//2.0.0.M7
        if (product == null) throw new NoSuchElementException();

        productNew.setMaker(product.getMaker());
        productNew.setType(product.getType());
        productNew.setModel(product.getModel());
        //productNew.setLaptops(product.getLaptops());
        productNew.setPcList(product.getPcList());
        productNew.setPrinters(product.getPrinters());

        productRepo.save(productNew);
    }

    @Transactional
    public void deleteBook(Integer model) throws NoSuchElementException {
        Product product = productRepo.findById(model).get();//2.0.0.M7

        if (product == null) throw new NoSuchElementException();
        productRepo.delete(product);
    }
}
