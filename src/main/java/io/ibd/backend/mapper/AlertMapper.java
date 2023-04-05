package io.ibd.backend.mapper;

import io.ibd.backend.model.Alert;
import io.ibd.backend.model.Product;
import io.ibd.backend.model.User;
import io.ibd.backend.model.dto.AlertDto;
import io.ibd.backend.model.dto.AlertPostDto;
import org.mapstruct.Mapper;


@Mapper
public interface AlertMapper {

    AlertDto entityToDto(Alert alert);

    Alert dtoToEntity(AlertDto alert);
    
    AlertDto postDtoToDto(AlertPostDto alertPostDto);
    
    default Alert postDtoToEntity(AlertPostDto alertPostDto) {
        AlertDto alertDto = postDtoToDto(alertPostDto);
        Alert alert = dtoToEntity(alertDto);

        alert.setProduct(Product.builder().id(alertPostDto.getProductId()).build());

        return alert;
    }
}
