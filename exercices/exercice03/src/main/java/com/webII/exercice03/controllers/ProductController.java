package com.webII.exercice03.controllers;

import com.webII.exercice03.dtos.ProductDTO;
import com.webII.exercice03.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody @Valid ProductDTO productDTO, UriComponentsBuilder uriComponentsBuilder) {
        productDTO = productService.add(productDTO);
        URI uri = uriComponentsBuilder.path("/product/{id}").buildAndExpand(productDTO.id()).toUri();
        return ResponseEntity.created(uri).body(productDTO);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<ProductDTO> productDTOS = productService.getAll();
        return ResponseEntity.ok().body(productDTOS);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.getProductById(id);

        if (productDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(productDTO);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) {
        ProductDTO updatedProductDTO = productService.update(id, productDTO);

        if (updatedProductDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(updatedProductDTO);
    }
}
