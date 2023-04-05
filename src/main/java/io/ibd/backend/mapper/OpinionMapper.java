package io.ibd.backend.mapper;

import io.ibd.backend.model.Opinion;
import io.ibd.backend.model.Product;
import io.ibd.backend.model.User;
import io.ibd.backend.model.dto.OpinionDto;
import io.ibd.backend.model.dto.OpinionPostDto;
import org.mapstruct.Mapper;

@Mapper
public interface OpinionMapper {

    OpinionDto entityToDto(Opinion opinion);

    Opinion dtoToEntity(OpinionDto opinion);

    OpinionDto postDtoToDto(OpinionPostDto opinionPostDto);

    default Opinion postDtoToEntity(OpinionPostDto opinionPostDto) {
        OpinionDto opinionDto = postDtoToDto(opinionPostDto);
        Opinion opinion = dtoToEntity(opinionDto);

        opinion.setProduct(Product.builder().id(opinionPostDto.getProductId()).build());
        opinion.setUser(User.builder().id(opinionPostDto.getUserId()).build());

        return opinion;
    }
}
