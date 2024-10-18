package com.webII.exercice03.services;

import com.webII.exercice03.domain.Product;
import com.webII.exercice03.dtos.ProductDTO;
import com.webII.exercice03.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * This method adds a new product to the database.
     * It converts a ProductDTO into a Product entity, saves the entity
     * in the repository, and returns the ProductDTO with the generated ID.
     *
     * @param productDTO The DTO object containing the product information to be added.
     * @return The ProductDTO with the generated product ID after being saved in the database.
     */
    public ProductDTO add(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);

        productRepository.save(product);

        return new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getQuantity()
        );
    }

    /**
     * This method returns a list of all products in the form of ProductDTO.
     * It retrieves all products from the database, converts each Product entity
     * into a ProductDTO, and returns a list of ProductDTOs.
     *
     * @return A list of ProductDTO containing all products from the database.
     */
    public ArrayList<ProductDTO> getAll() {
        List<Product> products =  productRepository.findAll();
        ArrayList<ProductDTO> productDTOs = new ArrayList<>();

        products.forEach(product -> {;
            productDTOs.add(new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getQuantity()
            ));
        });

        return productDTOs;
    }

    /**
     * This method retrieves a product by its ID.
     * It checks if a product with the specified ID exists in the database,
     * and if it does, it converts the Product entity into a ProductDTO.
     *
     * @param id The ID of the product to be retrieved.
     * @return A ProductDTO containing the product details if found, or null if not found.
     */
    public ProductDTO getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            return new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getQuantity()
            );
        }

        return null;
    }

    /**
     * This method updates an existing product identified by its ID.
     * It retrieves the product from the database, updates its details
     * with the provided new ProductDTO, and saves the changes.
     *
     * @param id The ID of the product to be updated.
     * @param newProductDTO The ProductDTO containing the new details for the product.
     * @return A ProductDTO with the updated product details if the product is found, or null if not found.
     */
    public ProductDTO update(Long id, ProductDTO newProductDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            product.setName(newProductDTO.name());
            product.setDescription(newProductDTO.description());
            product.setQuantity(newProductDTO.quantity());

            productRepository.save(product);

            return new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getQuantity()
            );
        }

        return null;
    }
}
