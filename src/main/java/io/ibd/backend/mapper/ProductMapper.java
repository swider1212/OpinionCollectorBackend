package io.ibd.backend.mapper;

import io.ibd.backend.model.Category;
import io.ibd.backend.model.Product;
import io.ibd.backend.model.dto.ProductDto;
import io.ibd.backend.model.dto.ProductPostDto;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    ProductDto entityToDto(Product product);

    Product dtoToEntity(ProductDto product);

    ProductDto postDtoToDto(ProductPostDto productPostDto);

    default Product postDtoToEntity(ProductPostDto productPostDto) {
        ProductDto productDto = postDtoToDto(productPostDto);
        Product product = dtoToEntity(productDto);

        product.setCategory(Category.builder().id(productPostDto.getCategoryId()).build());

        return product;
    }
}

