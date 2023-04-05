package io.ibd.backend.service;

import io.ibd.backend.exception.EntityNotFoundException;
import io.ibd.backend.model.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.function.Consumer;

public abstract class CrudService<ID, TEntity extends ModelEntity<ID>> {

    private final Class<TEntity> type = getType();

    private final JpaRepository<TEntity, ID> entityRepository;

    protected CrudService(JpaRepository<TEntity, ID> entityRepository) {
        this.entityRepository = entityRepository;
    }

    public List<TEntity> getAll() {

        return entityRepository
                .findAll();
    }

    public TEntity getById(ID id) {

        return entityRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(type, "id", id));
    }

    public TEntity create(TEntity entity) {

        return entityRepository
                .save(entity);
    }

    public TEntity update(ID id, TEntity entity) {
        entity.setId(id);

        return entityRepository
                .save(entity);
    }

    public TEntity update(ID id, Consumer<TEntity> modExpr) {
        TEntity entity = getById(id);

        modExpr.accept(entity);

        return entityRepository
                .save(entity);
    }

    public TEntity delete(ID id) {
        TEntity entity = getById(id);

        entityRepository.delete(entity);

        return entity;
    }

    abstract protected Class<TEntity> getType();
}
