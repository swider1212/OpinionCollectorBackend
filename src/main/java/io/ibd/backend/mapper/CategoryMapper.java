package io.ibd.backend.mapper;

import io.ibd.backend.model.Category;
import io.ibd.backend.model.dto.CategoryDto;
import io.ibd.backend.model.dto.CategoryPostDto;
import org.mapstruct.Mapper;


@Mapper
public interface CategoryMapper {

    CategoryDto entityToDto(Category category);

    Category dtoToEntity(CategoryDto category);

    CategoryDto postDtoToDto(CategoryPostDto categoryPostDto);

    default Category postDtoToEntity(CategoryPostDto categoryPostDto) {
        CategoryDto categoryDto = postDtoToDto(categoryPostDto);

        return dtoToEntity(categoryDto);
    }
}
