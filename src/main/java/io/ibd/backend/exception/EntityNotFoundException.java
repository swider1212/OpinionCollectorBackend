package io.ibd.backend.exception;

import io.ibd.backend.model.ModelEntity;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private static final String messageTemplate = "%s entity with %s = %s was not found!";

    private final Class entityClass;

    public <T extends ModelEntity> EntityNotFoundException(Class<T> entityClass, String paramName, Object paramValue) {
        super(String.format(messageTemplate, entityClass, paramName, paramValue));

        this.entityClass = entityClass;
    }
}
