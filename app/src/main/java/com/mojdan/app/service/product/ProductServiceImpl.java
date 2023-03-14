package com.mojdan.app.service.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mojdan.app.model.category.CategoryRepository;
import com.mojdan.app.model.product.Product;
import com.mojdan.app.model.product.ProductRepository;
import com.mojdan.app.model.tag.Tag;
import com.mojdan.app.model.tag.TagRepository;
import com.mojdan.app.service.product.dto.ProductConverter;
import com.mojdan.app.service.product.dto.ProductDTO;
import com.mojdan.app.service.tag.dto.TagDTO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductConverter productConverter;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private CategoryRepository category;
	
	@Override
	public Iterable<ProductDTO> getAllProducts() {
		List<Product> list = productRepository.findAll();
		return productConverter.toDTOs(list);
	}
	
	@Override
	public Page<ProductDTO> getAllProducts(Pageable pageRequest) {
		Page<Product> page = productRepository.findAll(pageRequest);
		Page<ProductDTO> pageDTO = page.map(productConverter::toDTO);	
		return pageDTO;
	}
	
	@Override
	public void removeProducts(Iterable<ProductDTO> products) {		
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		products.forEach(list::add);
		List<Product> entities = productConverter.toEntities(list);
		productRepository.deleteAll(entities);
	}

	@Override
	public ProductDTO findOne(long id) {
		Optional<Product> product = productRepository.findById(id);
		return productConverter.toDTO(product.get());
	}

	@Override
	public ProductDTO save(ProductDTO product) {		
		Product productToSave = productConverter.toEntity(product);
		List<TagDTO> tags = product.getTags();
		if (tags != null && !tags.isEmpty()) {
			List<Tag> tagssaved = new ArrayList<Tag>();
			tags.forEach((tag)->{
				Tag tagtmp = tagRepository.findByName(tag.getName());
				if (tagtmp == null) {
					tagssaved.add(tagRepository.save(new Tag(tag.getName())));
				} else {
					tagssaved.add(tagtmp);
				}
			});
			productToSave.setTags(tagssaved);
		}
		Product saved = productRepository.save(productToSave);
		return productConverter.toDTO(saved);
	}

	@Override
	public void delete(long id) {
		Optional<Product> entity = productRepository.findById(id);
		if (entity.isPresent()) {
			productRepository.delete(entity.get());			
		}
	}

	@Override
	public ProductDTO updateProduct(ProductDTO productDTO) {
		Product product = productConverter.toEntity(productDTO);
		ProductDTO dto = productConverter.toDTO(productRepository.save(product));
		return dto;
	}

}
