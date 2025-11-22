package dev.gerasimova.repository;

import dev.gerasimova.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p JOIN FETCH p.category")
    List<Product> findAllByCategory();
    @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.id = :id")
    Optional<Product> findByProductId(@Param("id")Long id);
    @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE lower(p.name) LIKE lower(concat('%', :searchText, '%')) " +
            "AND p.category.id = :id")
    List<Product> findProductsByNameAndCategory(@Param("searchText") String searchText, @Param("id")Long id);
}
