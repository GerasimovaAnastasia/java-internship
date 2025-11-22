package dev.gerasimova.repository;

import dev.gerasimova.model.ProductReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductReviewRepository extends MongoRepository<ProductReview, String> {
    List<ProductReview> findByProductId(Long productId);
}
