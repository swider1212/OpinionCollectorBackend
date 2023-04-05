package io.ibd.backend.service;

import io.ibd.backend.model.Alert;
import io.ibd.backend.model.Opinion;
import io.ibd.backend.repository.OpinionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionService extends CrudService<Long, Opinion> {

    OpinionRepository opinionRepository;

    public OpinionService(OpinionRepository opinionRepository) {
        super(opinionRepository);

        this.opinionRepository = opinionRepository;
    }

    @Override
    protected Class<Opinion> getType() {
        return Opinion.class;
    }

    public List<Opinion> getAllByProductId(Long productId){
        return opinionRepository.findAllByProductId(productId);
    }

    public List<Opinion> getAllByUserId(String userId){
        return opinionRepository.findAllByUserId(userId);
    }
}
