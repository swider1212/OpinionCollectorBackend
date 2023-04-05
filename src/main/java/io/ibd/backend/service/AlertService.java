package io.ibd.backend.service;

import io.ibd.backend.model.Alert;
import io.ibd.backend.repository.AlertRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlertService extends CrudService<Long, Alert> {

    AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        super(alertRepository);

        this.alertRepository = alertRepository;
    }

    @Override
    protected Class<Alert> getType() {
        return Alert.class;
    }

    public List<Alert> getAllByProductId(Long productId){
        return alertRepository.findAllByProductId(productId);
    }
}
