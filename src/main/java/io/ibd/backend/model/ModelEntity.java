package io.ibd.backend.model;

public interface ModelEntity<ID> {
    ID getId();
    void setId(ID id);
}
