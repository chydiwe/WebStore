package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.Category;
import com.jackass.RestAPI.entity.Manufacturer;
import com.jackass.RestAPI.entity.Product;
import com.jackass.RestAPI.repository.ProductRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Conditional(ConditionsConfig.InMemoryCondition.class)
public class InMemoryProductRepository extends InMemoryRepository<Product> implements ProductRepository {

    @Override
    public Product getProductById(int id) {
        return table.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Product getProductByName(String name) {
        return table.stream().filter(product -> product.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Set<Product> findAllByCategory(Category category/*, Pageable pageable*/) {
        return table.stream().filter(product -> product.getCategory().equals(category)).collect(Collectors.toSet());
    }

    @Override
    public Set<Product> findAllByManufacturer(Manufacturer manufacturer/*, Pageable pageable*/) {
        return table.stream().filter(product -> product.getManufacturer().equals(manufacturer)).collect(Collectors.toSet());
    }

    @Override
    public Set<Product> findAll(/*Pageable pageable*/) {
        return table;
    }

    @Override
    public Product save(Product product) {
        product.setId(id++);
        table.add(product);
        return product;
    }

    @Override
    public void delete(Product product) {
        table.remove(product);
    }
}
