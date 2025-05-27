package com.yui.workshopcassandra.controllers;

import com.yui.workshopcassandra.model.dto.ProductDTO;
import com.yui.workshopcassandra.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable UUID id){
        ProductDTO result = service.findById(id);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> findByDepartment(
            @RequestParam(name = "department", defaultValue = "") String department){
        List<ProductDTO> result = service.findByDepartment(department);

        return ResponseEntity.ok().body(result);
    }
}
