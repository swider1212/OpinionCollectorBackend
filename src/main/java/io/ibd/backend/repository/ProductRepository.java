package io.ibd.backend.repository;

import io.ibd.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "delete from Product where id = :id")
    void deleteById(@Param("id") Long id);

    List<Product> findAllByCategoryId(Long categoryId);
}
