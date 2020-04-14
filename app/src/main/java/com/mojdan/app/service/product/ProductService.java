package com.mojdan.app.service.product;

import com.mojdan.app.service.product.dto.ProductDTO;

public interface ProductService {

    public Iterable<ProductDTO> getAllProducts();
 
    public ProductDTO findOne(long id);
 
    public ProductDTO save(ProductDTO product);
 
    public void delete(long id);
    
    public ProductDTO updateProduct(ProductDTO productDTO);
}
