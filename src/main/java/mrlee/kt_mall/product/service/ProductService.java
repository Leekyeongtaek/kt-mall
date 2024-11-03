package mrlee.kt_mall.product.service;

import lombok.RequiredArgsConstructor;
import mrlee.kt_mall.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public void addProduct() {
    }

    public void modifyProduct() {
    }

    public void findProduct() {
    }

    public void findProducts() {
    }
}
