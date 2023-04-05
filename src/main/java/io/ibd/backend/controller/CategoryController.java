package io.ibd.backend.controller;

import io.ibd.backend.mapper.CategoryMapper;
import io.ibd.backend.mapper.ProductMapper;
import io.ibd.backend.model.Category;
import io.ibd.backend.model.Product;
import io.ibd.backend.model.dto.CategoryDto;
import io.ibd.backend.model.dto.CategoryPostDto;
import io.ibd.backend.model.dto.ProductDto;
import io.ibd.backend.service.CategoryService;
import io.ibd.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final ProductService productService;

    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<Category> categories = categoryService.getAll();

        return ok(categories.stream()
                .map(categoryMapper::entityToDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping(
            value = "/{categoryId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long categoryId) {
        Category category = categoryService.getById(categoryId);

        return ok(categoryMapper.entityToDto(category));
    }

    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryPostDto categoryPostDto) {
        Category category = categoryMapper.postDtoToEntity(categoryPostDto);
        Category createdCategory = categoryService.create(category);

        return ok(categoryMapper.entityToDto(createdCategory));
    }

    @PutMapping(
            value = "/update/{categoryId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryPostDto categoryPostDto) {
        Category category = categoryMapper.postDtoToEntity(categoryPostDto);
        Category updatedCategory = categoryService.update(categoryId, category);

        return ok(categoryMapper.entityToDto(updatedCategory));
    }

    @DeleteMapping(
            value = "/delete/{categoryId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long categoryId) {
        Category deletedCategory = categoryService.delete(categoryId);

        return ok(categoryMapper.entityToDto(deletedCategory));
    }

    @GetMapping(
            value = "/{categoryId}/products",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ProductDto>> getProductsForCategory(@PathVariable Long categoryId) {
        List<Product> products = productService.getAllByCategoryId(categoryId);

        return ok(products.stream()
                .map(productMapper::entityToDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping(
            value = "/{categoryId}/hide",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryDto> hideCategory(@PathVariable Long categoryId) {
        Category updatedCategory = categoryService.update(categoryId, category -> category.setHidden(true));

        return ok(categoryMapper.entityToDto(updatedCategory));
    }

    @GetMapping(
            value = "/{categoryId}/show",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryDto> showCategory(@PathVariable Long categoryId) {
        Category updatedCategory = categoryService.update(categoryId, category -> category.setHidden(false));

        return ok(categoryMapper.entityToDto(updatedCategory));
    }
}
