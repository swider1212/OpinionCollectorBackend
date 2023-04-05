package io.ibd.backend.controller;

import io.ibd.backend.mapper.AlertMapper;
import io.ibd.backend.mapper.OpinionMapper;
import io.ibd.backend.mapper.ProductMapper;
import io.ibd.backend.model.Alert;
import io.ibd.backend.model.Category;
import io.ibd.backend.model.Opinion;
import io.ibd.backend.model.Product;
import io.ibd.backend.model.dto.*;
import io.ibd.backend.service.AlertService;
import io.ibd.backend.service.OpinionService;
import io.ibd.backend.service.ProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private final ProductService productService;
    @Autowired
    private final AlertService alertService;
    @Autowired
    private final OpinionService opinionService;

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    private final AlertMapper alertMapper = Mappers.getMapper(AlertMapper.class);
    private final OpinionMapper opinionMapper = Mappers.getMapper(OpinionMapper.class);

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<Product> products = productService.getAll();

        return ok(products.stream()
                        .map(productMapper::entityToDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(
            value = "/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId){
        Product product = productService.getById(productId);

        return ok(productMapper.entityToDto(product));
    }

    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductPostDto productPostDto) {
        Product product = productMapper.postDtoToEntity(productPostDto);
        Product createdProduct = productService.create(product);

        return ok(productMapper.entityToDto(createdProduct));
    }

    @PutMapping(
            value = "/update/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductPostDto productPostDto) {
        Product product = productMapper.postDtoToEntity(productPostDto);
        Product updatedProduct = productService.update(productId, product);

        return ok(productMapper.entityToDto(updatedProduct));
    }

    @DeleteMapping(
            value = "/delete/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId) {
        Product deletedProduct = productService.delete(productId);

        return ok(productMapper.entityToDto(deletedProduct));
    }

    @GetMapping(
            value = "{productId}/alerts",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<AlertDto>> getAlertsForProduct(@PathVariable Long productId){
        List<Alert> alerts = alertService.getAllByProductId(productId);

        return ok(alerts.stream()
                .map(alertMapper::entityToDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping(
            value = "{productId}/opinions",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<OpinionDto>> getOpinionsForProduct(@PathVariable Long productId){
        List<Opinion> opinions = opinionService.getAllByProductId(productId);

        return ok(opinions.stream()
                .map(opinionMapper::entityToDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping(
            value = "/{productId}/hide",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProductDto> hideProduct(@PathVariable Long productId) {
        Product updatedProduct = productService.update(productId, product -> product.setHidden(true));

        return ok(productMapper.entityToDto(updatedProduct));
    }

    @GetMapping(
            value = "/{productId}/show",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProductDto> showProduct(@PathVariable Long productId) {
        Product updatedProduct = productService.update(productId, product -> product.setHidden(false));

        return ok(productMapper.entityToDto(updatedProduct));
    }
}
