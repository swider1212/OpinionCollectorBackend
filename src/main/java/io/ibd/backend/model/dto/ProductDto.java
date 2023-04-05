package io.ibd.backend.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductDto {

    private Long id;

    private String name;

    private float price;

    private boolean hidden;

    private String description;

    private String imageUrl;

    private int rating;

    private CategoryDto category;
}
