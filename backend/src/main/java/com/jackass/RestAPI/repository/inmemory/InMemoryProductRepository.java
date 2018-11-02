package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.Category;
import com.jackass.RestAPI.entity.Manufacturer;
import com.jackass.RestAPI.entity.Product;
import com.jackass.RestAPI.repository.ProductRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public List<Product> findAllByCategory(Category category) {
        return table.stream().filter(product -> product.getCategory().equals(category)).collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByManufacturer(Manufacturer manufacturer) {
        return table.stream().filter(product -> product.getManufacturer().equals(manufacturer)).collect(Collectors.toList());
    }

    @Override
    public Product save(Product product) {
        product.setId(id++);
        table.add(product);
        return product;
    }

    @Override
    public Product delete(Product product) {
        table.remove(product);
        return product;
    }
}
