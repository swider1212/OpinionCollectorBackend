package io.ibd.backend.controller;

import io.ibd.backend.mapper.AlertMapper;
import io.ibd.backend.model.Alert;
import io.ibd.backend.model.dto.AlertDto;
import io.ibd.backend.model.dto.AlertPostDto;
import io.ibd.backend.service.AlertService;
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
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    private final AlertService alertService;

    private final AlertMapper alertMapper = Mappers.getMapper(AlertMapper.class);

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<AlertDto>> getAllAlerts(){
        List<Alert> alerts = alertService.getAll();

        return ok(alerts.stream()
                        .map(alertMapper::entityToDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(
            value = "/{alertId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AlertDto> getAlertById(@PathVariable Long alertId){
        Alert alert = alertService.getById(alertId);

        return ok(alertMapper.entityToDto(alert));
    }

    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AlertDto> createAlert(@RequestBody AlertPostDto alertPostDto) {
        Alert alert = alertMapper.postDtoToEntity(alertPostDto);
        Alert createdAlert = alertService.create(alert);

        return ok(alertMapper.entityToDto(createdAlert));
    }

    @PutMapping(
            value = "/update/{alertId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AlertDto> updateAlert(@PathVariable Long alertId, @RequestBody AlertPostDto alertPostDto) {
        Alert alert = alertMapper.postDtoToEntity(alertPostDto);
        Alert updatedAlert = alertService.update(alertId, alert);

        return ok(alertMapper.entityToDto(updatedAlert));
    }

    @DeleteMapping(
            value = "/delete/{alertId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AlertDto> updateAlert(@PathVariable Long alertId) {
        Alert deletedAlert = alertService.delete(alertId);

        return ok(alertMapper.entityToDto(deletedAlert));
    }
}
