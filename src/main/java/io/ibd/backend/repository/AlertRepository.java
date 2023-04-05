package io.ibd.backend.repository;

import io.ibd.backend.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    @Query(value = "delete from Alert where id = :id")
    void deleteById(@Param("id") Long id);

    List<Alert> findAllByProductId(Long productId);
}
