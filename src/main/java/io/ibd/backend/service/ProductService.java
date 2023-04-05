package io.ibd.backend.service;

import io.ibd.backend.model.Opinion;
import io.ibd.backend.model.Product;
import io.ibd.backend.repository.OpinionRepository;
import io.ibd.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class ProductService extends CrudService<Long, Product> {

    ProductRepository productRepository;

    OpinionRepository opinionRepository;

    public ProductService(ProductRepository productRepository, OpinionRepository opinionRepository) {
        super(productRepository);

        this.productRepository = productRepository;
        this.opinionRepository = opinionRepository;
    }

    @Override
    protected Class<Product> getType() {
        return Product.class;
    }

    public List<Product> getAllByCategoryId(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Product> getAll(){
        List<Product> products = super.getAll();
        List<Opinion> opinions = opinionRepository.findAll();
        products.forEach(product -> product.setRating(calculateRating(product.getId(), opinions)));

        return products;
    }

    @Override
    public Product getById(Long id){
        Product product = super.getById(id);
        product.setRating(calculateRating(id));

        return product;
    }


    private int calculateRating(Long id){
        return calculateRating(id, null);
    }

    private int calculateRating(Long id, List<Opinion> opinions){
        OptionalDouble rawRating;

        if (opinions == null) {
            opinions = opinionRepository.findAllByProductId(id);
        }

        rawRating = opinions.stream()
                .filter(opinion -> opinion.getProduct().getId().equals(id))
                .mapToInt(Opinion::getGrade)
                .average();

        return (int)rawRating.orElse(0);
    }
}
