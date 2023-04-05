package io.ibd.backend.repository;

import io.ibd.backend.model.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {

    @Query(value = "delete from Opinion where id = :id")
    void deleteById(@Param("id") Long id);

    List<Opinion> findAllByProductId(Long productId);

    List<Opinion> findAllByUserId(String userId);
}
