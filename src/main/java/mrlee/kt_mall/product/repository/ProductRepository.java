package mrlee.kt_mall.product.repository;

import mrlee.kt_mall.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
