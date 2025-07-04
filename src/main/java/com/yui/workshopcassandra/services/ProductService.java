package com.yui.workshopcassandra.services;

import com.yui.workshopcassandra.model.dto.ProductDTO;
import com.yui.workshopcassandra.model.entities.Product;
import com.yui.workshopcassandra.repositories.ProductRepository;
import com.yui.workshopcassandra.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public ProductDTO findById(UUID id){
        Optional<Product> product = repository.findById(id);
        Product result = product.orElseThrow(()-> new ResourceNotFoundException("Recurso não encotnrado"));

        return  new ProductDTO(result);
    }

    public List<ProductDTO> findByDepartment(String department){
        List<Product> list;
        if("".equals(department)){
            list = repository.findAll();
        }else{
           list = repository.findByDepartment(department);
        }

        return list.stream().map(x -> new ProductDTO(x)).toList();
    }


    public List<ProductDTO> findByDescription(String text) {
        List<Product> list;
        if ("".equals(text)) {
            list = repository.findAll();
        }
        else {
            list = repository.findByDescription("%"+text+"%");
        }
        return list.stream().map(x -> new ProductDTO(x)).toList();
    }
}

