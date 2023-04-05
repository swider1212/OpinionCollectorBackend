package io.ibd.backend.controller;

import io.ibd.backend.mapper.OpinionMapper;
import io.ibd.backend.model.Opinion;
import io.ibd.backend.model.dto.OpinionDto;
import io.ibd.backend.model.dto.OpinionPostDto;
import io.ibd.backend.service.OpinionService;
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
@RequestMapping("/opinions")
public class OpinionController {

    @Autowired
    private final OpinionService opinionService;

    private final OpinionMapper opinionMapper = Mappers.getMapper(OpinionMapper.class);

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<OpinionDto>> getAllOpinions(){
        List<Opinion> opinions = opinionService.getAll();

        return ok(opinions.stream()
                        .map(opinionMapper::entityToDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(
            value = "/{opinionId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<OpinionDto> getOpinionById(@PathVariable Long opinionId){
        Opinion opinion = opinionService.getById(opinionId);

        return ok(opinionMapper.entityToDto(opinion));
    }

    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<OpinionDto> createOpinion(@RequestBody OpinionPostDto opinionPostDto) {
        Opinion opinion = opinionMapper.postDtoToEntity(opinionPostDto);
        Opinion createdOpinion = opinionService.create(opinion);

        return ok(opinionMapper.entityToDto(createdOpinion));
    }

    @PutMapping(
            value = "/update/{opinionId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<OpinionDto> updateOpinion(@PathVariable Long opinionId, @RequestBody OpinionPostDto opinionPostDto) {
        Opinion opinion = opinionMapper.postDtoToEntity(opinionPostDto);
        Opinion updatedOpinion = opinionService.update(opinionId, opinion);

        return ok(opinionMapper.entityToDto(updatedOpinion));
    }

    @DeleteMapping(
            value = "/delete/{opinionId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<OpinionDto> updateOpinion(@PathVariable Long opinionId) {
        Opinion deletedOpinion = opinionService.delete(opinionId);

        return ok(opinionMapper.entityToDto(deletedOpinion));
    }
}
