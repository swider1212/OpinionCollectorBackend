package io.ibd.backend.service;

import io.ibd.backend.model.Category;
import io.ibd.backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends CrudService<Long, Category> {

    public CategoryService(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }

    @Override
    protected Class<Category> getType() {
        return Category.class;
    }
}
